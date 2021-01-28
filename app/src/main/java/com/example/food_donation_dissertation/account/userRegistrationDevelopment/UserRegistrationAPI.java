package com.example.food_donation_dissertation.account.userRegistrationDevelopment;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserRegistrationAPI {
    @FormUrlEncoded
    @POST("api/users/registration-user/register")
    Call<RegistrationResponse> checkRegister(@Field("phoneNo") String phoneNo, @Field("password") String password, @Field("firstName") String firstName, @Field("lastName") String lastName, @Field("profilePicture") String profilePicture);

    @FormUrlEncoded
    @POST("api/users/login")
    Call<LoginResponse> checkLogin(@Field("phoneNo") String phoneNo, @Field("password") String password);


}
