package com.example.thaianhit.citytravel;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

import static com.example.thaianhit.citytravel.R.id.map;

public class DetailServices extends AppCompatActivity implements OnMapReadyCallback {
    private RecyclerView recyclerView;
    TextView txt_detail_service;
    ListView lv_detail_services;
    ArrayList arr_detail_services;
    RelativeLayout bt_direction;
    AdapterDetailServices adapter;
    private List<Comment> commentList = new ArrayList<>();
    private CommentAdapter commentAdapter;
    private GoogleMap mMap;
    Intent intentMap, chooser=null;
    float lat = (float) 10.751242;
    float lng = (float) 106.701170;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_services);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarDetail);
        txt_detail_service = (TextView) findViewById(R.id.txt_chi_tiet_dv);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_dtail);
        bt_direction = (RelativeLayout)findViewById(R.id.bt_direction);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(android.graphics.Color.WHITE);
        getSupportActionBar().setTitle("Detail service");
        bt_direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intentMap = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr="+lat+","+lng+"&daddr="+10.748544+","+106.688742));
                chooser = Intent.createChooser(intentMap,"Launch Maps");
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
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);
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

    private void showDialogDetail()
    {
        Dialog dialog = new Dialog(this);
        dialog.setTitle("Chi tiết dịch vụ");
        dialog.setContentView(R.layout.custom_dialog_detail);
        lv_detail_services = (ListView) dialog.findViewById(R.id.lv_dialog_detail);
        arr_detail_services = new ArrayList();
        arr_detail_services.add(new ClassDetailService("Loại phòng 1","500,000"));
        arr_detail_services.add(new ClassDetailService("Loại phòng 2","400,000"));
        arr_detail_services.add(new ClassDetailService("Loại phòng 3","300,000"));
        arr_detail_services.add(new ClassDetailService("Loại phòng 4","200,000"));
        arr_detail_services.add(new ClassDetailService("Loại phòng 1","500,000"));
        arr_detail_services.add(new ClassDetailService("Loại phòng 2","400,000"));
        arr_detail_services.add(new ClassDetailService("Loại phòng 3","300,000"));
        arr_detail_services.add(new ClassDetailService("Loại phòng 4","200,000"));
        adapter = new AdapterDetailServices(getApplicationContext(), R.layout.item_dialog_detail_service, arr_detail_services);
        lv_detail_services.setAdapter(adapter);
        dialog.show();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.getUiSettings().setAllGesturesEnabled(false);
        mMap = googleMap;

        final LatLng sydney = new LatLng(10.751242, 106.701170);

        mMap.addMarker(new MarkerOptions().position(sydney).title("861/90C Trần Xuân Soạn"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(14.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.moveCamera(cameraUpdate);
        
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
            case android.R.id.home :
            {
                onBackPressed();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    private void preparePlaceData()
    {
        Comment comment = new Comment("Dũng", "15/06/2016", "http://www.wn.com.vn/timthumb.php?src=http://www.wn.com.vn/product_images/x/971/anh-girl-xinh-full-hd(1)__70218.jpg&w=1000&h=606&zc=1","Glide includes a flexible API that allows developers to plug in to almost any network stack. " );
        commentList.add(comment);
        comment = new Comment("Dũng Trương Như","11/01/2016", "http://toananhdep.com/wp-content/uploads/2015/12/tuyen-tap-nhung-hinh-anh-girl-xinh-dang-yeu-nhat-viet-nam-9.jpg", "Glide's primary focus is on making scrolling any kind of a list of images as smooth and fast as possible, but Glide is also effective for almost any case where you need to fetch, resize, and display a remote image.");
        commentList.add(comment);
        commentAdapter.notifyDataSetChanged();
    }

}
