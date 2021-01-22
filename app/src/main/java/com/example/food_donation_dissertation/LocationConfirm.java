package com.example.food_donation_dissertation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LocationConfirm extends AppCompatActivity implements View.OnClickListener {

    private TextView tvMap, tvAddress;
    private Button btnContinue;
    private final String TAG = "LocationConfirm started";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_confirm);

        tvMap = findViewById(R.id.tvMap);
        tvAddress = findViewById(R.id.tvAddress);
        btnContinue = findViewById(R.id.btnContinue);

        tvMap.setOnClickListener(this);
        tvAddress.setOnClickListener(this);
        btnContinue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.tvMap:
                 intent = new Intent(getApplicationContext(), MapsActivity.class);
                 startActivity(intent);
                 break;
            case R.id.tvAddress:
                Log.i(TAG, "tvAddress is pressed");
                intent = new Intent(getApplicationContext(), MapsActivity.class);
                 startActivity(intent);
                 break;
            case R.id.btnContinue:
                Log.i(TAG, "btnContinue is pressed");
                break;
        }
    }
}