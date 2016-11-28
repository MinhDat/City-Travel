package com.example.thaianhit.citytravel;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import static com.example.thaianhit.citytravel.R.id.map;

public class DetailServices extends FragmentActivity implements OnMapReadyCallback {

    TextView txt_detail_service;
    ListView lv_detail_services;
    ArrayList arr_detail_services;
    AdapterDetailServices adapter;
    Button btn_detail_save;
    TextView tenDiaDiem;
    TextView txt_detail_dichvu;
    TextView txt_detail_address;

    HistoryLikeDAO historyLikeDAO;

    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_services);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarDetail);
        toolbar.setTitleTextColor(android.graphics.Color.WHITE);
        toolbar.setTitle("Dịch vụ");
        txt_detail_service = (TextView) findViewById(R.id.txt_chi_tiet_dv);
        //tbDetailService = findViewById(R.id.)
        txt_detail_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDetail();
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);

        btn_detail_save = (Button) findViewById(R.id.btn_detail_save);
        tenDiaDiem = (TextView) findViewById(R.id.tenDiaDiem);
        txt_detail_dichvu = (TextView) findViewById(R.id.txt_detail_dichvu);
        txt_detail_address = (TextView) findViewById(R.id.txt_detail_address);


        btn_detail_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Tạo Intent để mở màn hình History
                Intent myIntent = new Intent(DetailServices.this, HistoryActivity.class);
                //Khai báo Bundle
                Bundle bundle = new Bundle();

                HistoryLikeDTO historyLikeDTO = new HistoryLikeDTO();
                historyLikeDTO.setTenDiaDiem(tenDiaDiem.getText().toString());
                historyLikeDTO.setTenDichVu(txt_detail_dichvu.getText().toString());
                historyLikeDTO.setTenDiaChi(txt_detail_address.getText().toString());

                //Khai báo đối tượng sao chép để bỏ vào Bundle
                int getID = historyLikeDTO.get_id();
                String getNameDiaDiem = historyLikeDTO.getTenDiaDiem();
                String getNameDichVu = historyLikeDTO.getTenDichVu();
                String getNameDiaChi = historyLikeDTO.getTenDiaChi();

                //đưa dữ liệu vào Bundle
                bundle.putInt("getID",getID);
                bundle.putString("getNameDiaDiem",getNameDiaDiem);
                bundle.putString("getNameDichVu",getNameDichVu);
                bundle.putString("getNameDiaChi",getNameDiaChi);

                //Đưa Bundle vào Intent
                myIntent.putExtra("MyPackage", bundle);

                //Mở Activity HistoryActivity
                startActivity(myIntent);
            }
        });

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
                Intent intent = new Intent(getApplicationContext(), MapDetailService.class);
                startActivity(intent);
            }
        });
    }


}
