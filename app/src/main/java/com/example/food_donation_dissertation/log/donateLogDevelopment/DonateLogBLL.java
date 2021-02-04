package com.example.food_donation_dissertation.log.donateLogDevelopment;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.food_donation_dissertation.MainActivity;
import com.example.food_donation_dissertation.URL;
import com.example.food_donation_dissertation.account.userDevelopment.UserAPI;
import com.example.food_donation_dissertation.account.userDevelopment.UserResponse;
import com.example.food_donation_dissertation.donate.requestDonateDevelopment.RequestDonateAPI;

import retrofit2.Call;
import retrofit2.Response;

public class DonateLogBLL {
    private String requestedDate;
    private String address;
    private String lat;
    private String longs;
    private String charity;
    private String token;
    private String quantity;
    private String expiryDate;
    private String foodTypes;



    public DonateLogBLL(String token, String requestedDate, String address, String lat, String longs, String charity,  String quantity, String expiryDate, String foodTypes) {
        this.token = token;
        this.requestedDate = requestedDate;
        this.address = address;
        this.lat = lat;
        this.longs = longs;
        this.charity = charity;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.foodTypes = foodTypes;
    }



    private Response<Void> response;

    public boolean checkPostRequest() {
        RequestDonateAPI api = URL.getInstance().create(RequestDonateAPI.class);

        Call<Void> call = api.postRequestDonate(token, requestedDate, address, lat, longs, charity, quantity, expiryDate, foodTypes);
        try {
            response = call.execute();
            if (response.isSuccessful()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

//    public UserResponse returnUser() {
//        return response.body();
//    }
    

//    private String getTokenFromSharedPreference() {
//        Context applicationContext = MainActivity.getContextOfApplication();
//        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
//        return sharedPreferences.getString("TOKEN", "");
//    }

}
