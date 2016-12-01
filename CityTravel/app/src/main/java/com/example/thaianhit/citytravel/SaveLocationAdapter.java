package com.example.thaianhit.citytravel;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by hamin on 30-Nov-16.
 */

public class SaveLocationAdapter extends RecyclerView.Adapter<SaveLocationAdapter.ViewHolder> {
    List<PlaceLikeDTO> mDataset;
    Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public void removeAt(int position) {
        PlaceLikeDAO placeLikeDAO = new PlaceLikeDAO(mContext);
        placeLikeDAO.open();

        boolean kiemtra = placeLikeDAO.DeleteItemHistoryLike(mDataset.get(position));
        if (kiemtra)
            Toast.makeText(mContext, "Đã xóa thành công địa điểm yêu thích",Toast.LENGTH_LONG).show();
        placeLikeDAO.close();

        mDataset.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mDataset.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        // each data item is just a string in this case
        public TextView name_location;
        public TextView category;
        public TextView tv_date;
        public TextView tv_address;
        RecyclerItemClickListener.OnItemClickListener mItemClickListener;

        public ViewHolder(View v) {
            super(v);
            //mTextView = v;
            name_location = (TextView)v.findViewById(R.id.name_location);
            category = (TextView)v.findViewById(R.id.category);
            tv_date = (TextView)v.findViewById(R.id.tv_date);
            tv_address = (TextView)v.findViewById(R.id.tv_address);

            v.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            removeAt(getPosition());
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            //No button clicked
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();

            return false;
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SaveLocationAdapter(List<PlaceLikeDTO> myDataset, Context myContext) {
        mDataset = myDataset;
        this.mContext = myContext;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SaveLocationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {

        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_save_location, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new SaveLocationAdapter.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.name_location.setText(mDataset.get(position).getTenDiaDiem());
        holder.category.setText(mDataset.get(position).getTenDichVu());
        holder.tv_date.setText(mDataset.get(position).getThoiGianLike());
        holder.tv_address.setText(mDataset.get(position).getTenDiaChi());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if(mDataset == null) return 0;
        return mDataset.size();
    }
}
