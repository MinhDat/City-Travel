package com.example.thaianhit.citytravel;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.io.IOException;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

public class EditProfile extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    ProgressDialog progressDialog;
    @Bind(R.id.tbEdit)
    Toolbar tbEdit;
    @Bind(R.id.edt_first_name)
    EditText edt_firstname;
    @Bind(R.id.edt_last_name)
    EditText edt_lastname;
    @Bind(R.id.edt_phone)
    EditText edt_phone;
    @Bind(R.id.edtAddress)
    EditText edtAddress;
    @Bind(R.id.edt_gender)
    EditText edtGender;
    @Bind(R.id.edt_birthday)
    EditText edtBirthday;
    @Bind(R.id.ivEditProfile)
    ImageView ivEditProfile;
    CharSequence[] values = {" Male "," Female "," Non "};
    AlertDialog alertDialog_birthday;
    int id_choose =0;
    Account account = null;
    AccountLocalStore accountLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
         accountLocalStore = new AccountLocalStore(EditProfile.this);
        account = accountLocalStore.GetLoggedInUser();
        Glide.with(this).load(R.drawable.anh).asBitmap().centerCrop().into(new BitmapImageViewTarget(ivEditProfile)
        {
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
        edtAddress.setText(account.getAddress());
        edtBirthday.setText(account.getBirth());
        edt_phone.setText(account.getPhone());
        edt_firstname.setText(account.getFirstName());
        edt_lastname.setText(account.getLastName());
        if (account.getGender() == 0) {
            edtGender.setText("Male");
        }
        if (account.getGender() == 1) {
            edtGender.setText("Female");
        }
        if(account.getGender() == -1)
        {
            edtGender.setText("None");
        }
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        int gender = -1;
        if(edtGender.getText().equals("Male"))
        {
            gender = 0;
        }
        if(edtGender.getText().equals("Female"))
        {
            gender = 1;
        }
        switch (id){
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_save:
                Account accountedit = new Account(account.getEmail(),account.getPicture(),edtAddress.getText().toString(),edtBirthday.getText().toString(),gender,edt_phone.getText().toString(),edt_firstname.getText().toString(),edt_lastname.getText().toString(),account.getPassword(),account.getRole());
                accountLocalStore.StoreUserData(accountedit);
                EditAsyncTask editAsyncTask = new EditAsyncTask();
                APIInterface service = ApiClient.getClient(EditProfile.this).create(APIInterface.class);
                Call<Boolean> call = service.editProfile(accountedit);
                editAsyncTask.execute(call);
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
                        edtGender.setText("Female");
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
    public class EditAsyncTask extends AsyncTask<Call,Void,Boolean>
    {

        @Override
        protected void onPreExecute()
        {
            progressDialog = new ProgressDialog(EditProfile.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Changing...");
            progressDialog.show();
            super.onPreExecute();
        }
        @Override
        protected Boolean doInBackground(Call... calls) {
            try {
                Call<Boolean> call = calls[0];
                Response<Boolean> response = null;
                response = call.execute();
                return response.body();
            } catch (IOException e) {
                return false;
            }
        }
        @Override
        protected void onPostExecute(Boolean aBoolean)
        {
            super.onPostExecute(aBoolean);
            if(progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            if(aBoolean == true)
            {
                Toast.makeText(EditProfile.this,"Change success!",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(EditProfile.this,"Change fail!",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
