package com.example.food_donation_dissertation.donate.getCharityDevelopment;

import android.util.Log;

import com.example.food_donation_dissertation.URL;
import com.example.food_donation_dissertation.account.userDevelopment.UserAPI;
import com.example.food_donation_dissertation.account.userDevelopment.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class CharityBLL {
    private String name;
    Response<List<CharityResponse>> response;


    public boolean checkGetCharityName() {
        CharityAPI api = URL.getInstance().create(CharityAPI.class);
        Call<List<CharityResponse>> call = api.getCharitiesName();
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

    public List<CharityResponse> returnCharityNames() {
        return response.body();
    }
}
