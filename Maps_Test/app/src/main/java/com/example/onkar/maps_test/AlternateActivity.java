package com.example.onkar.maps_test;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.android.gms.location.FusedLocationProviderClient;

public class AlternateActivity extends AppCompatActivity {
    private Button btn;
    private boolean permisan=false;
    private int count = 1;
    private FusedLocationProviderClient

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alternate);

        waitForButton();



    }

    public void waitForButton(){
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( permisan == true){
                    startMapsActivity();
                }
                else{
                    getLocationPermission();
                    check();
                    waitForButton();

                }
            }
        });
    }
    public void check(){
        if(permisan==true){
            startMapsActivity();
        }
        else{
            waitForButton();
        }
    }
    private void getLocationPermission ()
    {
        String[] per={Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION };
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
        {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            {
                permisan = true;
                waitForButton();
            }
            else
            {
                ActivityCompat.requestPermissions(this,per,1234);

            }
        }
        else
        {
            ActivityCompat.requestPermissions(this,per,1234);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] per, @NonNull int[] grantResults)
    {
        permisan = false;

        switch (requestCode)
        {
            case 1234:
            {
                if(grantResults.length>0)
                {
                    for(int i=0;i<grantResults.length;i++)
                    {
                        String permission = per[i];
                        if(grantResults[i]!=PackageManager.PERMISSION_GRANTED)
                        {
                            boolean showRationale = shouldShowRequestPermissionRationale( permission );
                            if (! showRationale) {
                                permisan=false;
                                // user also CHECKED "never ask again"
                                // you can either enable some fall back,
                                // disable features of your app
                                // or open another dialog explaining
                                // again the permission and directing to
                                // the app setting
                            } else if (Manifest.permission.WRITE_CONTACTS.equals(permission)) {

                                permisan=false;
                                return;
                                // user did NOT check "never ask again"
                                // this is a good place to explain the user
                                // why you need the permission and ask if he wants
                                // to accept it (the rationale)
                            }

                        }
                    }
                    permisan=true;
                    check();

                }
            }

        }
    }

    public void startMapsActivity(){
        Intent intent = new Intent(AlternateActivity.this, MapsActivity.class);
        startActivity(intent);
    }


}
