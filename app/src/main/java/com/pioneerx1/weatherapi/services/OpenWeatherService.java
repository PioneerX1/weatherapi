package com.pioneerx1.weatherapi.services;

import android.util.Log;

import com.pioneerx1.weatherapi.Constants;
import com.pioneerx1.weatherapi.models.DailyForecast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class OpenWeatherService {

    public static final String TAG = OpenWeatherService.class.getSimpleName();

    // method processes JSON data results from API
    public ArrayList<DailyForecast> processResults(Response response) {

        ArrayList<DailyForecast> forecasts = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject openWeatherJSON = new JSONObject(jsonData);
                // only need to pull city and country for object ones
                String city = openWeatherJSON.getJSONObject("city").getString("name");
                String country = openWeatherJSON.getJSONObject("city").getString("country");

                // loop through LIST in JSON data and grab info for every daily forecast
                JSONArray forecastsJSON = openWeatherJSON.getJSONArray("list");
                for (int i = 0; i < forecastsJSON.length(); i++) {
                    JSONObject forecastJSON = forecastsJSON.getJSONObject(i);
                    String date = forecastJSON.getString("dt");
                    String lowTemp = forecastJSON.getJSONObject("temp").getString("min");
                    String highTemp = forecastJSON.getJSONObject("temp").getString("max");
                    String humidity = forecastJSON.getString("humidity");
                    JSONArray weatherDetailsJSON = forecastJSON.getJSONArray("weather");
                    String conditions = weatherDetailsJSON.getJSONObject(0).getString("description");

                    // test to see if JSON data can be pulled correctly
                    Log.v(TAG, "THIS IS THE OBJECT!!!  date: " + date + " lowTemp: " + lowTemp + " highTemp: " + highTemp + " Conditions: " + conditions);

                    // create new daily forecast object, add it to ArrayList of daily forecasts
                    DailyForecast newForecast = new DailyForecast("seattle", "us", date, lowTemp, highTemp, humidity, conditions);
                    forecasts.add(newForecast);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return forecasts;
    }

    // method talks directly to Open Weather API and pulls forecasts
    public static void findWeather(String location, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                //.addInterceptor(new SigningInterceptor(consumer))
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.API_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.LOCATION_QUERY_PARAMETER, location);
        urlBuilder.addQueryParameter(Constants.NUMBER_OF_DAYS_PARAMETER, "17");

        urlBuilder.addQueryParameter(Constants.API_KEY_QUERY_PARAMETER, Constants.API_KEY);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
