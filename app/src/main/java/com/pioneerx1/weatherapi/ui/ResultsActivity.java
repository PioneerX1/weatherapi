package com.pioneerx1.weatherapi.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.pioneerx1.weatherapi.models.DailyForecast;
import com.pioneerx1.weatherapi.services.OpenWeatherService;
import com.pioneerx1.weatherapi.R;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ResultsActivity extends AppCompatActivity {

    public static final String TAG = ResultsActivity.class.getSimpleName();

    @Bind(R.id.textViewLocation2) TextView mTextViewLocation2;

    public ArrayList<DailyForecast> mForecasts = new ArrayList<>();


    private void getWeather(String location) {
        final OpenWeatherService openWeatherService = new OpenWeatherService();
        openWeatherService.findWeather(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                try {
//                    String jsonData = response.body().string();
                    if (response.isSuccessful()) {
//                        Log.v(TAG, jsonData);
                        mForecasts = openWeatherService.processResults(response);
                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
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
