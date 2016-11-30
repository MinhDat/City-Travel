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
//        recyclerView.setHasFixedSize(true);
//
//        // Setting the LayoutManager.
//        layoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);
//        DataRecyclerSearch a=new DataRecyclerSearch("9.0","861/90C Trần Xuân Soạn, P.Tân Hưng, Q.7",(float)1.5,"Khách sạn","","Khách sạn xanh");
//        DataRecyclerSearch b=new DataRecyclerSearch("9.0","861/90C Trần Xuân Soạn, P.Tân Hưng, Q.7",(float)1.5,"Khách sạn","","Khách sạn xanh");
//        DataRecyclerSearch c=new DataRecyclerSearch("9.0","861/90C Trần Xuân Soạn, P.Tân Hưng, Q.7",(float)1.5,"Khách sạn","","Khách sạn xanh");
//        DataRecyclerSearch d=new DataRecyclerSearch("9.0","861/90C Trần Xuân Soạn, P.Tân Hưng, Q.7",(float)1.5,"Khách sạn","","Khách sạn xanh");
//        listData.add(a);
//        listData.add(b);
//        listData.add(c);
//        listData.add(d);
//        // Setting the adapter.
//        adapter = new CustomRecyclerAdapterSearch(listData,getActivity());
//        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
//        recyclerView.setAdapter(adapter);
    return myFragmentView;
    }

}
