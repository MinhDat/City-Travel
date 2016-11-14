package com.example.thaianhit.citytravel;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

public class fragment_search_locations extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CustomRecyclerAdapterSearch adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageView img_backround;
    FloatingActionButton floatingActionButton;
    private List<DataRecyclerSearch> listData = new ArrayList<DataRecyclerSearch>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_search_locations);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.setting);
        // If the size of views will not change as the data changes.
        recyclerView.setHasFixedSize(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertFormElements();
            }
        });
        // Setting the LayoutManager.
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DataRecyclerSearch a=new DataRecyclerSearch("Bệnh viện","Nodescription","10");
        DataRecyclerSearch b=new DataRecyclerSearch("ATM","Nodescription","10");
        DataRecyclerSearch c=new DataRecyclerSearch("Trường học","Nodescription","10");
        DataRecyclerSearch d=new DataRecyclerSearch("Khách sạn","Nodescription","10");
        listData.add(a);
        listData.add(b);
        listData.add(c);
        listData.add(d);
        // Setting the adapter.
        adapter = new CustomRecyclerAdapterSearch(listData,fragment_search_locations.this);
        recyclerView.setAdapter(adapter);
    }
    public void alertFormElements() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.alert_show_select_search,
                null, false);

        // You have to list down your form elements
        final CheckBox myCheckBoxNear = (CheckBox) formElementsView
                .findViewById(R.id.myCheckBoxNear);

        final CheckBox myCheckBoxRate = (CheckBox) formElementsView
                .findViewById(R.id.myCheckBoxRate);
        // the alert dialog
        new AlertDialog.Builder(fragment_search_locations.this).setView(formElementsView)
                .setTitle("Form Elements")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String toastString = "";

                    /*
                     * Detecting whether the checkbox is checked or not.
                     */
                        if (myCheckBoxNear.isChecked()) {
                            toastString += "Happy is checked!\n";
                        } else {
                            toastString += "Happy IS NOT checked.\n";
                        }

                    /*
                     * Getting the value of selected RadioButton.
                     */
                        // get selected radio button from radioGroup


                        dialog.cancel();
                    }

                }).show();
    }
}
