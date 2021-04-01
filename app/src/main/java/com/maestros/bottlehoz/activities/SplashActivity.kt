package com.maestros.bottlehoz.activities

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.webkit.PermissionRequest
import android.widget.Toast
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.DexterError
import com.maestros.bottlehoz.R
import com.maestros.bottlehoz.utils.AppConstats
import com.maestros.bottlehoz.utils.SharedHelper

class SplashActivity : AppCompatActivity() {
    var strUserID: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        /*  Handler().postDelayed({
            val intent = Intent(this, OnboardScreenActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)*/
    }
}