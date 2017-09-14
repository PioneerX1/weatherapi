package com.pioneerx1.weatherapi;


public class Constants {
    public static final String API_KEY = "BuildConfig.API_KEY";
    public static final String API_BASE_URL = "http://api.openweathermap.org/data/2.5/forecast?id=";
    public static final String LOCATION_QUERY_PARAMETER = "location"; //Example: "location"
    public static final String API_KEY_QUERY_PARAMETER = "appid";
    // public static final String API_KEY_QUERY_PARAMETER = "appid";
    // connect like: API_BASE_URL + YOUR_QUERY_PARAMETER + API_KEY
    // working call in Postman:
    // api.openweathermap.org/data/2.5/forecast/daily?q=new york city,US&cnt=17&appid=c109379d321012346a8b0e6bdea99879
}
