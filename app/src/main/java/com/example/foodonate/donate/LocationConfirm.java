package com.example.foodonate.donate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodonate.MainActivity;
import com.example.foodonate.R;
import com.example.foodonate.log.donateLogDevelopment.DonateLogBLL;
import com.example.foodonate.util.SharedPreference;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class LocationConfirm extends AppCompatActivity implements View.OnClickListener{
    private TextView tvMap, tvAddress, tvCancel;
    private ImageView imgCheck;
    private Button btnConfirm;
    private final String TAG = "LocationConfirm started";

    String addressLine;
    private String quantity;
    private String foodTypes;
    private String charity;
    private String expiryDate;
    private String lats;
    private String longs;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_confirm);

        tvMap = findViewById(R.id.tvMap);
        tvAddress = findViewById(R.id.tvAddress);
        btnConfirm = findViewById(R.id.btnConfirm);
        imgCheck = findViewById(R.id.imgCheck);
        tvCancel = findViewById(R.id.tvCancel);

        getAddressFromSharedPreference();
        getDonateValuesFromSharedPreference();
//        getValuesFromIntent();

        tvMap.setOnClickListener(this);
        tvAddress.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.tvMap:
            case R.id.tvAddress:
                intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
                break;
            case R.id.btnConfirm:
                postRequestCall();
                new MaterialAlertDialogBuilder(this)
                        .setTitle("Success")
                        .setMessage("Your request has been sent successfully. Check in Logs Tab to view status.")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                LocationConfirm.this.finish();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        }).show();
                break;
            case R.id.tvCancel:
                finish();
                break;
        }

    }
    private void postRequestCall() {
        java.util.Date date = new java.util.Date();
        DonateLogBLL bll = new DonateLogBLL(SharedPreference.getToken(), date.toString(), addressLine, lats, longs, charity, quantity, expiryDate, foodTypes);
        if (bll.checkPostRequest()) {
            SharedPreference.clearDonateInfo(getApplicationContext());
        } else {
            Toast.makeText(this, "postRequestCall error ...", Toast.LENGTH_SHORT).show();
        }
    }

    private void getAddressFromSharedPreference() {
        SharedPreferences savedData = getSharedPreferences("USER_LOCATION", Context.MODE_PRIVATE);
        addressLine =  savedData.getString("address_line", "");
        lats = savedData.getString("lat", "");
        longs = savedData.getString("long", "");

        if (addressLine.isEmpty()) {
            tvAddress.setText("Address has not been set ...");
            imgCheck.setVisibility(View.INVISIBLE);
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
    }

    private void getDonateValuesFromSharedPreference() {
        SharedPreferences savedData = getSharedPreferences("DONATE", Context.MODE_PRIVATE);
        foodTypes = savedData.getString("foodTypes", "");
        charity = savedData.getString("charity", "");
        quantity = savedData.getString("quantity", "");
        expiryDate = savedData.getString("expiryDate", "");
    }
}