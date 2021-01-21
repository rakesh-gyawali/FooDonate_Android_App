package com.example.food_donation_dissertation.adapter;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.food_donation_dissertation.R;

import java.util.ArrayList;

public class RowItemAdapter extends RecyclerView.Adapter<RowItemAdapter.ViewHolder> {
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> mImages = new ArrayList<>();
    private ArrayList<String> mAddresses = new ArrayList<>();
    private Context mContext;
    private static final String TAG = "RecyclerViewAdapter";

    public RowItemAdapter(ArrayList<String> mNames, ArrayList<Integer> mImages, ArrayList<String> mAddresses, Context mContext) {
        this.mNames = mNames;
        this.mImages = mImages;
        this.mAddresses = mAddresses;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        holder.itemImage.setImageDrawable(ContextCompat.getDrawable(mContext, mImages.get(position)));
        holder.tvName.setText(mNames.get(position));
        holder.tvAddress.setText(mAddresses.get(position));
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder{
        ImageView itemImage;
        TextView tvName;
        TextView tvAddress;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            tvName = itemView.findViewById(R.id.tvName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
        }
    }
}
