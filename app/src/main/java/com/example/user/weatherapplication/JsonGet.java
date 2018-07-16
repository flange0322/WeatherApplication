package com.example.user.weatherapplication;

public class JsonGet {

    private Double lon;
    private Double lat;
    private String main;
    private Double temp;
    private int pressure;
    private Double speed;
    private String name;

    public JsonGet(Double lon, Double lat, String main, Double temp, int pressure, Double speed, String name) {
        this.lon = lon;
        this.lat = lat;
        this.main = main;
        this.temp = temp;
        this.pressure = pressure;
        this.speed = speed;
        this.name = name;
    }

    public Double getLon(){
        return lon;
    }

    public void setLon(Double lon){
        this.lon = lon;
    }

    public Double getLat(){
        return lat;
    }

    public void setLat(Double lat){
        this.lat = lat;
    }

    public String getMain(){
        return main;
    }

    public void setMain(String main){
        this.main = main;
    }

    public Double getTemp(){
        return temp;
    }

    public void setTemp(Double wind){
        this.temp = temp;
    }

    public int getPressure(){
        return pressure;
    }

    public void setPressure(int pressure){
        this.pressure = pressure;
    }

    public Double getSpeed(){
        return speed;
    }

    public void setSpeed(Double speed){
        this.speed = speed;
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
