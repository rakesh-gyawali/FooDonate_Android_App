package com.example.food_donation_dissertation.account.userRegistrationDevelopment;

import retrofit2.Call;
import retrofit2.Response;

import com.example.food_donation_dissertation.URL;

import java.io.IOException;

public class UserRegistrationBLL {
    private String phoneNo;
    private String password;
    private boolean isSuccess;

    public UserRegistrationBLL(String phoneNo, String password) {
        this.password = password;
        this.phoneNo = phoneNo;
    }

    public boolean checkLogin() {
        isSuccess = false;
        UserRegistrationAPI userAPI = URL.getInstance().create(UserRegistrationAPI.class);
        Call<LoginResponse> call =userAPI.checkLogin(phoneNo, password);
        try {
            Response<LoginResponse> response = call.execute();
            if (response.isSuccessful()) {
                URL.token = response.body().getToken();
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
