package com.example.thaianhit.citytravel;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;

//import com.akexorcist.googledirection.DirectionCallback;
//import com.akexorcist.googledirection.GoogleDirection;
//import com.akexorcist.googledirection.constant.TransportMode;
//import com.akexorcist.googledirection.model.Direction;
//import com.akexorcist.googledirection.model.Leg;
//import com.akexorcist.googledirection.model.Route;
//import com.akexorcist.googledirection.util.DirectionConverter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapDetailService extends FragmentActivity implements OnMapReadyCallback {

    Toolbar tbDetail;
    String API = "AIzaSyARQpLUzG0SDIebZUM_6zZfotVBmwG4r_E";
    public GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_detail_service);

        tbDetail = (Toolbar) findViewById(R.id.tbMapDetailService);
        tbDetail.setTitle("Map detail service");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.gg_map);
        mapFragment.getMapAsync(this);


    }

    void addMarker(LatLng position)
    {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15));
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_marker))
                .anchor(0.5f, 1.0f) // Anchors the marker on the bottom left
                .position(position));
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng ori = new LatLng(37.7849569, -122.4068855);
        LatLng des = new LatLng(37.7814432, -122.4460177);

        mMap = googleMap;
//        GoogleDirection.withServerKey(API)
//                .from(ori)
//                .to(des)
//                .transportMode(TransportMode.DRIVING)
//                .execute(new DirectionCallback() {
//                    @Override
//                    public void onDirectionSuccess(Direction direction, String rawBody) {
//
//                            Route route = direction.getRouteList().get(0);
//                            Leg leg = route.getLegList().get(0);
//                            ArrayList<LatLng> directionPositionList = leg.getDirectionPoint();
//                            PolylineOptions polylineOptions = DirectionConverter.createPolyline(getApplicationContext(), directionPositionList, 5, Color.RED);
//                            mMap.addPolyline(polylineOptions);
//                    }
//                    @Override
//                    public void onDirectionFailure(Throwable t) {
//                        // Do something here
//                        //Toast.makeText(getApplicationContext(),"can not found the way", Toast.LENGTH_LONG);
//                    }
//                });
        addMarker(ori);
        addMarker(des);
    }
}
