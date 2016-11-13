package com.example.thaianhit.citytravel;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailServices extends AppCompatActivity {

    TextView txt_detail_service;

    ListView lv_detail_services;
    ArrayList arr_detail_services;
    AdapterDetailServices adapter;
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
}
