package com.example.android.weatherapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static com.example.android.weatherapp.FetchWeatherDetails.wd;

public class MainActivity extends AppCompatActivity {

    TextView cityView;
    SensorManager sensorManager;
    LocationManager locationManager;
    LocationListener locationListener;
    TextView detailsField;
    String city,postalCode;
    TextView currentTemperatureField;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                Geocoder gc = new Geocoder(MainActivity.this,Locale.getDefault());
                try {
                    Double lati = location.getLatitude();
                    Double longi = location.getLongitude();
                    wd.setLatitude(lati);
                    wd.setLongitude(longi);
                    try {
                        FetchWeatherDetails process = new FetchWeatherDetails();
                        process.execute();
                    }catch(Exception e) {
                    }
                    List<Address> addresses = gc.getFromLocation(lati,longi,1);
                    String address = addresses.get(0).getAddressLine(0);
                    city = (addresses.get(0).getLocality()).toUpperCase(Locale.US)+"\n"+addresses.get(0).getAdminArea()+","+addresses.get(0).getCountryName();
                    postalCode = addresses.get(0).getPostalCode();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

                Intent newIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(newIntent);
            }
        };

        configureButton();

        Intent show = new Intent(this, DayPage.class);

        startActivity(show);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 50:
                configureButton();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void configureButton() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET
                }, 50);
            }
            return;
        }

        locationManager.requestLocationUpdates("gps",100000,0,locationListener);

    }


}
