package com.example.thaianhit.citytravel;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.StringLoader;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.thaianhit.citytravel.LoginActivity.string_email;

public class EditProfile extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    @Bind(R.id.tbEdit)
    Toolbar tbEdit;
    @Bind(R.id.edtAddress)
    EditText edtAddress;
    @Bind(R.id.edt_gender)
    EditText edtGender;
    @Bind(R.id.edt_birthday)
    EditText edtBirthday;
    @Bind(R.id.edt_PhoneNumber)
    EditText edtPhoneNumber;
    @Bind(R.id.ivEditProfile)
    ImageView ivEditProfile;
    CharSequence[] values = {" Male ", " Female ", " Non "};
    AlertDialog alertDialog_birthday;
    int id_choose = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        Glide.with(this).load(R.drawable.anh).asBitmap().centerCrop().into(new BitmapImageViewTarget(ivEditProfile) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                ivEditProfile.setImageDrawable(circularBitmapDrawable);
            }
        });

        setSupportActionBar(tbEdit);
        tbEdit.setTitleTextColor(android.graphics.Color.WHITE);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editprofile, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_save:
                edit_Profile();
                break;
        }
        return super.onOptionsItemSelected(item);
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
        edtBirthday.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
    }

    public void CreateAlertDialogWithRadioButtonGroup() {

        AlertDialog.Builder builder = new AlertDialog.Builder(EditProfile.this);
        builder.setTitle("Gender");
        builder.setSingleChoiceItems(values, id_choose, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                id_choose = item;
                switch (item) {
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

    public void edit_Profile() {
        APIInterface service = ApiClient.getClient().create(APIInterface.class);
        AccountLocalStore prof = new AccountLocalStore(EditProfile.this);
        Account account_local = prof.GetLoggedInUser();
        String email = account_local.getEmail();
        Log.d("ffff",email.toString()) ;
        String picture = account_local.getPicture();
        String address = edtAddress.getText().toString();
        int gender = 0;
        String gen = edtGender.getText().toString();
        if (gen == "Male") {
            gender = 0;
        } else if (gen == "Female") {
            gender = 1;
        } else {
            gender = -1;
        }
        String birth = edtBirthday.getText().toString();
        String phone = edtPhoneNumber.getText().toString();
        String firsrName = account_local.getFirsrName();
        String lastName = account_local.getLastName();
        String password = account_local.getPassword();
        Account tk = new Account(email, picture, address, birth, gender, phone, firsrName, lastName, password);
        Call<Boolean> call = service.editProfile(tk);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    if (response.body() == true) {
                        Toast.makeText(EditProfile.this, "Edit success!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(EditProfile.this, "Database Error!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(EditProfile.this, "Edit Fail", Toast.LENGTH_LONG).show();
            }
        });
    }
}
