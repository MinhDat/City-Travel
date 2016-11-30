package com.example.thaianhit.citytravel;

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

import com.example.thaianhit.citytravel.CustomRecyclerAdapterHome.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by NGUYEN TUAN ANH on 11/8/2016.
 */

public class CustomRecyclerAdapterHome extends RecyclerView.Adapter<RecyclerViewHolder>  {
    private List<Category> listData = new ArrayList<Category>();
    private int lastPosition = -1;
    private Context context;

    public CustomRecyclerAdapterHome(List<Category> listData, Context context) {
        this.listData = listData;
        this.context=context;
    }

    public void updateList(List<Category> data) {
        listData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                                 int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.item_recycler_home, viewGroup, false);

        return new RecyclerViewHolder(itemView);
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
    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int position) {
        viewHolder.txtName.setText(listData.get(position).getName());
        setAnimation(viewHolder.container,position);
    }

    public void addItem(int position, Category data) {
        listData.add(position, data);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        listData.remove(position);
        notifyItemRemoved(position);
    }



    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        public TextView txtName;
        public TextView txtDescription;
        public ImageView img_icon;
        LinearLayout container;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            container=(LinearLayout) itemView.findViewById(R.id.item_recycler);
            txtName = (TextView) itemView.findViewById(R.id.category_name);
            img_icon = (ImageView) itemView.findViewById(R.id.category_photo);
        }

        // remove item when click button delete
        @Override
        public void onClick(View v) {
            removeItem(getAdapterPosition());
        }
    }

}