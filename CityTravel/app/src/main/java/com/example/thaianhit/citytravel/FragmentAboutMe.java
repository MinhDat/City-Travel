package com.example.thaianhit.citytravel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import butterknife.ButterKnife;

public class FragmentAboutMe extends AppCompatActivity {

    ImageView imgAvata;
    ImageButton img_btn_editUser, img_btn_editPassword;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_about_me);

        ButterKnife.bind(this);
        imgAvata = (ImageView) findViewById(R.id.img_avatar);
        img_btn_editUser = (ImageButton) findViewById(R.id.img_edit_user);
        img_btn_editPassword = (ImageButton) findViewById(R.id.img_edit_password);

        img_btn_editUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditProfile.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        img_btn_editPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChangePassword.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
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
    }
}
