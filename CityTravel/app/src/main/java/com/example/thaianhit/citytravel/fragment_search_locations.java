package com.example.thaianhit.citytravel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

public class fragment_search_locations extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CustomRecyclerAdapterSearch adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageView img_backround;
    private List<DataRecyclerSearch> listData = new ArrayList<DataRecyclerSearch>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_search_locations);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        // If the size of views will not change as the data changes.
        recyclerView.setHasFixedSize(true);

        // Setting the LayoutManager.
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DataRecyclerSearch a=new DataRecyclerSearch("Bệnh viện","Nodescription","10");
        DataRecyclerSearch b=new DataRecyclerSearch("ATM","Nodescription","10");
        DataRecyclerSearch c=new DataRecyclerSearch("Trường học","Nodescription","10");
        DataRecyclerSearch d=new DataRecyclerSearch("Khách sạn","Nodescription","10");
        listData.add(a);
        listData.add(b);
        listData.add(c);
        listData.add(d);
        // Setting the adapter.
        adapter = new CustomRecyclerAdapterSearch(listData,fragment_search_locations.this);
        recyclerView.setAdapter(adapter);
    }

}
