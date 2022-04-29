package com.example.findyourlocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.findyourlocation.databinding.ActivityMainBinding;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LocationListener {
    private ActivityMainBinding binder;
    protected static final String[] PERMS = {
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binder.getRoot());
        checkPermissions(MainActivity.this, PERMS);


        //exit FAB
        binder.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });

        LocationListener locListener=this;
        LocationManager mLocManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //even though we dont need that check (user already forced to allow location perm),it it needed for location manager below
        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED
            && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
                return;
            }
        }
        if (mLocManager!=null){
            mLocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,3000, 1, locListener);
            mLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000, 1, locListener);

        }



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length == PERMS.length) {
            if (allPermsGranted(MainActivity.this, PERMS)) {//Tricky click the login when all perms are granted
                // ta permissions exoun parthei


            }else{
                // o xrhsths aperipse ta permissions
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }

        }
    }

        private void checkPermissions (Context context, String[]perms){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                for (final String s : perms)
                    if (context.checkSelfPermission(s) != PackageManager.PERMISSION_GRANTED)
                        ((AppCompatActivity) context).requestPermissions(perms, 1111);
            }
        }


        public static boolean allPermsGranted (Context context, String[]perms){
            int curPermsAllowed = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                for (final String s : perms)
                    if (context.checkSelfPermission(s) == PackageManager.PERMISSION_GRANTED)
                        curPermsAllowed++;
                return curPermsAllowed == perms.length;
            }
            //sumvatothta pros ta pisw
            return true;

        }


        @Override
        public void onLocationChanged (@NonNull Location location){
            Log.e("test",location.toString());

        }




}