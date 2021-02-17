package com.example.foodonate.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.foodonate.MainActivity;
import com.example.foodonate.URL;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreference {
    public static String getToken() {
        Context applicationContext = MainActivity.getContextOfApplication();
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        return sharedPreferences.getString("TOKEN", "");
    }

    public static void storeAddress(Context context, Double lats, Double longs, String addressLine) {
        SharedPreferences sharedPreferences;
        sharedPreferences = context.getSharedPreferences("USER_LOCATION", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("address_line", addressLine);
        editor.putString("lat", String.valueOf(lats));
        editor.putString("long", String.valueOf(longs));
        editor.apply();
    }

    public static void clearAddress() {
        Context applicationContext = MainActivity.getContextOfApplication();
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("USER_LOCATION", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
    }

    public static void storeLoggedInStatus() {
        Context applicationContext = MainActivity.getContextOfApplication();
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("LOGIN", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("HAS_LOGGED_IN", true);
        editor.putString("TOKEN", URL.token);
        editor.apply();
    }

    public static void clearLoggedInStatus() {
        Context applicationContext = MainActivity.getContextOfApplication();
        SharedPreferences loginData = applicationContext.getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        loginData.edit().clear().apply();
    }

    public static void storeSignUpInfo(String firstname, String lastname, String phone, String password) {
        Context applicationContext = MainActivity.getContextOfApplication();
        SharedPreferences sharedPreferences =applicationContext.getSharedPreferences("SIGN_UP", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("firstname", firstname);
        editor.putString("lastname", lastname);
        editor.putString("phone", phone);
        editor.putString("password", password);
        editor.apply();
    }

    public static void storeDonateInfo(Context context, String foodTypes, String charity, String quantity, String expiryDate) {
        SharedPreferences sharedPreferences =context.getSharedPreferences("DONATE", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("foodTypes", foodTypes);
        editor.putString("charity", charity);
        editor.putString("quantity", quantity);
        editor.putString("expiryDate", expiryDate);
        editor.apply();
    }

    public static void clearDonateInfo(Context context) {
        SharedPreferences loginData = context.getSharedPreferences("DONATE", Context.MODE_PRIVATE);
        loginData.edit().clear().apply();
    }
}
