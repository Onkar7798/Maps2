package com.example.onkar.maps_test;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StillScreen extends AppCompatActivity {
    //private Button btn;
    private static int time = 4000;
    private static final String TAG = "SecondActivity";
    private boolean permisan=false;
    private static final int error_req=9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_still_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkLocationPermission();

                //finish();
            }
        },time);
    }
    private void checkLocationPermission ()
    {
        String[] per={Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION };
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
        {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            {
                permisan = true;
                startMapsActivity();
            }
            else
            {
                startAlternate();

            }
        }
        else
        {
            startAlternate();

        }
    }


    public void startMapsActivity(){
        Intent intent = new Intent(StillScreen.this, MapsActivity.class);
        startActivity(intent);
    }

    public void startAlternate(){
        Intent intent = new Intent(StillScreen.this,AlternateActivity.class);
        startActivity(intent);
    }


}


