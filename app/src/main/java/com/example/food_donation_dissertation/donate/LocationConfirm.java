package com.example.food_donation_dissertation.donate;

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

import com.example.food_donation_dissertation.MainActivity;
import com.example.food_donation_dissertation.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class LocationConfirm extends AppCompatActivity implements View.OnClickListener{
    private TextView tvMap, tvAddress, tvCancel;
    private ImageView imgCheck;
    private Button btnConfirm;
    private final String TAG = "LocationConfirm started";
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
                intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
                break;
            case R.id.tvAddress:
                Log.i(TAG, "tvAddress is pressed");
                intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
                break;
            case R.id.btnConfirm:
                new MaterialAlertDialogBuilder(this)
                        .setTitle("Success")
                        .setMessage("Your request has been sent successfully. Check in Request Tab for more information.")
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
    private void getAddressFromSharedPreference() {
        SharedPreferences savedData = getSharedPreferences("USER_LOCATION", Context.MODE_PRIVATE);
        String addressLine =  savedData.getString("address_line", "");
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
}