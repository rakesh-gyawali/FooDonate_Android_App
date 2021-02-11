package com.example.foodonate.donate.getCharityDevelopment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CharityAPI {
    @GET("charities")
    Call<List<CharityResponse>> getCharitiesName();
}
