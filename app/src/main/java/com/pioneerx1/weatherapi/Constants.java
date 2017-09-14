package com.pioneerx1.weatherapi;


public class Constants {
    public static final String API_KEY = BuildConfig.API_KEY;
    public static final String API_BASE_URL = "https://api.openweathermap.org/data/2.5/forecast/daily?";
    public static final String LOCATION_QUERY_PARAMETER = "q"; // This if for "location"
    public static final String NUMBER_OF_DAYS_PARAMETER = "cnt";
    public static final String API_KEY_QUERY_PARAMETER = "appid";

    // working call in Postman:
    // api.openweathermap.org/data/2.5/forecast/daily?q=new york city,US&cnt=17&appid=c109379d321012346a8b0e6bdea99879
    // country code parameter not needed after all???
}
