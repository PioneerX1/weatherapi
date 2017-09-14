package com.pioneerx1.weatherapi.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pioneerx1.weatherapi.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.editTextLocation) EditText mEditTextLocation;
    @Bind(R.id.buttonLocation) Button mButtonLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mButtonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = mEditTextLocation.getText().toString();
                Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
                intent.putExtra("location", location);
                startActivity(intent);
            }
        });


    }
}
