package com.example.thaianhit.citytravel;

/**
 * Created by NGUYEN TUAN ANH on 11/8/2016.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import com.example.thaianhit.citytravel.CustomRecyclerAdapterSearch.RecyclerViewHolder;
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
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.name_location.setText(listData.get(position).getName());
       setAnimation(holder.container,position);
    }

    public void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.push_left_right);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
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
        public TextView discriptionlocation;
        public ImageView ratinglocation;
        public TextView text_rating;
        LinearLayout container;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            container=(LinearLayout) itemView.findViewById(R.id.recyclersearch);
            photo_location=(ImageView)itemView.findViewById(R.id.photo_location);
            name_location=(TextView)itemView.findViewById(R.id.name_location);
            discriptionlocation=(TextView)itemView.findViewById(R.id.description_location);
            ratinglocation=(ImageView)itemView.findViewById(R.id.rating_location);
            text_rating=(TextView)itemView.findViewById(R.id.text_rating);
        }

        // remove item when click button delete
        @Override
        public void onClick(View v) {
            removeItem(getAdapterPosition());
        }
    }

}