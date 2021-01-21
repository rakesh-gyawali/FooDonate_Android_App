package com.example.food_donation_dissertation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class HomeFragment extends Fragment {
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //-----carousel view -----
        CarouselView carouselView;
        int[] sampleImages = {R.drawable.poor_man, R.drawable.volunteers};
        carouselView = (CarouselView) view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        ImageListener imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(sampleImages[position]);
            }
        };
        carouselView.setImageListener(imageListener);

        CarouselView carouselView1;
        int[] sampleImages1 = {R.drawable.poor_man, R.drawable.volunteers};
        carouselView1 = (CarouselView) view.findViewById(R.id.carouselView1);
        carouselView1.setPageCount(sampleImages1.length);
        ImageListener imageListener1 = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(sampleImages1[position]);
            }
        };
        carouselView1.setImageListener(imageListener1);
        return view;

    }

}