package com.MyFirstMapNaver.Map;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Intro extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

        new Thread() {
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//GPS화면 띄우기 인데 여기 잘 모르겠음
                LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
                boolean enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);

                if (!enabled) {
                    Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(i);
                } //여기가 헷갈림
                    Intent i = new Intent(Intro.this, MapsActivity.class);
                    startActivity(i);
                finish();
            }
        }.start();
    }
}