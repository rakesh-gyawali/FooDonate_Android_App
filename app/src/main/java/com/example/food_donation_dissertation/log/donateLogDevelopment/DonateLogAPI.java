package com.example.food_donation_dissertation.log.donateLogDevelopment;

import java.util.Date;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DonateLogAPI {
    @FormUrlEncoded
    @GET("api/donates/")
    Call<DonateLogResponse> checkGetDonate();

//    @FormUrlEncoded
//    @POST("api/donates")
//    Call<DonateLogResponse> checkPostDonate(@Field("requestedDate") Date requestedDate, @Field("pickUpDate") Date pickUpDate,
//                                            @Field("address") String address, @Field("lat") String lat, @Field("long") String longs,
//                                            @Field("charity") String charity, @Field("user") String user, @Field("quantity") String quantity,
//                                            @Field("expiryDate") String expiryDate, @Field("foodTypes") String foodTypes);
}
