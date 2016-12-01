package com.example.thaianhit.citytravel;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class FragmentSearchLocations extends AppCompatActivity {


    private FloatingSearchView floatingSearchView;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressDialog pDialog;
    private static final int REQUEST_CODE = 10;
    // private FloatingSearchView floatingSearchView;
    int choice_filter = -1;
    int choice_setting = 0;
    CharSequence[] values = {"Nearby", "Rate"};
    CharSequence[] setting = {"Search as you enter the keyword"};
    AlertDialog alertDialog_setting;
    boolean bl[] = new boolean[2];

    AlertDialog alertDialog_filter;
    boolean t = true;
    boolean b = true;

    android.app.FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_search_locations);
        floatingSearchView = (FloatingSearchView) findViewById(R.id.floating_search_view);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        fragmentManager = getFragmentManager();
        floatingSearchView.setOnMenuItemClickListener(
                new FloatingSearchView.OnMenuItemClickListener() {
                    @Override
                    public void onActionMenuItemSelected(MenuItem item) {
                        if (item.getItemId() == R.id.action_showmaps) {

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
        Bundle extras = getIntent().getExtras();
        String value1;
        if (extras != null) {
            value1 = extras.getString("category");
            floatingSearchView.setSearchText(value1);
            SearchAsyncTask searchAsyncTask = new SearchAsyncTask(this);
            APIInterface apiService = ApiClient.getClient(this).create(APIInterface.class);
            Call<List<PlaceDetail>> call = apiService.searchPlace(value1
            );
            searchAsyncTask.execute(call);
        }

        floatingSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.action_settings) {
                    //Settingdialog();
                }
                if (item.getItemId() == R.id.action_filter) {
                    Filterdialog();
                }
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

//        floatingSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
//            @Override
//            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
//
//            }
//
//            @Override
//            public void onSearchAction(String currentQuery) {
//                SearchAsyncTask searchAsyncTask = new SearchAsyncTask(FragmentSearchLocations.this);
//                APIInterface apiService = ApiClient.getClient(FragmentSearchLocations.this).create(APIInterface.class);
//                Call<List<PlaceDetail>> call = apiService.searchPlace(currentQuery
//                );
//                searchAsyncTask.execute(call);
//            }
//        });


//            floatingSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
//                @Override
//                public void onSearchTextChanged(String oldQuery, String newQuery) {
//                    SearchAsyncTask searchAsyncTask = new SearchAsyncTask(FragmentSearchLocations.this);
//                    APIInterface apiService = ApiClient.getClient(FragmentSearchLocations.this).create(APIInterface.class);
//                    Call<List<PlaceDetail>> call = apiService.searchPlace(newQuery
//                    );
//                    searchAsyncTask.execute(call);
//                }
//            });

        // Setting the LayoutManager.
        layoutManager = new LinearLayoutManager(this);


    }

    public void Filterdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FragmentSearchLocations.this);
        builder.setTitle("Filter");
        builder.setSingleChoiceItems(values, choice_filter, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                choice_filter = item;
                switch (item) {
                    case 0:
                        break;
                    case 1:

                        break;

                }
                alertDialog_filter.dismiss();
            }
        });
        alertDialog_filter = builder.create();
        alertDialog_filter.show();

    }

    public void Settingdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FragmentSearchLocations.this);
        builder.setTitle("Setting");
        builder.setSingleChoiceItems(values, choice_setting, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                choice_setting = item;
                switch (item) {
                    case 0:

                        break;
                    case 1:
                        break;

                }
                alertDialog_setting.dismiss();
            }
        });
        alertDialog_setting = builder.create();
        alertDialog_setting.show();
    }

    public static void searchPoint(List<PlaceDetail> placeDetails) {
        Collections.sort(placeDetails, new Comparator<PlaceDetail>() {
            @Override
            public int compare(PlaceDetail placeDetail, PlaceDetail t1) {
                if (placeDetail.getDanhgia() > t1.getDanhgia()) {
                    return -1;
                }
                if (placeDetail.getDanhgia() == t1.getDanhgia()) {
                    return 0;
                }
                return 1;
            }
        });
    }

    public static void SearchNearby(List<PlaceDetail> placeDetails, Context context) {
        GPSTracker gps;
        gps = new GPSTracker(context);
        if (gps.canGetLocation()) {
            double lat = gps.getLatitude();
            double lng = gps.getLatitude();
            final Location locationA = new Location("point A");
            locationA.setLatitude(lat);
            locationA.setLongitude(lng);
            final Location locationB = new Location("point B");
            final Location locationC = new Location("point C");
            Collections.sort(placeDetails, new Comparator<PlaceDetail>() {
                @Override
                public int compare(PlaceDetail placeDetail, PlaceDetail t1) {
                    locationB.setLatitude(placeDetail.getKinhdo());
                    locationB.setLongitude(placeDetail.getVido());
                    locationC.setLatitude(t1.getKinhdo());
                    locationC.setLongitude(t1.getVido());
                    if (locationA.distanceTo(locationB) > locationA.distanceTo(locationC)) {
                        return -1;
                    }
                    if (locationA.distanceTo(locationB) == locationA.distanceTo(locationC)) {
                        return 0;
                    }
                    return 1;
                }
            });

        } else {
            gps.showSettingsAlert();
        }
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

                if (choice_filter == 0) {
                    SearchNearby(placeDetails, FragmentSearchLocations.this);

                }
                if (choice_filter == 1) {
                    searchPoint(placeDetails);
                }

                recyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(FragmentSearchLocations.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                HistoryLikeDTO historyLikeDTO = new HistoryLikeDTO();
                                historyLikeDTO.set_id(placeDetails.get(position).getPlace().getId_place());
                                historyLikeDTO.setTenDiaDiem(placeDetails.get(position).getPlace().getName_place());
                                historyLikeDTO.setTenDichVu(placeDetails.get(position).getCategory().getName_category());
                                //Lay gio he thong
                                Date thoiGian = new Date();

                                //Khai bao dinh dang ngay thang
                                SimpleDateFormat dinhDangThoiGian = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");

                                //parse ngay thang sang dinh dang va chuyen thanh string.
                                String hienThiThoiGian = dinhDangThoiGian.format(thoiGian.getTime());
                                historyLikeDTO.setThoiGianLike(hienThiThoiGian);
                                historyLikeDTO.setTenDiaChi("Số " + placeDetails.get(position).getSonha() +",đường " + placeDetails.get(position).getDuong().getName_duong() +", "+ placeDetails.get(position).getPhuong().getName_phuong() + ", " + placeDetails.get(position).getQuanhuyen().getName_quanhuyen() + ", " + placeDetails.get(position).getTinhthanh().getName_tinhthanh() + ".");
                                historyLikeDTO.setHinhAnhDichVu("");
                                historyLikeDTO.setDiemDanhGia(String.valueOf(placeDetails.get(position).getDanhgia()));

                                HistoryLikeDAO historyLikeDAO = new HistoryLikeDAO(getApplication());
                                historyLikeDAO.open();

                                boolean kiemtra = historyLikeDAO.AddHistoryLike(historyLikeDTO);
                                historyLikeDAO.close();
                                if (kiemtra)
                                    Toast.makeText(FragmentSearchLocations.this, "Đã thêm vào Lịch sử",Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(FragmentSearchLocations.this, "Chưa thêm vào Lịch sử", Toast.LENGTH_SHORT).show();
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
