package com.example.thaianhit.citytravel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class FragmentHome extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ImageView img_backround;
    private FloatingActionButton Search;
    Toolbar toolbar;
    private View myFragmentView;
    private ProgressDialog pDialog;
    private static final int REQUEST_CODE = 10;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myFragmentView = inflater.inflate(R.layout.activity_fragment_home, container, false);
        toolbar = (Toolbar) myFragmentView.findViewById(R.id.toolbarHome);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        img_backround = (ImageView) myFragmentView.findViewById(R.id.img_backround);
        Search = (FloatingActionButton) myFragmentView.findViewById(R.id.Search);
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tempt = new Intent(getActivity(), FragmentSearchLocations.class);
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
        recyclerView = (RecyclerView) myFragmentView.findViewById(R.id.recycler);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        APIInterface apiService = ApiClient.getClient(getActivity()).create(APIInterface.class);
        Call<List<Category>> call = apiService.getCategory();
        CategoryAsyncTask categoryAsyncTask = new CategoryAsyncTask();
        categoryAsyncTask.execute(call);

        return myFragmentView;
    }

    public class CategoryAsyncTask extends AsyncTask<Call, Void, List<Category>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected void onPostExecute(final List<Category> categories) {

            super.onPostExecute(categories);
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }
            if (categories.size() != 0) {
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(new CustomRecyclerAdapterHome(categories, getActivity().getApplicationContext()));
                recyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(getActivity(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                            @Override public void onItemClick(View view, int position) {
                               Intent intent = new Intent(getActivity(),FragmentSearchLocations.class);
                                intent.putExtra("category",categories.get(position).getName());
                                startActivityForResult(intent, REQUEST_CODE);
                            }

                            @Override public void onLongItemClick(View view, int position) {
                                // do whatever
                            }
                        })
                );
            } else {
                Toast.makeText(getActivity(), "Load fail", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        protected List<Category> doInBackground(Call... calls) {
            List<Category> list = new ArrayList<Category>();
            try {
                Call<List<Category>> call = calls[0];
                Response<List<Category>> response = call.execute();
                list.addAll(response.body());

                return list;
            } catch (Exception e) {
                Log.d("fffff", e.toString());
                return list;
            }

        }
    }
}
