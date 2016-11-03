package edu.hcmus.minhdat.citytravel.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


//import edu.hcmus.minhdat.citytravel.R;
//
//
//import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    Button btnSingup;
    Button btnSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnSingup = (Button)findViewById(R.id.btnSingUp);
        btnSignin = (Button)findViewById(R.id.btnSingin);
        btnSingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Singup = new Intent(LoginActivity.this, Singup.class);
                startActivity(Singup);
            }
        });
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signin = new Intent(LoginActivity.this, Manhinhchinh.class);
                startActivity(signin);
            }
        });

        // Set up the login form.

    }

}

