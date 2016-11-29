package com.example.thaianhit.citytravel;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.SearchView;
import android.view.MenuItem;

import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;


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
    boolean t = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_search_locations);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        floatingSearchView = (FloatingSearchView) findViewById(R.id.floating_search_view);
        floatingSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.action_settings) {
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
        floatingSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {

                Toast.makeText(FragmentSearchLocations.this, newQuery, Toast.LENGTH_SHORT).show();
            }
        });
        floatingSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

            }

            @Override
            public void onSearchAction(String currentQuery) {
                Toast.makeText(FragmentSearchLocations.this, currentQuery, Toast.LENGTH_SHORT).show();
            }
        });


        // Setting the LayoutManager.
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DataRecyclerSearch a=new DataRecyclerSearch("9.0","861/90C Trần Xuân Soạn, P.Tân Hưng, Q.7",(float)1.5,"Khách sạn","","Khách sạn xanh");
        DataRecyclerSearch b=new DataRecyclerSearch("9.0","861/90C Trần Xuân Soạn, P.Tân Hưng, Q.7",(float)1.5,"Khách sạn","","Khách sạn xanh");
        DataRecyclerSearch c=new DataRecyclerSearch("9.0","861/90C Trần Xuân Soạn, P.Tân Hưng, Q.7",(float)1.5,"Khách sạn","","Khách sạn xanh");
        DataRecyclerSearch d=new DataRecyclerSearch("9.0","861/90C Trần Xuân Soạn, P.Tân Hưng, Q.7",(float)1.5,"Khách sạn","","Khách sạn xanh");
        listData.add(a);
        listData.add(b);
        listData.add(c);
        listData.add(d);
        // Setting the adapter.
        adapter = new CustomRecyclerAdapterSearch(listData, FragmentSearchLocations.this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    public void alertFormElements() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FragmentSearchLocations.this);
        builder.setTitle("Setting");
        builder.setMultiChoiceItems(values, bl, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {

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
