package edu.hcmus.minhdat.citytravel.activities;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

//import edu.hcmus.minhdat.citytravel.R;

public class Singup extends Activity {

    Button btnLogin ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        btnLogin = (Button)findViewById(R.id.btnSingin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Singup.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}
