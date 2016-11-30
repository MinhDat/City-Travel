package com.example.thaianhit.citytravel;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.thaianhit.citytravel._class.PlaceDetail;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

import static com.example.thaianhit.citytravel.R.id.map;

public class DetailServices extends AppCompatActivity implements OnMapReadyCallback {
    private RecyclerView recyclerView;
    private ProgressDialog pDialog;
    TextView txt_detail_service;
    TextView tv_name_place;
    @Bind(R.id.rating_bar_detail)
    RatingBar rating_bar_detail;
    @Bind(R.id.txt_detail_rating)
    TextView txt_detail_rating;
    @Bind(R.id.img_category)
    ImageView img_category;
    @Bind(R.id.txt_detail_address)
    TextView txt_detail_address;
    @Bind(R.id.txt_detail_dichvu)
    TextView txt_detail_dichvu;
    @Bind(R.id.txt_note_detail)
    TextView txt_note_detail;
    ListView lv_detail_services;
    ArrayList arr_detail_services;
    RelativeLayout bt_direction;
    SupportMapFragment mapFragment;
    AdapterDetailServices adapter;
    private List<Comment> commentList = new ArrayList<>();
    private CommentAdapter commentAdapter;
    private GoogleMap mMap;
    Intent intentMap, chooser = null;
    double latA, latB;
    double lngA, lngB;
    int id_place;
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_services);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDetail);
        txt_detail_service = (TextView) findViewById(R.id.txt_chi_tiet_dv);
        tv_name_place = (TextView) findViewById(R.id.tv_name_place);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_dtail);
        bt_direction = (RelativeLayout) findViewById(R.id.bt_direction);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(android.graphics.Color.WHITE);
        getSupportActionBar().setTitle("Detail service");
        gps = new GPSTracker(this);
        if (gps.canGetLocation()) {
            latA = gps.getLatitude();
            lngA = gps.getLongitude();
        } else {
            latA = 0;
            lngA = 0;
        }
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            id_place = extras.getInt("id");
            APIInterface apiService = ApiClient.getClient(this).create(APIInterface.class);
            Call<PlaceDetail> call = apiService.getPlace(id_place);
            CategoryAsyncTask categoryAsyncTask = new CategoryAsyncTask();
            categoryAsyncTask.execute(call);
        }

        bt_direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentMap = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr=" + latA + "," + lngA + "&daddr=" + latB + "," + lngB));
                chooser = Intent.createChooser(intentMap, "Launch Maps");
                intentMap.setClassName("com.google.android.apps.maps",
                        "com.google.android.maps.MapsActivity");

                startActivity(chooser);
            }
        });
        txt_detail_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDetail();
            }
        });
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(DetailServices.this);
        commentAdapter = new CommentAdapter(commentList, DetailServices.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(commentAdapter);
        recyclerView.setFocusable(false);
        preparePlaceData();
    }

    private void showDialogDetail() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_detail);
        lv_detail_services = (ListView) dialog.findViewById(R.id.lv_dialog_detail);
        arr_detail_services = new ArrayList();
        arr_detail_services.add(new ClassDetailService("Loại phòng 1", "500,000"));
        arr_detail_services.add(new ClassDetailService("Loại phòng 2", "400,000"));
        arr_detail_services.add(new ClassDetailService("Loại phòng 3", "300,000"));
        arr_detail_services.add(new ClassDetailService("Loại phòng 4", "200,000"));
        arr_detail_services.add(new ClassDetailService("Loại phòng 1", "500,000"));
        arr_detail_services.add(new ClassDetailService("Loại phòng 2", "400,000"));
        arr_detail_services.add(new ClassDetailService("Loại phòng 3", "300,000"));
        arr_detail_services.add(new ClassDetailService("Loại phòng 4", "200,000"));
        adapter = new AdapterDetailServices(getApplicationContext(), R.layout.item_dialog_detail_service, arr_detail_services);
        lv_detail_services.setAdapter(adapter);
        dialog.show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.getUiSettings().setAllGesturesEnabled(false);
        mMap = googleMap;
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Intent intent = new Intent(DetailServices.this, MapDetailService.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void preparePlaceData() {
        Comment comment = new Comment("Dũng", "15/06/2016", "http://www.wn.com.vn/timthumb.php?src=http://www.wn.com.vn/product_images/x/971/anh-girl-xinh-full-hd(1)__70218.jpg&w=1000&h=606&zc=1", "Glide includes a flexible API that allows developers to plug in to almost any network stack. ");
        commentList.add(comment);
        comment = new Comment("Dũng Trương Như", "11/01/2016", "http://toananhdep.com/wp-content/uploads/2015/12/tuyen-tap-nhung-hinh-anh-girl-xinh-dang-yeu-nhat-viet-nam-9.jpg", "Glide's primary focus is on making scrolling any kind of a list of images as smooth and fast as possible, but Glide is also effective for almost any case where you need to fetch, resize, and display a remote image.");
        commentList.add(comment);
        commentAdapter.notifyDataSetChanged();
    }

    public class CategoryAsyncTask extends AsyncTask<Call, Void, PlaceDetail> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(DetailServices.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(PlaceDetail placeDetail) {

            super.onPostExecute(placeDetail);
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }
            try {
                tv_name_place.setText(placeDetail.getPlace().getName_place());
                rating_bar_detail.setRating(placeDetail.getDanhgia());
                txt_detail_rating.setText(String.valueOf(placeDetail.getDanhgia()));
                Glide.with(DetailServices.this)
                        .load(placeDetail.getCategory().getPhoto_category())
                        .override(60, 60)
                        .centerCrop()
                        .into(img_category);
                txt_detail_address.setText(placeDetail.getSonha() + ", " + placeDetail.getDuong().getName_duong() + ", " + placeDetail.getPhuong().getName_phuong() + ", " + placeDetail.getQuanhuyen().getName_quanhuyen() + ", " + placeDetail.getTinhthanh().getName_tinhthanh() + ".");
                txt_detail_dichvu.setText(placeDetail.getCategory().getName_category());
                txt_note_detail.setText(placeDetail.getChuthich());
                latB = placeDetail.getKinhdo();
                lngB = placeDetail.getVido();
                Toast.makeText(DetailServices.this, String.valueOf(latB), Toast.LENGTH_SHORT).show();
                final LatLng sydney = new LatLng(placeDetail.getKinhdo(), placeDetail.getVido());
                mMap.addMarker(new MarkerOptions().position(sydney).title(placeDetail.getPlace().getName_place()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(14.0f).build();
                CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                mMap.moveCamera(cameraUpdate);
            } catch (Exception e) {
                Toast.makeText(DetailServices.this, "Load fail", Toast.LENGTH_SHORT).show();
                Log.d("TGGG", e.toString());
            }


        }

        @Override
        protected PlaceDetail doInBackground(Call... calls) {
            try {
                Call<PlaceDetail> call = calls[0];
                Response<PlaceDetail> response = call.execute();
                return response.body();
            } catch (Exception e) {
                Log.d("fffff", e.toString());
                return null;
            }

        }
    }

}
