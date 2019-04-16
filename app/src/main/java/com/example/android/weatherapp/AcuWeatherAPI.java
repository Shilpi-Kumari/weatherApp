package com.example.android.weatherapp;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AcuWeatherAPI extends AsyncTask <Void,Void,Void> {

    String dataReceived ="";
    String currentData ="";
    String forcastData ="";
    static WeatherData wd = new WeatherData();

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            URL url = new URL("http://dataservice.accuweather.com/locations/v1/cities/geoposition/search?apikey=hAz3ANVPCTEcBUZjzbVe5n9ghgtnXwm4&q=37.28%2C-121.955");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line ="";
            while(line != null) {
                line = bufferedReader.readLine();
                dataReceived = dataReceived + line;
            }
            Log.d("parsed",dataReceived);
            JSONObject reader = new JSONObject(dataReceived);

            int locationKey = reader.getInt("Key");
            inputStream.close();

            URL url1 = new URL("https://dataservice.accuweather.com/currentconditions/v1/"+locationKey+"?apikey=hAz3ANVPCTEcBUZjzbVe5n9ghgtnXwm4");
            httpURLConnection = (HttpURLConnection) url1.openConnection();
            inputStream = httpURLConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            line ="";
            while(line != null) {
                line = bufferedReader.readLine();
                currentData = currentData + line;
            }
            Log.d("parsed2",currentData);
            /*JSONArray jsonArray = new JSONArray(currentData);

            JSONObject jsonobject = jsonArray.getJSONObject(1);
            String epochTime = jsonobject.getString("EpochTime");
            System.out.println(epochTime);*/

            //String epochTime = (String) jsonArray.getString(Integer.parseInt("EpochTime"));


            inputStream.close();

            URL url2 = new URL("https://dataservice.accuweather.com/forecasts/v1/daily/1day/"+locationKey+"?apikey=hAz3ANVPCTEcBUZjzbVe5n9ghgtnXwm4");
            httpURLConnection = (HttpURLConnection) url2.openConnection();
            inputStream = httpURLConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            line ="";
            while(line != null) {
                line = bufferedReader.readLine();
                forcastData = forcastData + line;
            }
            Log.d("parsed3",forcastData);



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
