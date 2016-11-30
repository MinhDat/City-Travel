package com.example.thaianhit.citytravel;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.example.thaianhit.citytravel._class.PlaceDetail;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;

public class FragmentSearchLocations extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingSearchView floatingSearchView;
    AlertDialog alertDialog_setting;
    boolean bl[] = new boolean[2];
    CharSequence[] values = {"Near me", "Rate"};
    CharSequence[] setting = {"Search as you enter the keyword"};
    boolean t = true;
    private ProgressDialog pDialog;
    private static final int REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_search_locations);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        floatingSearchView = (FloatingSearchView) findViewById(R.id.floating_search_view);
        Bundle extras = getIntent().getExtras();
        String value1;

        if (extras != null) {
            value1 = extras.getString("category");
            floatingSearchView.setSearchText(value1);
            SearchAsyncTask searchAsyncTask = new SearchAsyncTask(FragmentSearchLocations.this);
            APIInterface apiService = ApiClient.getClient(FragmentSearchLocations.this).create(APIInterface.class);
            Call<List<PlaceDetail>> call = apiService.searchPlace(value1
            );
            searchAsyncTask.execute(call);
        }

        floatingSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.action_filter) {
                    alertFilter();
                }
                if (item.getItemId() == R.id.action_settings)
                {
                    alertSetting();
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

        floatingSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

            }

            @Override
            public void onSearchAction(String currentQuery) {
                SearchAsyncTask searchAsyncTask = new SearchAsyncTask(FragmentSearchLocations.this);
                APIInterface apiService = ApiClient.getClient(FragmentSearchLocations.this).create(APIInterface.class);
                Call<List<PlaceDetail>> call = apiService.searchPlace(currentQuery
                );
                searchAsyncTask.execute(call);
            }
        });
        // Setting the LayoutManager.
        layoutManager = new LinearLayoutManager(this);


    }
    public void alertSetting() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FragmentSearchLocations.this);
        builder.setTitle("Filter");
        builder.setMultiChoiceItems(setting, bl, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {

            }

        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                alertDialog_setting.dismiss();
            }
        });
        alertDialog_setting = builder.create();
        alertDialog_setting.show();
    }
    public void alertFilter() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FragmentSearchLocations.this);
        builder.setTitle("Filter");
        builder.setMultiChoiceItems(values, bl, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {

            }

        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                alertDialog_setting.dismiss();
            }
        });
        alertDialog_setting = builder.create();
        alertDialog_setting.show();
    }

    public class SearchAsyncTask extends AsyncTask<Call, Void, List<PlaceDetail>> {

        Context context;

        public SearchAsyncTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(FragmentSearchLocations.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected void onPostExecute(final List<PlaceDetail> placeDetails) {

            super.onPostExecute(placeDetails);
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }
            if (placeDetails.size() != 0) {
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.addItemDecoration(new DividerItemDecoration(FragmentSearchLocations.this, LinearLayoutManager.VERTICAL));
                recyclerView.setAdapter(new CustomRecyclerAdapterSearch(placeDetails, context));
                recyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(FragmentSearchLocations.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent(FragmentSearchLocations.this, DetailServices.class);
                                intent.putExtra("id", placeDetails.get(position).getId_placedetail());
                                startActivityForResult(intent, REQUEST_CODE);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                // do whatever
                            }
                        })
                );

            } else {
                Toast.makeText(context, "Load fail", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        protected List<PlaceDetail> doInBackground(Call... calls) {
            List<PlaceDetail> list = new ArrayList<PlaceDetail>();
            try {
                Call<List<PlaceDetail>> call = calls[0];
                Response<List<PlaceDetail>> response = call.execute();
                list.addAll(response.body());

                return list;
            } catch (Exception e) {
                Log.d("fffff", e.toString());
                return list;
            }

        }
    }

}
