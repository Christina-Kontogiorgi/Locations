package com.example.findyourlocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
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
        checkPermissions(MainActivity.this,PERMS);


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

    private void checkPermissions(Context context, String[] perms) {
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            for ( final String s : perms)
                if (context.checkSelfPermission(s) != PackageManager.PERMISSION_GRANTED)
                    ((AppCompatActivity) context).requestPermissions(perms, 1111);
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }



}
