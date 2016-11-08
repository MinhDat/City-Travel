package com.example.thaianhit.citytravel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import com.example.thaianhit.citytravel.CustomRecyclerAdapter.RecyclerViewHolder;
/**
 * Created by NGUYEN TUAN ANH on 11/8/2016.
 */

public class CustomRecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder>  {
    private List<DataRecycler> listData = new ArrayList<DataRecycler>();
    private int lastPosition = -1;
    private Context context;

    public CustomRecyclerAdapter(List<DataRecycler> listData,Context context) {
        this.listData = listData;
        this.context=context;
    }

    public void updateList(List<DataRecycler> data) {
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
        View itemView = inflater.inflate(R.layout.item_recycler, viewGroup, false);
        return new RecyclerViewHolder(itemView);
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
    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int position) {
        viewHolder.txtName.setText(listData.get(position).getName());
        setAnimation(viewHolder.container,position);
    }

    public void addItem(int position, DataRecycler data) {
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
            txtDescription=(TextView)itemView.findViewById(R.id.category_description);
            img_icon = (ImageView) itemView.findViewById(R.id.category_photo);
        }

        // remove item when click button delete
        @Override
        public void onClick(View v) {
            removeItem(getAdapterPosition());
        }
    }

}