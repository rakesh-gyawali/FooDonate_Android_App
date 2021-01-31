package com.example.food_donation_dissertation.donate.requestDonateDevelopment;

import com.example.food_donation_dissertation.account.userDevelopment.UserResponse;

import java.util.Date;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RequestDonateAPI {
    @POST ("donates")
    Call<Void> postRequestDonate(@Field("requestedDate") Date requestedDate,  @Field("address") String address,
                                             @Field("lat") String lat, @Field("long") String longs, @Field("charity") String charity,
                                             @Field("user") String user, @Field("quantity") String quantity,
                                             @Field("expiryDate") Date expiryDate, @Field("foodTypes") String foodTypes);



}
