package com.example.thaianhit.citytravel;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapDetailService extends AppCompatActivity implements OnMapReadyCallback {

    Toolbar tbDetail;
    String API = "AIzaSyARQpLUzG0SDIebZUM_6zZfotVBmwG4r_E";
    public GoogleMap mMap;
    
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_detail_service);

        intent = getIntent();
        tbDetail = (Toolbar) findViewById(R.id.tbMapDetailService);
        tbDetail.setTitle("Map detail service");

        setSupportActionBar(tbDetail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Map detail service");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.gg_map);
        mapFragment.getMapAsync(this);
    }

    void addMarker(LatLng position)
    {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 16));
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_marker))
                .anchor(0.5f, 1.0f)
                .position(position));
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng point = new LatLng(10.751242, 106.701170);
        mMap = googleMap;
        addMarker(point);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_map_detail_direction, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        Intent intentMap, chooser=null;
        float lat = (float) 10.751242;
        float lng = (float) 106.701170;
        Uri uri = Uri.parse("http://maps.google.com/maps?saddr="+lat+","+lng+"&daddr="+10.748544+","+106.688742);

        switch (id){
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.mnMapDirection:
                intentMap = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr="+lat+","+lng+"&daddr="+10.748544+","+106.688742));
                chooser = Intent.createChooser(intentMap,"Launch Maps");
                intentMap.setClassName("com.google.android.apps.maps",
                        "com.google.android.maps.MapsActivity");

                startActivity(chooser);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
