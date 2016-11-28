package com.example.thaianhit.citytravel;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;


public class ChangePassword extends AppCompatActivity {
    ProgressDialog  progressDialog;
    @Bind(R.id.tbChangePass)
    Toolbar tbChangePass;
    @Bind(R.id.edt_oldpassword)
    EditText edt_oldpassword;
    @Bind(R.id.edt_newpassword)
    EditText edt_newpassword;
    @Bind(R.id.edt_repassword)
    EditText edt_repassword;
    AccountLocalStore accountLocalStore;
    Account account = null;
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
      accountLocalStore = new AccountLocalStore(ChangePassword.this);
      account =  accountLocalStore.GetLoggedInUser();
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
            case R.id.action_save:
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
        ChangeAsyncTask asyncTask = new ChangeAsyncTask();
        APIInterface service = ApiClient.getClient(ChangePassword.this).create(APIInterface.class);
        Call<Boolean> call = service.ChangePassword(account.getEmail(), edt_oldpassword.getText().toString(),edt_newpassword.getText().toString());
        asyncTask.execute(call);

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
    public class ChangeAsyncTask extends AsyncTask<Call,Void,Boolean>
    {


        @Override
        protected void onPreExecute()
        {
            progressDialog = new ProgressDialog(ChangePassword.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Changing...");
            progressDialog.show();
            super.onPreExecute();
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
                ShowDialog();
            }
            else
            {
                Toast.makeText(ChangePassword.this,"Change fail!",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected Boolean doInBackground(Call... calls)
        {
            try {
                Call<Boolean> call = calls[0];
                Response<Boolean> response = null;
                response = call.execute();
                return response.body();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }
    public void ShowDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Change password success!");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id)
            {
                onBackPressed();
            }
        });
        builder.create();
        builder.show();
    }

}
