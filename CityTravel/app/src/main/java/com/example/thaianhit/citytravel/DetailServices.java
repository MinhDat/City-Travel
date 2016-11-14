package com.example.thaianhit.citytravel;

import android.app.Dialog;
import android.location.Address;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ListView;
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

public class DetailServices extends FragmentActivity implements OnMapReadyCallback {

    TextView txt_detail_service;

    ListView lv_detail_services;
    ArrayList arr_detail_services;
    AdapterDetailServices adapter;

    List<Address> addresses = null;
    String address = "", city = "", country = "", postalCode;

    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_services);
        txt_detail_service = (TextView) findViewById(R.id.txt_chi_tiet_dv);
        txt_detail_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDetail();
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);
    }

    private void showDialogDetail(){
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
        mMap = googleMap;

        LatLng sydney = new LatLng(10.751242, 106.701170);

        mMap.addMarker(new MarkerOptions().position(sydney).title("861/90C Trần Xuân Soạn"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(14.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.moveCamera(cameraUpdate);

    }
}
