package com.example.thaianhit.citytravel;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.MenuItem;

import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;


import java.util.ArrayList;
import java.util.List;

public class FragmentSearchLocations extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CustomRecyclerAdapterSearch adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingSearchView floatingSearchView;
    AlertDialog alertDialog_setting;
    private List<DataRecyclerSearch> listData = new ArrayList<DataRecyclerSearch>();
    boolean bl[] = new boolean[2];
    CharSequence[] values = {"Near me", "Rate"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_search_locations);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        floatingSearchView = (FloatingSearchView) findViewById(R.id.floating_search_view);
        floatingSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener()
        {
            @Override
            public void onActionMenuItemSelected(MenuItem item)
            {
                if(item.getItemId() == R.id.action_settings)
                {
                    alertFormElements();
                }
            }
        });
        floatingSearchView.setOnHomeActionClickListener(new FloatingSearchView.OnHomeActionClickListener() {
            @Override
            public void onHomeClicked() {
                Intent intent = new Intent(FragmentSearchLocations.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                finish();
            }
        });


        // Setting the LayoutManager.
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DataRecyclerSearch a = new DataRecyclerSearch("Bệnh viện", "Nodescription", "10");
        DataRecyclerSearch b = new DataRecyclerSearch("ATM", "Nodescription", "10");
        DataRecyclerSearch c = new DataRecyclerSearch("Trường học", "Nodescription", "10");
        DataRecyclerSearch d = new DataRecyclerSearch("Khách sạn", "Nodescription", "10");
        listData.add(a);
        listData.add(b);
        listData.add(c);
        listData.add(d);
        // Setting the adapter.
        adapter = new CustomRecyclerAdapterSearch(listData,FragmentSearchLocations.this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    public void alertFormElements() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FragmentSearchLocations.this);
        builder.setTitle("Setting");
        builder.setMultiChoiceItems(values, bl, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b)
            {

            }

        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when click positive button
                alertDialog_setting.dismiss();
            }
        });
        alertDialog_setting = builder.create();
        alertDialog_setting.show();
    }
}
