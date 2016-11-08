package com.example.thaianhit.citytravel;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import com.example.thaianhit.citytravel.CustomRecyclerAdapter.RecyclerViewHolder;
/**
 * Created by NGUYEN TUAN ANH on 11/8/2016.
 */

public class CustomRecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder>  {
    private List<DataRecycler> listData = new ArrayList<DataRecycler>();

    public CustomRecyclerAdapter(List<DataRecycler> listData) {
        this.listData = listData;

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

    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int position) {
        viewHolder.txtName.setText(listData.get(position).getName());
    }

    public void addItem(int position, DataRecycler data) {
        listData.add(position, data);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        listData.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * ViewHolder for item view of list
     * */

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        public TextView txtName;
        public TextView txtDescription;
        public ImageView img_icon;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
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