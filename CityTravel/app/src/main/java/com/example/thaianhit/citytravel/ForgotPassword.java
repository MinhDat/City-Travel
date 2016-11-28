package com.example.thaianhit.citytravel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ForgotPassword extends AppCompatActivity
{
    ProgressDialog pDialog;
    @Bind(R.id.bgForgotPassword)
    LinearLayout linearLayoutBackground;
    @Bind(R.id.btn_submitforgot)
    Button btnSubmitForgot;
    @Bind(R.id.edt_forgot_email)
    EditText _emailText;
    private static final int REQUEST_SIGNUP = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        btnSubmitForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });
        Glide.with(this).load(R.drawable.background).asBitmap().into(new SimpleTarget<Bitmap>(400, 500) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable drawable = new BitmapDrawable(resource);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    linearLayoutBackground.setBackground(drawable);
                }
            }
        });
    }
    public void submit()
    {
        if (!validate()) {
            onSubmitFailed();
            return;
        }
        APIInterface service = ApiClient.getClient(ForgotPassword.this).create(APIInterface.class);
        Call<Boolean> call = service.Forgetpassword(_emailText.getText().toString());
        SubmitAsyncTask submitAsyncTask = new SubmitAsyncTask();
        submitAsyncTask.execute(call);
    }


    public void onSubmitFailed() {
        Toast.makeText(getBaseContext(), "Submit failed", Toast.LENGTH_LONG).show();
    }

    public boolean validate() {
        boolean valid = true;
        String email = _emailText.getText().toString();
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else
        {
            _emailText.setError(null);
        }
        return valid;
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivityForResult(intent, REQUEST_SIGNUP);
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        super.onBackPressed();
    }
    public class SubmitAsyncTask extends AsyncTask<Call,Void,Boolean>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(ForgotPassword.this,
                    R.style.AppTheme_Dark_Dialog);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean)
        {
            super.onPostExecute(aBoolean);
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }
            try {
                if (aBoolean == true)
                {
                    Toast.makeText(ForgotPassword.this, "Submit success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ForgotPassword.this, LoginActivity.class);
                    startActivityForResult(intent, REQUEST_SIGNUP);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    finish();
                } else {
                    Toast.makeText(ForgotPassword.this, "Submit fail", Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {
                Toast.makeText(ForgotPassword.this, "Submit fail", Toast.LENGTH_SHORT).show();
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
