package edu.hcmus.minhdat.citytravel.activities;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by T.N on 11/3/2016.
 */

public class Manhinhchinh extends Activity{
    ImageButton btnSave,btnHistory, btnUserInfo, eSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* btnSave = (Button)findViewById(R.id.ibtnSaveLocation);
        btnSave.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           Intent intent = new Intent(Manhinhchinh.this, SaveLocation.class);
                                           startActivity(intent);
                                       }
                                   }
        );*/
        btnSave = (ImageButton)findViewById(R.id.ibtnSaveLocation);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Manhinhchinh.this, SaveLocation.class);
                startActivity(intent);
            }
        });

        btnHistory = (ImageButton)findViewById(R.id.ibtnHistory);
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Manhinhchinh.this, History.class);
                startActivity(intent);
            }
        });

        btnUserInfo = (ImageButton)findViewById(R.id.ibtnUserInfo);
        btnUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(Manhinhchinh.this, UserInfo.class);
                startActivity(intent3);
            }
        });
        eSearch = (ImageButton) findViewById(R.id.ibtnSearch);
        eSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(Manhinhchinh.this, timkiem.class);
                startActivity(intent3);
            }
        });
    }


}
