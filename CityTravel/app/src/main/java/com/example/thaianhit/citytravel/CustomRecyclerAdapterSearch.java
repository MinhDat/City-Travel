package com.example.thaianhit.citytravel;

/**
 * Created by NGUYEN TUAN ANH on 11/8/2016.
 */

import android.content.Context;

import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.thaianhit.citytravel._class.PlaceDetail;

import java.util.ArrayList;
import java.util.List;
public class CustomRecyclerAdapterSearch extends RecyclerView.Adapter<CustomRecyclerAdapterSearch.RecyclerViewHolder>  {
    private List<PlaceDetail> listData = new ArrayList<PlaceDetail>();
    private Context context;
    GPSTracker gps;

    public CustomRecyclerAdapterSearch (List<PlaceDetail> listData, Context context)
    {
        this.listData = listData;
        this.context=context;
    }
    public class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView photo_location;
        public TextView name_location;
        public TextView text_rating;
        public TextView text_address;
        public TextView nearme;
        public TextView category;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            photo_location = (ImageView) itemView.findViewById(R.id.photo_location);
            name_location = (TextView) itemView.findViewById(R.id.name_location);
            category = (TextView) itemView.findViewById(R.id.category);
            text_rating = (TextView) itemView.findViewById(R.id.text_rating);
            text_address = (TextView) itemView.findViewById(R.id.tv_address);
            nearme = (TextView) itemView.findViewById(R.id.near_me);

        }
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.item_recycler_search_location, viewGroup, false);
        return new RecyclerViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position)
    {
        gps = new GPSTracker(context);
        double latitude =0,longitude =0;
        holder.name_location.setText(listData.get(position).getPlace().getName_place());
        holder.category.setText(listData.get(position).getCategory().getName_category());
        holder.text_address.setText("Số " + listData.get(position).getSonha() +",đường " + listData.get(position).getDuong().getName_duong() +", "+ listData.get(position).getPhuong().getName_phuong() + ", " + listData.get(position).getQuanhuyen().getName_quanhuyen() + ", " + listData.get(position).getTinhthanh().getName_tinhthanh() + ".");

         holder.text_rating.setText(String.valueOf(listData.get(position).getDanhgia()));
        Glide.with(context)
                .load(listData.get(position).getCategory().getPhoto_category())
                .override(60,60)
                .centerCrop()
                .into(holder.photo_location);
        if(gps.canGetLocation())
        {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            Location locationA = new Location("point A");
            locationA.setLatitude(latitude);
            locationA.setLongitude(longitude);
            Location locationB = new Location("point B");
            locationB.setLatitude(listData.get(position).getKinhdo());
            locationB.setLongitude(listData.get(position).getVido());
            float distance = locationA.distanceTo(locationB);
            holder.nearme.setText(String.valueOf(distance));
        }
        else
        {
            holder.nearme.setText(String.valueOf(0));
        }
    }
    @Override
    public int getItemCount() {
        return listData.size();
    }


}