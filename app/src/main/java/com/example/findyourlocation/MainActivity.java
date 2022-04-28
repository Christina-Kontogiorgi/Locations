package com.example.findyourlocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.View;

import com.example.findyourlocation.databinding.ActivityMainBinding;

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
        binder=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binder.getRoot());

        //exit FAB
        binder.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              moveTaskToBack(true);
              android.os.Process.killProcess(android.os.Process.myPid());
              System.exit(1);
            }
        });


    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }



}
