package com.example.android.weatherapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.android.weatherapp.FetchWeatherDetails.fd;
import static com.example.android.weatherapp.FetchWeatherDetails.wd;

//import static com.example.android.weatherapp.FetchWeatherDetails.wd;

public class DayPage extends AppCompatActivity {

    TextView textView1;
    TextView textView3;
    TextView textView4;
    TextView textView6;
    TextView textView8;
    TextView textView10;
    TextView updated_field;
    SensorManager sensorManager;
    LocationManager locationManager;
    LocationListener locationListener;
    String city,postalCode;
    TextView city_field;
    TextView weatherIcon;
    Typeface weatherFont;
    ImageView image1, imageDay0, imageDay1, imageDay2, imageDay3, imageDay4;
    TextView day0,day1,day2,day3,day4;
    TextView temp0,temp1,temp2,temp3,temp4;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_day);

        textView1 = (TextView) findViewById(R.id.city);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView6 = (TextView) findViewById(R.id.textView6);
        textView8 = (TextView) findViewById(R.id.textView8);
        textView10 = (TextView) findViewById(R.id.textView10);

        temp0 = (TextView) findViewById(R.id.temp0);
        temp1 = (TextView) findViewById(R.id.temp1);
        temp2 = (TextView) findViewById(R.id.temp2);
        temp3 = (TextView) findViewById(R.id.temp3);
        temp4 = (TextView) findViewById(R.id.temp4);


        updated_field = (TextView) findViewById(R.id.updated_field);
        city_field = (TextView) findViewById(R.id.city_field);
        image1 = (ImageView) findViewById(R.id.image1);
        imageDay0 = (ImageView) findViewById(R.id.icon0);
        imageDay1 = (ImageView) findViewById(R.id.icon1);
        imageDay2 = (ImageView) findViewById(R.id.icon2);
        imageDay3 = (ImageView) findViewById(R.id.icon3);
        imageDay4 = (ImageView) findViewById(R.id.icon4);

        day0 = (TextView) findViewById(R.id.day0);
        day1 = (TextView) findViewById(R.id.day1);
        day2 = (TextView) findViewById(R.id.day2);
        day3 = (TextView) findViewById(R.id.day3);
        day4 = (TextView) findViewById(R.id.day4);


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                Geocoder gc = new Geocoder(DayPage.this,Locale.getDefault());
                try {
                    Double lati = location.getLatitude();
                    Double longi = location.getLongitude();
                    List<Address> addresses = gc.getFromLocation(lati,longi,1);
                    String address = addresses.get(0).getAddressLine(0);
                    city = addresses.get(0).getLocality();
                    postalCode = addresses.get(0).getPostalCode();

                    try {
                        FetchWeatherDetails process = new FetchWeatherDetails();
                        process.execute();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    //Log.d("mylog", "mycompleteaddress"+addresses.toString());
                    //Log.d("mylog", "Address"+address);
                   // Log.d("postalcode", "postalCode: "+postalCode);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(500);
                } catch (Exception e){
                    e.printStackTrace();
                }
                city_field.setText(city.toUpperCase(Locale.US)+", CA, USA");

                String baseUrl = "http://openweathermap.org/img/w/";
                String imageUrl =  baseUrl+wd.getIcon()+".png";
                setIcon(5,imageUrl);
                //setWeatherIcon(5,imageUrl);
                String day0ImageUrl =  baseUrl+fd.getDay0Icon()+".png";
                setIcon(0,day0ImageUrl);
                String day1ImageUrl =  baseUrl+fd.getDay1Icon()+".png";
                setIcon(1,day1ImageUrl);
                String day2ImageUrl =  baseUrl+fd.getDay2Icon()+".png";
                setIcon(2,day2ImageUrl);
                String day3ImageUrl =  baseUrl+fd.getDay3Icon()+".png";
                setIcon(3,day3ImageUrl);
                String day4ImageUrl =  baseUrl+fd.getDay4Icon()+".png";
                setIcon(4,day4ImageUrl);

                textView3.setText(
                        wd.getRain().toUpperCase(Locale.US) +
                                "\n" + "Humidity: " +wd.getHumidity() + "%" +
                                "\n" + "Pressure: " + wd.getPressure() + " hPa");

                textView4.setText(
                String.format("%.2f", wd.getTemperature())+ " °F");

                DateFormat df = DateFormat.getDateTimeInstance();
                DateFormat df1 = DateFormat.getTimeInstance();
                String updatedOn = df.format(new Date(wd.getDt()*1000));
                updated_field.setText("Last update: " + updatedOn);

                String sunrise = df1.format(new Date(wd.getSunrise()*1000));
                textView6.setText(sunrise);

                String sunset = df1.format(new Date(wd.getSunset()*1000));
                textView8.setText(sunset);

                SimpleDateFormat sf = new SimpleDateFormat("EEE");
                String weekDay0 = sf.format(fd.getDay0()*1000) ;
                day0.setText(weekDay0);
                String weekDay1 = sf.format(new Date(fd.getDay1()*1000));
                day1.setText(weekDay1);
                String weekDay2 = sf.format(new Date(fd.getDay2()*1000)) ;
                day2.setText(weekDay2);
                String weekDay3 = sf.format(new Date(fd.getDay3()*1000)) ;
                day3.setText(weekDay3);
                String weekDay4 = sf.format(new Date(fd.getDay4()*1000)) ;
                day4.setText(weekDay4);

                textView10.setText((wd.getSpeed()).toString()+" mph");
                //textView.append("\nLongitude: "+ location.getLongitude() +"  \nLatitude: " + location.getLatitude() +" \nLocation: "+city + " \nRain:"+ wd.getRain()+

                //    "\nTemperature: "+wd.getTemperature()+"\nMin Temperature: "+wd.getMinTemperature()+"\nMax Temperature: "+wd.getMaxTemperature()+"\nHumidity: "+wd.getHumidity()+"\nSunrise: "+wd.getSunrise()+"\nSunset: "+wd.getSunset());



                temp0.setText(String.format("%.0f", fd.getDay0TempMax())+ "°|" +String.format("%.0f", fd.getDay0TempMin())+"°");
                temp1.setText(String.format("%.0f", fd.getDay1TempMax())+ "°|" +String.format("%.0f", fd.getDay1TempMin())+"°");
                temp2.setText(String.format("%.0f", fd.getDay2TempMax())+ "°|" +String.format("%.0f", fd.getDay2TempMin())+"°");
                temp3.setText(String.format("%.0f", fd.getDay3TempMax())+ "°|" +String.format("%.0f", fd.getDay3TempMin())+"°");
                temp4.setText(String.format("%.0f", fd.getDay4TempMax())+ "°|" +String.format("%.0f", fd.getDay4TempMin())+"°");

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

    }

    private void setWeatherIcon(int id,String imageUrl){
        System.out.println("imageUrl"+imageUrl);
        Picasso.with(this).load(imageUrl).placeholder(R.drawable.sunny)
                .error(R.drawable.sunny)
                .into(image1, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void setIcon(int id, String imageUrl)  {
        if(id ==5)
            Picasso.with(this).load(imageUrl).into(image1);
        if( id == 0)
            Picasso.with(this).load(imageUrl).resize(150,150).into(imageDay0);
         if(id == 1)
            Picasso.with(this).load(imageUrl).resize(150,150).into(imageDay1);
         if (id == 2)
             Picasso.with(this).load(imageUrl).resize(150,150).into(imageDay2);
         if(id == 3)
             Picasso.with(this).load(imageUrl).resize(150,150).into(imageDay3);
         if(id == 4)
             Picasso.with(this).load(imageUrl).resize(150,150).into(imageDay4);

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

    public void showFocast() {
        Intent show = new Intent(this, Forcast.class);
        startActivity(show);
    }
}
