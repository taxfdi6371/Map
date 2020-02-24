package com.MyFirstMapNaver.Map;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.LocationOverlay;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.FusedLocationSource;

public class MapsActivity extends Activity implements OnMapReadyCallback {
    private final int MY_PERMISSIONS_REQUEST_LOCATION = 1001;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MapView mapView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapsactivity);
        mapView = findViewById(R.id.map_view);
        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);
        mapView.getMapAsync(this);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,  @NonNull int[] grantResults) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            return;
        }
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
    Marker marker = new Marker();
    LatLng MyLatLng = new LatLng(35.888,128.65);
   // CameraPosition cameraPosition = new CameraPosition(MyLatLng, 14, 0,  0 );
    marker.setPosition(MyLatLng);

    naverMap.setLocationSource(locationSource);
        //Toast.makeText(this,""+naverMap.getMapType(),Toast.LENGTH_LONG).show();//지도의 타입
    marker.setMap(naverMap);
    //marker2.setPosition((new LatLng(35.88,128.655))); marker2.setMap(naverMap);
}

}
