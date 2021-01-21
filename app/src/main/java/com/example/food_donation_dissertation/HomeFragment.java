package com.example.food_donation_dissertation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.food_donation_dissertation.adapter.RowItemAdapter;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ArrayList<String> mNames;
    ArrayList<Integer> mImages;
    ArrayList<String> mAddresses;
    private static final String TAG = "HomeFragment";


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
        //-----row item-----
          makeList();
          initRecyclerView(view);
        return view;
    }
    private void makeList() {
        mNames = new ArrayList<>();
        mImages = new ArrayList<>();
        mAddresses = new ArrayList<>();

        mImages.add(R.drawable.charity_logo);
        mNames.add("Rotatrot Club");
        mAddresses.add("Chakrapath, Kathmandu");

        mImages.add(R.drawable.charity_logo);
        mNames.add("KTM Youth Club");
        mAddresses.add("Bouddha, Kathmandu");

        mImages.add(R.drawable.charity_logo);
        mNames.add("KTM Youth Club");
        mAddresses.add("Bouddha, Kathmandu");

        mImages.add(R.drawable.charity_logo);
        mNames.add("KTM Youth Club");
        mAddresses.add("Bouddha, Kathmandu");

        mImages.add(R.drawable.charity_logo);
        mNames.add("KTM Youth Club");
        mAddresses.add("Bouddha, Kathmandu");

        mImages.add(R.drawable.charity_logo);
        mNames.add("KTM Youth Club");
        mAddresses.add("Bouddha, Kathmandu");
    }
    private void initRecyclerView(View view) {
        Log.d(TAG, "initRecyclerView: init recyclerview");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = view.findViewById(R.id.rvRowItem);
        recyclerView.setLayoutManager(layoutManager);
        RowItemAdapter adapter = new RowItemAdapter (mNames, mImages, mAddresses, getContext());
        recyclerView.setAdapter(adapter);
    }

}