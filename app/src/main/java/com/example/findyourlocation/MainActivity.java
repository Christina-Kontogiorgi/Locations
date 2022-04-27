package com.example.findyourlocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.example.findyourlocation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements LocationListener {
    private ActivityMainBinding binder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binder.getRoot());


    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}