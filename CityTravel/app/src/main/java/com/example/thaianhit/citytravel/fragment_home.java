package com.example.thaianhit.citytravel;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

public class fragment_home extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CustomRecyclerAdapterHome adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageView img_backround;
    private List<DataRecyclerHome> listData = new ArrayList<DataRecyclerHome>();
    private FloatingActionButton Search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        img_backround=(ImageView)findViewById(R.id.img_backround);
        Search=(FloatingActionButton)findViewById(R.id.Search);

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tempt=new Intent(fragment_home.this,fragment_search_locations.class);
                startActivity(tempt);
            }
        });
        Glide.with(this).load(R.drawable.logo)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img_backround);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        // If the size of views will not change as the data changes.
        recyclerView.setHasFixedSize(true);

        // Setting the LayoutManager.
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DataRecyclerHome a=new DataRecyclerHome("Bệnh viện","Nodescription");
        DataRecyclerHome b=new DataRecyclerHome("ATM","Nodescription");
        DataRecyclerHome c=new DataRecyclerHome("Trường học","Nodescription");
        DataRecyclerHome d=new DataRecyclerHome("Khách sạn","Nodescription");
        listData.add(a);
        listData.add(b);
        listData.add(c);
        listData.add(d);
        // Setting the adapter.
        adapter = new CustomRecyclerAdapterHome(listData,fragment_home.this);
        recyclerView.setAdapter(adapter);
    }

}
