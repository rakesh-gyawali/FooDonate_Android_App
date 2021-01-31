package com.example.food_donation_dissertation.account.userDevelopment;

import android.widget.Toast;

import com.example.food_donation_dissertation.URL;
import com.example.food_donation_dissertation.account.userRegistrationDevelopment.RegistrationResponse;

import retrofit2.Call;
import retrofit2.Response;

public class UserBLL {
    private String firstName;
    private String lastName;
    private String phoneNo;
    Response<UserResponse> response;

    public UserBLL() {
    }

    public UserBLL(String firstName, String lastName, String phoneNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
    }

    public boolean checkGetUser() {
        UserAPI api = URL.getInstance().create(UserAPI.class);
        Call<UserResponse> call = api.getUser(URL.token);
        try {
             response = call.execute();
            if (response.isSuccessful()) {
                firstName = response.body().getFirstName();
                lastName = response.body().getLastName();
                phoneNo = response.body().getPhoneNo();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public UserResponse returnUser() {
        return response.body();
    }
}
