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
import java.util.Date;

public class FetchWeatherDetails extends AsyncTask <Void,Void,Void> {

    String dataReceived ="";
    String forcastData ="";
    static WeatherData wd = new WeatherData();
    static ForcastData fd = new ForcastData();

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            Double lati = wd.getLatitude();
            Double longi = wd.getLongitude();
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?lat="+lati+"&lon="+longi+"&appid=");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line ="";
            while(line != null) {
                line = bufferedReader.readLine();
                dataReceived = dataReceived + line;
            }

            URL forcastUrl = new URL("https://api.openweathermap.org/data/2.5/forecast?lat="+lati+"&lon="+longi+"&appid=");
            HttpURLConnection hConnection = (HttpURLConnection) forcastUrl.openConnection();
            InputStream is= hConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String readLine ="";
            while(readLine != null) {
                readLine = br.readLine();
                forcastData = forcastData + readLine;
            }

            JSONObject reader = new JSONObject(dataReceived);
            JSONArray sys  = reader.getJSONArray("weather");
            String rain = sys.getJSONObject(0).getString("description");
            int iconId = Integer.parseInt(sys.getJSONObject(0).getString("id"));
            String icon = sys.getJSONObject(0).getString("icon");

            JSONObject main = reader.getJSONObject("main");
            Double temperature = Double.valueOf(main.getString("temp"));
            temperature = ((temperature-273.15)*9/5 + 32);

            Double minTemperature = Double.valueOf(main.getString("temp_min"));
            minTemperature =((minTemperature-273.15)*9/5 + 32);
            Double maxTemperature = Double.valueOf(main.getString("temp_max"));
            maxTemperature =((maxTemperature-273.15)*9/5 + 32);
            String humidity = main.getString("humidity");
            String pressure = main.getString("pressure");

            JSONObject sys1 = reader.getJSONObject("sys");
            long dt = reader.getInt("dt");
            long sunrise = sys1.getLong("sunrise");
            long sunset = sys1.getLong("sunset");

            JSONObject wind = reader.getJSONObject("wind");
            Double speed = wind.getDouble("speed");

            wd.setSpeed(speed);
            wd.setRain(rain);
            wd.setDt(dt);
            wd.setIcon(icon);
            wd.setPressure(pressure);
            wd.setTemperature(temperature);
            wd.setMaxTemperature(maxTemperature);
            wd.setMinTemperature(minTemperature);
            wd.setHumidity(humidity);
            wd.setSunrise(sunrise);
            wd.setSunset(sunset);
            wd.setIconId(iconId);


            JSONObject data = new JSONObject(forcastData);
            JSONArray dataArray  = data.getJSONArray("list");

            JSONObject index0 = dataArray.optJSONObject(0);
            long day0 = index0.getLong("dt");
            System.out.println("day0"+day0);
            JSONObject main0 = index0.getJSONObject("main");
            Double day0TempMin = main0.getDouble("temp_min");
            day0TempMin =((day0TempMin-273.15)*9/5 + 32);
            Double day0TempMax = main0.getDouble("temp_max");
            day0TempMax =((day0TempMax-273.15)*9/5 + 32);
            JSONArray weatherArray0 = index0.getJSONArray("weather");
            JSONObject weather0 = weatherArray0.getJSONObject(0);
            String day0Icon = weather0.getString("icon");

            JSONObject index1 = dataArray.optJSONObject(1);
            long day1 = index1.getLong("dt");
            JSONObject main1 = index1.getJSONObject("main");
            Double day1TempMin = main1.getDouble("temp_min");
            day1TempMin =((day1TempMin-273.15)*9/5 + 32);
            Double day1TempMax = main1.getDouble("temp_max");
            day1TempMax =((day1TempMax-273.15)*9/5 + 32);
            JSONArray weatherArray1 = index1.getJSONArray("weather");
            JSONObject weather1 = weatherArray1.getJSONObject(0);
            String day1Icon = weather1.getString("icon");

            JSONObject index2 = dataArray.optJSONObject(2);
            long day2 = index2.getLong("dt");
            JSONObject main2 = index2.getJSONObject("main");
            Double day2TempMin = main2.getDouble("temp_min");
            day2TempMin =((day2TempMin-273.15)*9/5 + 32);
            Double day2TempMax = main2.getDouble("temp_max");
            day2TempMax =((day2TempMax-273.15)*9/5 + 32);
            JSONArray weatherArray2 = index2.getJSONArray("weather");
            JSONObject weather2 = weatherArray2.getJSONObject(0);
            String day2Icon = weather2.getString("icon");

            JSONObject index3 = dataArray.optJSONObject(3);
            long day3 = index3.getLong("dt");
            JSONObject main3 = index3.getJSONObject("main");
            Double day3TempMin = main3.getDouble("temp_min");
            day3TempMin =((day3TempMin-273.15)*9/5 + 32);
            Double day3TempMax = main3.getDouble("temp_max");
            day3TempMax =((day3TempMax-273.15)*9/5 + 32);
            JSONArray weatherArray3 = index3.getJSONArray("weather");
            JSONObject weather3 = weatherArray3.getJSONObject(0);
            String day3Icon = weather3.getString("icon");

            JSONObject index4 = dataArray.optJSONObject(4);
            long day4 = index4.getLong("dt");
            JSONObject main4 = index4.getJSONObject("main");
            Double day4TempMin = main4.getDouble("temp_min");
            day3TempMin =((day4TempMin-273.15)*9/5 + 32);
            Double day4TempMax = main4.getDouble("temp_max");
            day4TempMax =((day4TempMax-273.15)*9/5 + 32);
            JSONArray weatherArray4 = index4.getJSONArray("weather");
            JSONObject weather4 = weatherArray4.getJSONObject(0);
            String day4Icon = weather4.getString("icon");

            fd.setDay0TempMax(day0TempMax);
            fd.setDay0TempMin(day0TempMin);
            fd.setDay1TempMax(day1TempMax);
            fd.setDay1TempMin(day1TempMin);
            fd.setDay2TempMax(day2TempMax);
            fd.setDay2TempMin(day2TempMin);
            fd.setDay3TempMax(day3TempMax);
            fd.setDay3TempMin(day3TempMin);
            fd.setDay4TempMax(day4TempMax);
            fd.setDay4TempMin(day4TempMin);
            fd.setDay0Icon(day0Icon);
            fd.setDay1Icon(day1Icon);
            fd.setDay2Icon(day2Icon);
            fd.setDay3Icon(day3Icon);
            fd.setDay4Icon(day4Icon);

            fd.setDay0(day0);
            fd.setDay1(day1);
            fd.setDay2(day2);
            fd.setDay3(day3);
            fd.setDay4(day4);


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
