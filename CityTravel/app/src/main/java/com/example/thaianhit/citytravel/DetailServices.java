package com.example.thaianhit.citytravel;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailServices extends AppCompatActivity {

    ListView lvDetailServices;
    ArrayAdapter<Integer> adapter_detail;
    ArrayList<Integer> arr_detail;
    TextView txtChiTietDv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_services);

        txtChiTietDv = (TextView) findViewById(R.id.txt_detail_chi_tiet_dv);

        txtChiTietDv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDetail();
            }
        });
    }

    private void showDialogDetail() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_detail_services);

        arr_detail =new ArrayList<Integer>();
        lvDetailServices = (ListView) dialog.findViewById(R.id.lv_detail_services);
        arr_detail.add(1600000);
        arr_detail.add(1700000);
        arr_detail.add(1800000);
        arr_detail.add(1900000);
        arr_detail.add(1600000);
        arr_detail.add(1700000);
        arr_detail.add(1800000);
        arr_detail.add(1900000);
        adapter_detail = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_list_item_1, arr_detail);
        lvDetailServices.setAdapter(adapter_detail);
        dialog.setTitle("Chi tiết dịch vụ");
        dialog.show();
    }
}
