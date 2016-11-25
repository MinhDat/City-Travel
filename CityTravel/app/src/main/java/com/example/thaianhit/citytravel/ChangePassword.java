package com.example.thaianhit.citytravel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.thaianhit.citytravel.LoginActivity.string_email;

public class ChangePassword extends AppCompatActivity {
    @Bind(R.id.tbChangePass)
    Toolbar tbChangePass;
    @Bind(R.id.edt_oldpassword)
    EditText edt_oldpassword;
    @Bind(R.id.edt_newpassword)
    EditText edt_newpassword;
    @Bind(R.id.edt_repassword)
    EditText edt_repassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        setSupportActionBar(tbChangePass);
        tbChangePass.setTitleTextColor(android.graphics.Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Change password");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_editprofile, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnSetting:
                ChangePass();
                break;
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    public void ChangePass() {

        if (!validate())
        {
            onChangePassFailed();
            return;
        }
        final ProgressDialog progressDialog = new ProgressDialog(ChangePassword.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Submit...");
        progressDialog.show();


        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        Change();
                        progressDialog.dismiss();
                    }
                }, 1000);
    }
    public void Change()
    {
        APIInterface service = ApiClient.getClient().create(APIInterface.class);
        Call<Boolean> call = service.ChangePassword(string_email, edt_oldpassword.getText().toString(),edt_newpassword.getText().toString());
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.isSuccessful())
                {
                    if(response.body() == true)
                    {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(ChangePassword.this, "Password old not valid", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(ChangePassword.this, "ChangPassword Fail", Toast.LENGTH_LONG).show();
            }
        });

    }
    public boolean validate() {
        boolean valid = true;

        String newpassword = edt_newpassword.getText().toString();
        String oldpassword = edt_oldpassword.getText().toString();
        String confirmpass=edt_repassword.getText().toString();
        if (oldpassword.isEmpty() ) {
            edt_oldpassword.setError("Old password not null");
        }
        else
        {
            edt_oldpassword.setError(null);
        }
        if (newpassword.isEmpty() || newpassword.length() < 4 || newpassword.length() > 10) {
            edt_newpassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            edt_newpassword.setError(null);
        }
        if (confirmpass.equals(newpassword)) {
            edt_repassword.setError(null);
        } else {
            edt_repassword.setError("Confirmpassword is not same Newpassword");
            valid = false;

        }
        return valid;
    }
    public void onChangePassFailed() {
        Toast.makeText(getBaseContext(), "ChangPassword failed", Toast.LENGTH_LONG).show();

    }

}
