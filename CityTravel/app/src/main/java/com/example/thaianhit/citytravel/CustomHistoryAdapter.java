package com.example.thaianhit.citytravel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by HuuBao on 28-Nov-16.
 */

public class CustomHistoryAdapter extends ArrayAdapter<HistoryLikeDTO> {

    Context context;
    int resource;
    List<HistoryLikeDTO> objects;

    public CustomHistoryAdapter(Context context, int resource, List<HistoryLikeDTO> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(resource,parent,false);

        TextView tenDiaDiem = (TextView) view.findViewById(R.id.tenDiaDiem);
        TextView tenDichVu = (TextView) view.findViewById(R.id.tenDichVu);
        TextView tenDiaChi = (TextView) view.findViewById(R.id.tenDiaChi);

        tenDiaDiem.setText(objects.get(position).getTenDiaDiem().toString());
        tenDichVu.setText(objects.get(position).getTenDichVu().toString());
        tenDiaChi.setText(objects.get(position).getTenDiaChi().toString());

        return view;
    }
}
