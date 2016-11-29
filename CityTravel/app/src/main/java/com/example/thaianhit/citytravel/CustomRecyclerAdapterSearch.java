package com.example.thaianhit.citytravel;

/**
 * Created by NGUYEN TUAN ANH on 11/8/2016.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.thaianhit.citytravel.CustomRecyclerAdapterSearch.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;
public class CustomRecyclerAdapterSearch extends RecyclerView.Adapter<RecyclerViewHolder>  {
    private List<DataRecyclerSearch> listData = new ArrayList<DataRecyclerSearch>();
    private int lastPosition = -1;
    private Context context;

    public CustomRecyclerAdapterSearch(List<DataRecyclerSearch> listData, Context context) {
        this.listData = listData;
        this.context=context;
    }

    public void updateList(List<DataRecyclerSearch> data) {
        listData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.item_recycler_search_location, viewGroup, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        holder.name_location.setText(listData.get(position).getName());
        holder.category.setText(listData.get(position).getCategory());
        holder.text_address.setText(listData.get(position).getAddress());
        holder.nearme.setText( String.valueOf(listData.get(position).getNearme()));
         holder.text_rating.setText(listData.get(position).getRating());
        Glide.with(context)
                .load(R.drawable.hotel)
                .override(60,60)
                .centerCrop()
                .into(holder.photo_location);
        setAnimation(holder.container,position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailServices.class);
                context.startActivity(intent);
                setAnimation(v, position);
            }
        });
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    public void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            viewToAnimate.animate().cancel();
            viewToAnimate.setTranslationY(100);
            viewToAnimate.setAlpha(0);
            viewToAnimate.animate().alpha(1.0f).translationY(0).setDuration(300).setStartDelay(position * 100);
        }
    }


    public void addItem(int position, DataRecyclerSearch data) {
        listData.add(position, data);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        listData.remove(position);
        notifyItemRemoved(position);
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        public ImageView photo_location;
        public TextView name_location;
        public TextView text_rating;
        public  TextView text_address;
        public TextView nearme;
        public TextView category;
        RelativeLayout container;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            container=(RelativeLayout)itemView.findViewById(R.id.recyclersearch);
            photo_location=(ImageView)itemView.findViewById(R.id.photo_location);
            name_location=(TextView)itemView.findViewById(R.id.name_location);
            category=(TextView)itemView.findViewById(R.id.category);
            text_rating=(TextView)itemView.findViewById(R.id.text_rating);
            text_address = (TextView)itemView.findViewById(R.id.tv_address);
            nearme = (TextView)itemView.findViewById(R.id.near_me);

        }

        // remove item when click button delete
        @Override
        public void onClick(View v) {
            removeItem(getAdapterPosition());
        }
    }

}