package com.example.thaianhit.citytravel;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class SaveLocation  extends Fragment {
    private RecyclerView recyclerView;
    private CustomRecyclerAdapterSearch adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageView img_backround;
    private List<DataRecyclerSearch> listData = new ArrayList<DataRecyclerSearch>();
    private View myFragmentView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myFragmentView = inflater.inflate(R.layout.activity_save_location, container, false);
        Toolbar toolbar = (Toolbar) myFragmentView.findViewById(R.id.toolbarSave);
        toolbar.setTitleTextColor(android.graphics.Color.WHITE);
        toolbar.setTitle("Saved");
          toolbar.inflateMenu(R.menu.menu_toolbar_save_location);
        recyclerView = (RecyclerView) myFragmentView.findViewById(R.id.recycler);

        // If the size of views will not change as the data changes.
        recyclerView.setHasFixedSize(true);

        // Setting the LayoutManager.
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
        // Setting the adapter.
        adapter = new CustomRecyclerAdapterSearch(listData,getActivity());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        return myFragmentView;
    }

}
