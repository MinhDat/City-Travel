package com.example.thaianhit.citytravel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ThaiAnh IT on 13-Nov-16.
 */

public class AdapterDetailServices extends ArrayAdapter<ClassDetailService> {
    public AdapterDetailServices(Context context, int resource, List<ClassDetailService> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.item_dialog_detail_service, null);
        }
        ClassDetailService p = getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            TextView txt_name_detail = (TextView) view.findViewById(R.id.txt_name_detail);
            txt_name_detail.setText(p.name_detail);

            TextView txt_price_detail = (TextView) view.findViewById(R.id.txt_price_detail);
            txt_price_detail.setText(p.price_detail);
        }
        return view;
    }
}
