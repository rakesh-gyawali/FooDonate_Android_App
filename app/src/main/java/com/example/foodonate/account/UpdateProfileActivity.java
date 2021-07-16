package com.example.foodonate.account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodonate.R;
import com.example.foodonate.URL;
import com.example.foodonate.account.userDevelopment.UserAPI;
import com.example.foodonate.account.userDevelopment.UserBLL;
import com.google.android.gms.maps.model.TileOverlay;

import java.io.IOException;

public class UpdateProfileActivity extends AppCompatActivity {

    private EditText edtFirstname, edtLastname, edtPhone, edtPassword, edtConfirmPassword;
    private Button btnUpdate, btnCancel;
    private String firstname, lastname, phone, password, profilePicture, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel);
        edtFirstname = findViewById(R.id.edtFirstname);
        edtLastname = findViewById(R.id.edtLastName);
        edtPhone = findViewById(R.id.edtPhone);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValuesFromIntent();
                getInputValues();
                UserBLL bll = new UserBLL(firstname, lastname, phone, password, profilePicture);
                if (!validateInput()) return;
                try {
                    if (bll.checkUpdateProfile()) {
                        finish();
                    } else {
                        Toast.makeText(UpdateProfileActivity.this, "Update Failed ...", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void getValuesFromIntent() {
        try {
            profilePicture = getIntent().getStringExtra("profile_picture");
            phone = getIntent().getStringExtra("phone");
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(this, "Could not get values from intent ...", Toast.LENGTH_SHORT).show();
        }
    }

    private void getInputValues() {
        firstname = edtFirstname.getText().toString();
        lastname = edtLastname.getText().toString();
        password = edtFirstname.getText().toString();
        confirmPassword = edtFirstname.getText().toString();
    }

    private Boolean validateInput() {
        if (firstname.isEmpty()) return false;
        if (lastname.isEmpty()) return false;
        if (password.isEmpty()) return false;
        if (confirmPassword.isEmpty()) return false;
        if (!password.equals(confirmPassword)) return false;
        return true;
    }
}