package com.example.thaianhit.citytravel;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class History  extends Fragment {
    private RecyclerView recyclerView;
    private CustomRecyclerAdapterHistory adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<DataRecyclerSearch> listData = new ArrayList<DataRecyclerSearch>();
    private View myFragmentView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

//        HistoryLikeDAO historyLikeDAO = new HistoryLikeDAO(this);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myFragmentView = inflater.inflate(R.layout.activity_history, container, false);
        Toolbar toolbar = (Toolbar) myFragmentView.findViewById(R.id.toolbarSave);
        toolbar.setTitleTextColor(android.graphics.Color.WHITE);
        toolbar.setTitle("History");
        toolbar.inflateMenu(R.menu.menu_toolbar_save_location);
        recyclerView = (RecyclerView) myFragmentView.findViewById(R.id.recycler);
        setHasOptionsMenu(true);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DataRecyclerSearch a=new DataRecyclerSearch("Bệnh viện","Nodescription","10");
        DataRecyclerSearch b=new DataRecyclerSearch("ATM","Nodescription","10");
        DataRecyclerSearch c=new DataRecyclerSearch("Trường học","Nodescription","10");
        DataRecyclerSearch d=new DataRecyclerSearch("Khách sạn","Nodescription","10");
        listData.add(a);
        listData.add(b);
        listData.add(c);
        listData.add(d);
        adapter = new CustomRecyclerAdapterHistory(listData,getActivity());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        return myFragmentView;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miCompose :
            {
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
