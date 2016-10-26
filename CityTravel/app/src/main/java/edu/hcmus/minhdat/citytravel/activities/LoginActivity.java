package edu.hcmus.minhdat.citytravel.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import edu.hcmus.minhdat.citytravel.R;


import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    Button btnSingup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnSingup = (Button)findViewById(R.id.btnSingUp);
        btnSingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Singup = new Intent(LoginActivity.this, Singup.class);
                startActivity(Singup);
            }
        });
        // Set up the login form.

    }

}

