package com.example.thaianhit.citytravel;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import retrofit2.Call;
import retrofit2.Response;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Calendar c;
    ProgressDialog pDialog;
    private static final int REQUEST_SIGNUP = 0;
    CharSequence[] values = {" None ", " Male ", " Female "};
    AlertDialog alertDialog_birthday;
    int id_choose = -1;
    @Bind(R.id.input_firstname)
    EditText _firstnameText;
    @Bind(R.id.input_lastname)
    EditText _lastnameText;
    @Bind(R.id.input_email)
    EditText _emailText;
    @Bind(R.id.input_password)
    EditText _passwordText;
    @Bind(R.id.input_reEnterPassword)
    EditText _reEnterPasswordText;
    @Bind(R.id.btn_signup)
    Button _signupButton;
    @Bind(R.id.link_login)
    TextView _loginLink;
    @Bind(R.id.bgSignup)
    ScrollView scrollView;
    @Bind(R.id.input_gender)
    TextView tvGender;
    @Bind(R.id.input_birthday)
    TextView tvBirthday;
    public static final String BASE_URL = "http://citytravel-2.apphb.com/api/";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        tvBirthday.setText(13 + "/" + 6 + "/" + 1995);
        Glide.with(this).load(R.drawable.background).asBitmap().into(new SimpleTarget<Bitmap>(400, 500) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable drawable = new BitmapDrawable(resource);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    scrollView.setBackground(drawable);
                }
            }
        });
        tvGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAlertDialogWithRadioButtonGroup();
            }
        });

        tvBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(view);
            }
        });
        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void CreateAlertDialogWithRadioButtonGroup() {

        AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
        builder.setTitle("Gender");
        builder.setSingleChoiceItems(values, id_choose, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                id_choose = item;
                switch (item) {
                    case 0:
                        tvGender.setText("None");
                        break;
                    case 1:
                        tvGender.setText("Male");
                        break;
                    case 2:
                        tvGender.setText("Female");
                        break;
                }
                alertDialog_birthday.dismiss();
            }
        });
        alertDialog_birthday = builder.create();
        alertDialog_birthday.show();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivityForResult(intent, REQUEST_SIGNUP);
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        super.onBackPressed();
    }

    public void signup() {
        if (!validate()) {
            Toast.makeText(getBaseContext(), "Signup failed!", Toast.LENGTH_SHORT).show();
            return;
        }
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        try {
            date = df.parse(tvBirthday.toString());
        } catch (Exception e) {
        }
        int gender =-1;
        if(tvGender.getText().equals("Male"))
        {
            gender = 0;
        }
        if(tvGender.getText().equals("Female"))
        {
            gender = -1;
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIInterface service = retrofit.create(APIInterface.class);
        SignupAsyntask signupAsyntask = new SignupAsyntask();
        Call<Boolean> call = service.postAccount(new Account(_emailText.getText().toString(), "", "", date.toString(), gender, "", _firstnameText.getText().toString(), _lastnameText.getText().toString(), _passwordText.getText().toString(),"Customer"));
        signupAsyntask.execute(call);
    }

    public boolean validate() {
        boolean valid = true;
        String lastname = _lastnameText.getText().toString();
        String firstname = _firstnameText.getText().toString();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();

        if (firstname.isEmpty() || firstname.length() < 3) {
            _firstnameText.setError("at least 3 characters");
            valid = false;
        } else {
            _firstnameText.setError(null);
        }
        if (lastname.isEmpty() || lastname.length() < 3) {
            _lastnameText.setError("at least 3 characters");
            valid = false;
        } else {
            _lastnameText.setError(null);
        }
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            _reEnterPasswordText.setError("Password Do not match");
            valid = false;
        } else {
            _reEnterPasswordText.setError(null);
        }

        return valid;
    }


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, monthOfYear);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        tvBirthday.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
    }
    public class SignupAsyntask extends AsyncTask<Call, Void, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(SignupActivity.this,
                    R.style.AppTheme_Dark_Dialog);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }
            try {
                Log.d("Gggg",aBoolean.toString());
                if (aBoolean == true)
                {
                    Toast.makeText(SignupActivity.this, "Sign up success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivityForResult(intent, REQUEST_SIGNUP);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    finish();
                } else {
                    Toast.makeText(SignupActivity.this, "Sign up fail", Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {
                Log.d("FFF",e.toString());
                Toast.makeText(SignupActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected Boolean doInBackground(Call... calls) {
            try {
                Call<Boolean> call = calls[0];
                Response<Boolean> response = null;
                response = call.execute();
                return response.body();
            } catch (Exception e) {
                Log.d("ffff",e.toString());
                return false;
            }

        }

    }
}