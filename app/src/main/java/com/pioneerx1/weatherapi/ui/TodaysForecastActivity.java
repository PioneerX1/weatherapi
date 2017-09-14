package com.pioneerx1.weatherapi.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.pioneerx1.weatherapi.services.OpenWeatherService;
import com.pioneerx1.weatherapi.R;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TodaysForecastActivity extends AppCompatActivity {

    public static final String TAG = TodaysForecastActivity.class.getSimpleName();

    @Bind(R.id.textViewLocation2) TextView mTextViewLocation2;


    private void getWeather(String location) {
        final OpenWeatherService openWeatherService = new OpenWeatherService();
        openWeatherService.findWeather(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todays_forecast);
        ButterKnife.bind(this);

        Intent oldIntent = getIntent();
        final String location = oldIntent.getStringExtra("location");

        mTextViewLocation2.setText("You entered " + location);
        getWeather(location);

    }
}