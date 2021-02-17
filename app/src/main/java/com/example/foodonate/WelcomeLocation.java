package com.example.foodonate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodonate.donate.MapsActivity;

public class WelcomeLocation extends AppCompatActivity implements View.OnClickListener {
    private TextView tvMap, tvAddress, tvLater;
    private ImageView imgCheck;
    private Button btnContinue;
    private Boolean isAddressSelected = false;
    private final String TAG = "LocationConfirm started";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_location);

        tvMap = findViewById(R.id.tvMap);
        tvAddress = findViewById(R.id.tvAddress);
        tvLater = findViewById(R.id.tvLater);
        btnContinue = findViewById(R.id.btnConfirm);
        imgCheck = findViewById(R.id.imgCheck);

        requestLocationPermission();
        getAddressFromSharedPreference();

        tvMap.setOnClickListener(this);
        tvAddress.setOnClickListener(this);
        tvLater.setOnClickListener(this);
        btnContinue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.tvMap:
            case R.id.tvAddress:
                intent = new Intent(getApplicationContext(), MapsActivity.class);
                try {
                    Boolean isFromWelcomeScreen =  getIntent().getBooleanExtra("welcome_screen", false);
                    if (isFromWelcomeScreen)  intent.putExtra("welcome_screen", true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                startActivity(intent);
                break;
            case R.id.btnConfirm:
                intent = new Intent(getApplicationContext(), MainActivity.class);
                if (isAddressSelected) {
                    startActivity(intent);
                    break;
                }
            case R.id.tvLater:
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
        }
    }

    private void getAddressFromSharedPreference() {
        SharedPreferences savedData = getSharedPreferences("USER_LOCATION", Context.MODE_PRIVATE);
        String addressLine =  savedData.getString("address_line", "");
        if (addressLine.isEmpty()) {
            tvAddress.setText("Address has not been set ...");
            imgCheck.setVisibility(View.INVISIBLE);
            btnContinue.setBackgroundColor(Color.GRAY);
            btnContinue.setClickable(false);
            return;
        } else if (addressLine.length() > 35) {
            //shorten addressLine to fit in card view ...
            String addressLineInShort =  addressLine.substring(0, 30);
            tvAddress.setText(addressLineInShort.concat(" ...."));
        } else {
            tvAddress.setText(addressLine);
        }
        //show check mark ...
        imgCheck.setVisibility(View.VISIBLE);
        btnContinue.setBackgroundResource(R.color.colorPrimary);
        btnContinue.setClickable(true);
        tvLater.setVisibility(View.INVISIBLE);
        isAddressSelected = true;

    }

    private void requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults){
        switch (requestCode){
            case 1: {
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(getApplicationContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}