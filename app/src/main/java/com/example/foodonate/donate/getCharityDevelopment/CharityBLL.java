package com.example.foodonate.donate.getCharityDevelopment;

import com.example.foodonate.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class CharityBLL {
    private String name;
    Response<List<CharityResponse>> listResponse;

    public boolean checkGetCharityName() {
        CharityAPI api = URL.getInstance().create(CharityAPI.class);
        Call<List<CharityResponse>> call = api.getCharitiesName();
        try {
            listResponse = call.execute();
            if (listResponse.isSuccessful()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<CharityResponse> returnCharityNames() {
        return listResponse.body();
    }
}
