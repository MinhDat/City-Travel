package com.example.thaianhit.citytravel;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentAboutMe extends Fragment {
    ImageView imgAvatar;
    ImageButton img_btn_editPassword;
    ImageButton img_btn_editUser;
    TextView txt_address;
    TextView txt_birth_day;
    TextView txt_gender;
    TextView txt_phone;
    ImageButton img_logout;
    TextView txt_name;
    private View myFragmentView;
    AccountLocalStore accountLocalStore;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        myFragmentView = inflater.inflate(R.layout.activity_fragment_about_me, container, false);
        accountLocalStore = new AccountLocalStore(getActivity());
        img_btn_editPassword = (ImageButton) myFragmentView.findViewById(R.id.img_edit_password);
        img_btn_editUser = (ImageButton) myFragmentView.findViewById(R.id.img_edit_user);
        txt_address = (TextView) myFragmentView.findViewById(R.id.txt_address_about_me);
        txt_birth_day = (TextView) myFragmentView.findViewById(R.id.txt_birth_day_about_me);
        txt_gender = (TextView) myFragmentView.findViewById(R.id.txt_gender);
        txt_phone = (TextView) myFragmentView.findViewById(R.id.txt_phone_number);
        txt_name = (TextView) myFragmentView.findViewById(R.id.txt_full_name);
        imgAvatar = (ImageView) myFragmentView.findViewById(R.id.img_avatar_about_me);
        final Account account = accountLocalStore.GetLoggedInUser();
        txt_address.setText(account.getAddress());
        txt_birth_day.setText(account.getBirth().toString());
        txt_phone.setText(account.getPhone());
        if (account.getGender() == 0) {
            txt_gender.setText("Male");
        }
        if (account.getGender() == 1) {
            txt_gender.setText("Female");
        }
        if (account.getGender() == -1) {
            txt_gender.setText("None");
        }
        txt_name.setText(account.getFirstName() + " " + account.getLastName());
        img_logout = (ImageButton) myFragmentView.findViewById(R.id.img_logout);

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
        img_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Are you sure logout!");
                builder.setCancelable(true);

                builder.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                LogoutAsyncTask logoutAsyncTask = new LogoutAsyncTask();
                                logoutAsyncTask.execute();
                            }
                        });

                builder.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();

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

    public class LogoutAsyncTask extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... voids) {

            APIInterface service = ApiClient.getClient(getActivity()).create(APIInterface.class);
            Call<Boolean> call = service.Logout();

            try {
                call.execute();
                accountLocalStore.ClearUser();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                getActivity().finish();
            } catch (IOException e) {
                accountLocalStore.ClearUser();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                getActivity().finish();
            }


            return null;
        }
    }

}

