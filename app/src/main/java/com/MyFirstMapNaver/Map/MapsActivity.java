package com.MyFirstMapNaver.Map;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;

public class MapsActivity extends Activity implements OnMapReadyCallback {

    private final int MY_PERMISSIONS_REQUEST_LOCATION = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MapView mapView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapsactivity);
        mapView = findViewById(R.id.map_view);
        mapView.getMapAsync(this);

        // 권한보유 확인법, checkSelfPermission()매서드는 권한이 있으면 PERMISSION_GRANTED 반환, 없으면 PERMISSION_DENIED 반환
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        //권한이 없다면, 토스트 메세지 출력
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 승인이 필요합니다", Toast.LENGTH_LONG).show();

            //shouldShowRequestPermissionRationale() 매서드는 권한을 거부하면 true 반환.
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this, "지도 사용을 위해 위치 권한이 필요합니다.", Toast.LENGTH_LONG).show();
            }
            //아직 권한이 없는 경우 requestPermissions()메서드를 호출하여 권한 요청후 응답 결과를 가지고 콜백 매서드 호출
            else {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
                Toast.makeText(this, "지도 사용을 위해 위치 권한이 필요합니다.", Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this,"승인이 허가되어 있습니다.",Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this,"아직 승인받지 않았습니다.",Toast.LENGTH_LONG).show();
                }
                return;
            }

        }
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        Marker marker = new Marker();
        marker.setPosition(new LatLng(37.5670135, 126.9783740));
        marker.setMap(naverMap);
    }
}
