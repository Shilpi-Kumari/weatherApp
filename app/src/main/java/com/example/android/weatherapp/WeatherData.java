package com.example.android.weatherapp;

public class WeatherData {

    private String rain;
    private Double minTemperature;
    private Double maxTemperature;
    private Double temperature;
    private String humidity;
    private long sunrise;
    private long sunset;
    private String city;
    private Double latitude;
    private String icon;
    private long dt;
    private Double longitude;
    private int iconId;
    private Double speed;

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }



    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }



    public String getPressure() {

        return pressure;
    }

    public void setPressure(String pressure) {

        this.pressure = pressure;
    }

    private String pressure;

    public Double getLatitude() {

        return latitude;
    }

    public void setLatitude(Double latitude) {

        this.latitude = latitude;
    }

    public Double getLongitude() {

        return longitude;
    }

    public void setLongitude(Double longitude) {

        this.longitude = longitude;
    }


    public String getRain() {

        return rain;
    }

    public void setRain(String rain) {

        this.rain = rain;
    }

    public Double getMinTemperature() {

        return minTemperature;
    }

    public void setMinTemperature(Double minTemperature) {

        this.minTemperature = minTemperature;
    }

    public Double getMaxTemperature() {

        return maxTemperature;
    }

    public void setMaxTemperature(Double maxTemperature) {

        this.maxTemperature = maxTemperature;
    }

    public Double getTemperature() {

        return temperature;
    }

    public void setTemperature(Double temperature) {

        this.temperature = temperature;
    }

    public String getHumidity() {

        return humidity;
    }

    public void setHumidity(String humidity) {

        this.humidity = humidity;
    }

    public long getSunrise() {

        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;

    }

    public long getSunset() {

        return sunset;
    }

    public void setSunset(long sunset) {

        this.sunset = sunset;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
