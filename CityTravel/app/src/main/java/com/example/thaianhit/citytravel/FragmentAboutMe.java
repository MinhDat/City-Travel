package com.example.thaianhit.citytravel;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;


public class FragmentAboutMe extends Fragment
{
//    @Bind(R.id.img_avatar_about_me)
    ImageView imgAvatar;
  //  @Bind(R.id.img_edit_password_about_me)
    ImageButton img_btn_editPassword ;
 //   @Bind(R.id.img_edit_user_about_me)
    ImageButton img_btn_editUser;
  //  @Bind(R.id.txt_address_about_me)
    TextView txt_address;
   // @Bind(R.id.txt_birth_day_about_me)
    TextView txt_birth_day;
 //   @Bind(R.id.txt_genner_about_me)
    TextView txt_gender;
  //  @Bind(R.id.txt_phone_number_about_me)
    TextView txt_phone;
  //  @Bind(R.id.txt_full_name_about_me)
   // TextView txt_fullname;
    private View myFragmentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        myFragmentView = inflater.inflate(R.layout.activity_fragment_about_me, container, false);
        img_btn_editPassword = (ImageButton)myFragmentView.findViewById(R.id.img_edit_password);
        img_btn_editUser = (ImageButton)myFragmentView.findViewById(R.id.img_edit_user);
        txt_address = (TextView)myFragmentView.findViewById(R.id.txt_address_about_me);
        txt_birth_day =(TextView)myFragmentView.findViewById(R.id.txt_birth_day_about_me
        );
        txt_gender =(TextView)myFragmentView.findViewById(R.id.txt_gender);
        txt_phone = (TextView)myFragmentView.findViewById(R.id.txt_phone_number);
        imgAvatar = (ImageView)myFragmentView.findViewById(R.id.img_avatar_about_me);
        GetAccount();
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

        Glide.with(FragmentAboutMe.this).load(R.drawable.anh).asBitmap().centerCrop().into(new BitmapImageViewTarget(imgAvatar) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imgAvatar.setImageDrawable(circularBitmapDrawable);
            }
        });
        return myFragmentView;
    }
    public void GetAccount()
    {
//
//        Log.d("email_tag",LoginActivity.string_email);
//        call.enqueue(new Callback<Account>() {
//           @Override
//           public void onResponse(Call<Account> call, Response<Account> response)
//           {
//               Account account = response.body();
//               Log.d("address_tag",response.body().toString());
//               txt_address.setText(account.getAddress().toString());
//               txt_birth_day.setText(account.getBirth().toString());
//           }
//
//           @Override
//           public void onFailure(Call<Account> call, Throwable t) {
//                Log.d("address_tag",t.toString());
//           }
//       });
    }
}

