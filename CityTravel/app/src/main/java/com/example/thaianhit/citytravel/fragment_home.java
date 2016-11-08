package com.example.thaianhit.citytravel;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

public class fragment_home extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CustomRecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageView img_backround;
    private List<DataRecycler> listData = new ArrayList<DataRecycler>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        img_backround=(ImageView)findViewById(R.id.img_backround);
        Glide.with(this).load(R.drawable.logo)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img_backround);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        // If the size of views will not change as the data changes.
        recyclerView.setHasFixedSize(true);

        // Setting the LayoutManager.
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DataRecycler a=new DataRecycler("Bệnh viện","Nodescription");
        DataRecycler b=new DataRecycler("ATM","Nodescription");
        DataRecycler c=new DataRecycler("Trường học","Nodescription");
        DataRecycler d=new DataRecycler("Khách sạn","Nodescription");
        listData.add(a);
        listData.add(b);
        listData.add(c);
        listData.add(d);
        // Setting the adapter.
        adapter = new CustomRecyclerAdapter(listData,fragment_home.this);
        recyclerView.setAdapter(adapter);
    }

}
