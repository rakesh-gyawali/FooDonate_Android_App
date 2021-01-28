package com.example.food_donation_dissertation.account.userDevelopment;

import com.example.food_donation_dissertation.account.userRegistrationDevelopment.LoginResponse;
import com.example.food_donation_dissertation.account.userRegistrationDevelopment.RegistrationResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserAPI {
    @FormUrlEncoded
    @GET("users")
    Call<RegistrationResponse> checkRegister(@Field("phoneNo") String phoneNo, @Field("firstName") String firstName, @Field("lastName") String lastName, @Field("profilePicture") String profilePicture);

    @FormUrlEncoded
    @PUT("users")
    Call<LoginResponse> checkLogin(@Field("phoneNo") String phoneNo, @Field("password") String password);
}
