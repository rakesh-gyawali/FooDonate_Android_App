package com.example.foodonate.account;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodonate.MainActivity;
import com.example.foodonate.R;
import com.example.foodonate.URL;
import com.example.foodonate.account.uploadImageDevelopment.UploadImageBLL;
import com.example.foodonate.account.userDevelopment.UserBLL;
import com.example.foodonate.util.SharedPreference;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.material.appbar.MaterialToolbar;

import java.io.IOException;
import java.net.MalformedURLException;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {
    private TextView tvName;
    private TextView tvPhoneNo;
    private MaterialToolbar toolbar;
    private String profilePicture = "";
    private String phoneNo;
    private CircleImageView imgProfile;
    private Button btnUpdate;
    private Bitmap bmp;
    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        tvName = view.findViewById(R.id.tvName);
        tvPhoneNo = view.findViewById(R.id.tvPhoneNo);
        toolbar = view.findViewById(R.id.topAppBar);
        imgProfile = view.findViewById(R.id.imgProfile);
        btnUpdate = view.findViewById(R.id.btnUpdate);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.logOut) {
                    //To Erase Login Data ...
                    SharedPreference.clearLoggedInStatus();
                    SharedPreference.clearAddress();

                    Fragment loginFragment = new LoginFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.frameLayout, loginFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                return false;
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UpdateProfileActivity.class);
                intent.putExtra("profile_picture", profilePicture);
                intent.putExtra("phone", phoneNo);
                startActivity(intent);
            }
        });

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                View mView = getLayoutInflater().inflate(R.layout.image_viewer, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                photoView.setImageBitmap(bmp);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        userCall();
        return view;
    }
    private void userCall() {
        URL.getStrictMode();
        UserBLL bll = new UserBLL();
        if (bll.checkGetUser()) {
            tvName.setText(bll.returnUser().getFirstName() + " " +  bll.returnUser().getLastName());
            phoneNo = bll.returnUser().getPhoneNo();
            tvPhoneNo.setText(phoneNo);
            profilePicture = bll.returnUser().getProfilePicture();
            profilePictureCall();

        } else {
            Toast.makeText(getContext(), "userCall Failed ...", Toast.LENGTH_SHORT).show();
        }

    }

    private void profilePictureCall() {
        // if no profile picture then ...
        if (profilePicture == null) return;
        // if there is profile picture ...
        UploadImageBLL imageBLL = new UploadImageBLL();
        imageBLL.MakeStrict();
        String imagePath = URL.IMAGE_BASE_URL +"uploads/" + profilePicture;
        try {
            java.net.URL url = new java.net.URL(imagePath);
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            imgProfile.setImageBitmap(bmp);
        } catch (MalformedURLException e) {
            Toast.makeText(getContext(), "MalformedURLException", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(getContext(), "IOException", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}