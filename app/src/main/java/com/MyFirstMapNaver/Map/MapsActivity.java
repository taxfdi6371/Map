package com.MyFirstMapNaver.Map;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.NonNull;


import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;

public class MapsActivity extends Activity implements OnMapReadyCallback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MapView mapView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapsactivity);
        mapView = findViewById(R.id.map_view);
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        Marker marker = new Marker();
        marker.setPosition(new LatLng(37.5670135, 126.9783740));
        marker.setMap(naverMap);
    }
}
