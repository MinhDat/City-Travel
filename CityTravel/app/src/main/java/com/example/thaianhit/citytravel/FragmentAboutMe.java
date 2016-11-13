package com.example.thaianhit.citytravel;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;


public class FragmentAboutMe extends Fragment
{

    ImageView imgAvata;
    ImageButton img_btn_editUser, img_btn_editPassword;

    private View myFragmentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        myFragmentView = inflater.inflate(R.layout.activity_fragment_about_me, container, false);
        imgAvata = (ImageView) myFragmentView.findViewById(R.id.img_avatar);
        img_btn_editUser = (ImageButton) myFragmentView.findViewById(R.id.img_edit_user);
        img_btn_editPassword = (ImageButton) myFragmentView.findViewById(R.id.img_edit_password);

        img_btn_editUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditProfile.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        img_btn_editPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangePassword.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        Glide.with(FragmentAboutMe.this).load(R.drawable.anh).asBitmap().centerCrop().into(new BitmapImageViewTarget(imgAvata) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imgAvata.setImageDrawable(circularBitmapDrawable);
            }
        });
        return myFragmentView;
    }
}

