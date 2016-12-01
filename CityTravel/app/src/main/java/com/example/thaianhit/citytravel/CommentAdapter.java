package com.example.thaianhit.citytravel;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

/**
 * Created by T.N on 11/15/2016.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {
    private List<Comment> commentsList;
    private Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,comment,date;
        public ImageView avatar;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView)view.findViewById(R.id.tv_name);
            comment = (TextView)view.findViewById(R.id.comment);
            avatar = (ImageView)view.findViewById(R.id.iv_avatar);
            date =(TextView)view.findViewById(R.id.date);
        }
    }


    public CommentAdapter(List<Comment> commentsList, Context context) {
        this.commentsList = commentsList;
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Comment comment = commentsList.get(position);
        holder.name.setText(comment.getFirst_name() + " " + comment.getLast_name());
        holder.comment.setText(comment.getComment());
        holder.date.setText(comment.getDate().toString());
        Glide.with(context).load(comment.getAvatar()).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.avatar) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(),resource);
                circularBitmapDrawable.setCircular(true);
                holder.avatar.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(commentsList!= null)
        {
            return commentsList.size();
        }
        else
            return 0;

    }
}
