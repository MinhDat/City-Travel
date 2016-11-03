package edu.hcmus.minhdat.citytravel.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//import edu.hcmus.minhdat.citytravel.R;

public class ResetPassword extends AppCompatActivity {

    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        bt = (Button)findViewById(R.id.btnResetPassword);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(ResetPassword.this,LoginActivity.class);
                startActivity(a);
            }
        });
    }
}
