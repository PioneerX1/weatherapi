package com.pioneerx1.weatherapi.services;

import android.util.Log;

import com.pioneerx1.weatherapi.Constants;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OpenWeatherService {

    public static final String TAG = OpenWeatherService.class.getSimpleName();

    public static void findWeather(String location, Callback callback) {


        OkHttpClient client = new OkHttpClient.Builder()
                //.addInterceptor(new SigningInterceptor(consumer))
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.API_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.LOCATION_QUERY_PARAMETER, location);
        urlBuilder.addQueryParameter(Constants.NUMBER_OF_DAYS_PARAMETER, "17");

        urlBuilder.addQueryParameter(Constants.API_KEY_QUERY_PARAMETER, Constants.API_KEY);
        String url = urlBuilder.build().toString();

        Log.v(TAG, Constants.API_KEY);
        Log.v(TAG, location);
        Log.v(TAG, url);

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
