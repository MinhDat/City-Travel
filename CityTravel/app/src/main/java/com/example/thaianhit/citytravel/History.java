package com.example.thaianhit.citytravel;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class History  extends Fragment {
    private RecyclerView recyclerView;
    private CustomRecyclerAdapterHistory adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<DataRecyclerSearch> listData = new ArrayList<DataRecyclerSearch>();
    private View myFragmentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myFragmentView = inflater.inflate(R.layout.activity_history, container, false);
        Toolbar toolbar = (Toolbar) myFragmentView.findViewById(R.id.toolbarHistory);
        toolbar.setTitleTextColor(android.graphics.Color.WHITE);
        toolbar.setTitle("History");
        toolbar.inflateMenu(R.menu.menu_toolbar_save_location);
        recyclerView = (RecyclerView) myFragmentView.findViewById(R.id.recycler);
        setHasOptionsMenu(true);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DataRecyclerSearch a=new DataRecyclerSearch("9.0","861/90C Trần Xuân Soạn, P.Tân Hưng, Q.7",(float)1.5,"Khách sạn","","Khách sạn xanh");
        DataRecyclerSearch b=new DataRecyclerSearch("9.0","861/90C Trần Xuân Soạn, P.Tân Hưng, Q.7",(float)1.5,"Khách sạn","","Khách sạn xanh");
        DataRecyclerSearch c=new DataRecyclerSearch("9.0","861/90C Trần Xuân Soạn, P.Tân Hưng, Q.7",(float)1.5,"Khách sạn","","Khách sạn xanh");
        DataRecyclerSearch d=new DataRecyclerSearch("9.0","861/90C Trần Xuân Soạn, P.Tân Hưng, Q.7",(float)1.5,"Khách sạn","","Khách sạn xanh");
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
