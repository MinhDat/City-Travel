package com.example.thaianhit.citytravel;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {
    android.app.FragmentManager fragmentManager;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getFragmentManager();
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId)
            {
                if (tabId == R.id.btn_search)
                {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_switch,new fragment_home(), "fragmentA");

                    fragmentTransaction.addToBackStack("fragmentA");

                    fragmentTransaction.commit();
                }
                if(tabId==R.id.btn_list)
                {

                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_switch,new SaveLocation(), "fragmentB");

                    fragmentTransaction.addToBackStack("fragmentB");

                    fragmentTransaction.commit();

                }
                if(tabId==R.id.btn_about_me)
                {

                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_switch,new FragmentAboutMe(), "fragmentD");

                    fragmentTransaction.addToBackStack("fragmentD");

                    fragmentTransaction.commit();

                }
            }
        });
    }

}
