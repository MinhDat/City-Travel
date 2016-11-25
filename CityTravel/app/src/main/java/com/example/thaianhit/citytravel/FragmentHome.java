package com.example.thaianhit.citytravel;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHome extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ImageView img_backround;
    private FloatingActionButton Search;
    Toolbar toolbar;
    private View myFragmentView;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        myFragmentView = inflater.inflate(R.layout.activity_fragment_home, container, false);
        toolbar = (Toolbar)myFragmentView.findViewById(R.id.toolbarHome);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        img_backround=(ImageView)myFragmentView.findViewById(R.id.img_backround);
        Search=(FloatingActionButton)myFragmentView.findViewById(R.id.Search);
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tempt=new Intent(getActivity(),FragmentSearchLocations.class);
                getActivity().startActivity(tempt);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                getActivity().finish();
            }
        });
        Glide.with(this).load(R.drawable.logo)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img_backround);

        recyclerView = (RecyclerView)myFragmentView.findViewById(R.id.recycler);
        recyclerView.setNestedScrollingEnabled(false);
        // If the size of views will not change as the data changes.
        recyclerView.setHasFixedSize(true);

        // Setting the LayoutManager.
        layoutManager = new LinearLayoutManager(getActivity());

        APIInterface apiService = ApiClient.getClient().create(APIInterface.class);
        Call<List<Category>> call = apiService.getCategory();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response)
            {
                List<Category> movies =new ArrayList<Category>();
                movies.addAll(response.body()) ;
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(new CustomRecyclerAdapterHome(movies, getActivity().getApplicationContext()));
                Log.d("TAG1",response.body().toString());
            }
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("TAG1","2");
            }
        });
        return myFragmentView;
    }



}
