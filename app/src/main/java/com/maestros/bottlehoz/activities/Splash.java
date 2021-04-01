package com.maestros.bottlehoz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.utils.AppConstats;
import com.maestros.bottlehoz.utils.Connectivity;
import com.maestros.bottlehoz.utils.SharedHelper;

import java.util.List;
import java.util.Objects;

public class Splash extends AppCompatActivity {
    String strUserID = "";
   RelativeLayout noConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);
        strUserID = SharedHelper.getKey(getApplicationContext(), AppConstats.USER_ID);
        noConnection=findViewById(R.id.noConnection);
        Connectivity connectivity = new Connectivity(this);
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.INTERNET,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.WRITE_CONTACTS,
                        Manifest.permission.CAMERA,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE )

                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                if (connectivity.isOnline()){
                                    if (strUserID.equals("")){
                                        //  finish();

                                        startActivity(new Intent(Splash.this, OnboardScreenActivity.class));
                                        finish();
                                    }

                                    else {

                                        // finish();
                                        startActivity(new Intent(Splash.this, BottomNavActivity.class));
                                        finish();
                                    }
                                    noConnection.setVisibility(View.GONE);
                                }
                                else {

                                    noConnection.setVisibility(View.VISIBLE);

                                }

                            }


                        },2000);
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Error occurred! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }
}
