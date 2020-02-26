package com.MyFirstMapNaver.Map;

import android.Manifest;
import android.app.Activity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.content.pm.PackageManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.LocationOverlay;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.FusedLocationSource;


public class MapsActivity extends Activity implements OnMapReadyCallback, LocationListener {
    private MapView mapView;
    private double longitude; //경도
    private double latitude;   //위도
    Button locationFindButton;
    LocationManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapsactivity);
        mapView = findViewById(R.id.map_view);
        locationFindButton = findViewById((R.id.LocationButtonView));
        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationFindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (locationFindButton.isPressed()) {
                        locationFindButton.setText("수신중..");
                        // GPS 제공자의 정보가 바뀌면 콜백하도록 리스너 등록하기~!!!
                        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, // 등록할 위치제공자
                                100, // 통지사이의 최소 시간간격 (miliSecond)
                                1, // 통지사이의 최소 변경거리 (m)
                                MapsActivity.this);
                        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, // 등록할 위치제공자
                                100, // 통지사이의 최소 시간간격 (miliSecond)
                                1, // 통지사이의 최소 변경거리 (m)
                                MapsActivity.this);
                    } else {
                        locationFindButton.setText("위치 찾기");
                        lm.removeUpdates(MapsActivity.this);  //  미수신할때는 반드시 자원해체를 해주어야 한다.
                    }
                } catch (SecurityException ex) {
                }
            }
        });
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        LatLng MyLatLng;
      //  if (latitude != 0.0 && longitude != 0.0) {
       //     MyLatLng = new LatLng(latitude, longitude);
      //  } else {
            MyLatLng = new LatLng(37.5670135, 126.9783740);
      //  }

        LocationOverlay locationOverlay = naverMap.getLocationOverlay();
        locationOverlay.setVisible(true);
        locationOverlay.setPosition(MyLatLng);//내위치


        Marker marker = new Marker();
        marker.setPosition(MyLatLng);//마커 위치 지정
        marker.setMap(naverMap);//마커를 지도위에 찍기


        CameraPosition cameraPosition = new CameraPosition(MyLatLng, 14, 0, 0);//카메라 포지션
        naverMap.setCameraPosition(cameraPosition);


        //Toast.makeText(this,""+naverMap.getMapType(),Toast.LENGTH_LONG).show();//지도의 타입
    }

    @Override
    public void onLocationChanged(Location location) {
        longitude = location.getLongitude(); //경도
        latitude = location.getLatitude();   //위도
        Toast.makeText(MapsActivity.this,"경도 : "+longitude+"위도 : "+latitude,Toast.LENGTH_LONG).show();
//        double altitude = location.getAltitude();   //고도
//        float accuracy = location.getAccuracy();    //정확도
//        String provider = location.getProvider();   //위치제공자

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("test", "onStatusChanged, provider:" + provider + ", status:" + status + " ,Bundle:" + extras);
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("test", "onProviderEnabled, provider:" + provider);
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("test", "onProviderDisabled, provider:" + provider);
    }
}
