package com.example.thaianhit.citytravel;

import android.app.DatePickerDialog;

import android.content.DialogInterface;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EditProfile extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    @Bind(R.id.tbEdit)
    Toolbar tbEdit;
    @Bind(R.id.edtAddress)
    EditText edtAddress;
    @Bind(R.id.edt_gender)
    EditText edtGender;
    @Bind(R.id.edt_birthday)
    EditText edtBirthday;
    @Bind(R.id.ivEditProfile)
    ImageView ivEditProfile;
    CharSequence[] values = {" Male "," Famale "," Non "};
    AlertDialog alertDialog_birthday;
    int id_choose =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        Glide.with(this).load(R.drawable.anh)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivEditProfile);
        setSupportActionBar(tbEdit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Edit profile");
        edtGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAlertDialogWithRadioButtonGroup();
            }
        });
        edtBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(view);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_editprofile, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // store the values selected into a Calendar instance
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, monthOfYear);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        edtBirthday.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
    }
    public void CreateAlertDialogWithRadioButtonGroup(){

        AlertDialog.Builder builder = new AlertDialog.Builder(EditProfile.this);
        builder.setTitle("Gender");
        builder.setSingleChoiceItems(values, id_choose, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item)
            {
                id_choose = item;
                switch(item)
                {
                    case 0:
                        edtGender.setText("Male");
                        break;
                    case 1:
                        edtGender.setText("Famale");
                        break;
                    case 2:
                        edtGender.setText("Non");
                        break;
                }
                alertDialog_birthday.dismiss();
            }
        });
        alertDialog_birthday = builder.create();
        alertDialog_birthday.show();

    }
}
