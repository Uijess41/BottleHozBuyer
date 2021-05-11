package com.maestros.bottlehoz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.databinding.ActivityVerifyBinding;
import com.maestros.bottlehoz.retrofit.BaseUrl;
import com.maestros.bottlehoz.utils.AppConstats;
import com.maestros.bottlehoz.utils.Connectivity;
import com.maestros.bottlehoz.utils.ProgressBarCustom.CustomDialog;
import com.maestros.bottlehoz.utils.SharedHelper;

import org.json.JSONException;
import org.json.JSONObject;

import static com.maestros.bottlehoz.retrofit.BaseUrl.HELP_EMAIL;
import static com.maestros.bottlehoz.retrofit.BaseUrl.OTPVERIFY;

public class VerifyActivity extends AppCompatActivity {
    private ActivityVerifyBinding binding;
    private Context context;
    private View view;
    private String email="",pwd="",mobile="",userType="",age="",strPin,struserId="",otp="";
    Connectivity connectivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityVerifyBinding.inflate(getLayoutInflater());
        view=binding.getRoot();
        setContentView(view);

        context=this;
        connectivity=new Connectivity(context);
        if (getIntent()!=null){
           /* email=getIntent().getStringExtra("email");
            pwd=getIntent().getStringExtra("pwd");
            mobile=getIntent().getStringExtra("mobile");

            age=getIntent().getStringExtra("age");*/

            userType=getIntent().getStringExtra("userType");
            otp=getIntent().getStringExtra("otp");
            struserId=getIntent().getStringExtra("struserId");
        }

        binding.tvMail.setText("A verification code sent to "+email);

        binding.btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (connectivity.isOnline()) {
                    strPin = binding.firstPinView.getText().toString();
                    if (userType.equals("1")){
                        if (otp.equals(strPin)){
                            startActivity(new Intent(context, BasicInfoActivity.class));
                            finish();
                        }else {
                            Toast.makeText(context, "Please enter correct otp !", Toast.LENGTH_SHORT).show();
                        }}
                    else {
                        startActivity(new Intent(context, AccountHelpActivity.class).putExtra("come_from", "1"));
                        finish();
                    }


                 /*
                    if (userType.equals("3")||userType.equals("2")) {

                        if (userType.equals("3")){
                            startActivity(new Intent(context, BasicInfoActivity.class)
                                    .putExtra("email", email)
                                    .putExtra("pwd", pwd)
                                    .putExtra("mobile", mobile)
                                    .putExtra("userType", userType)
                                    .putExtra("age", age));
                        }else if (userType.equals("1")){


                            startActivity(new Intent(context, CreateSellerActivity.class));
//                            startActivity(new Intent(context, SellerDashboard.class).putExtra("My_product","0"));
                        }
                        finish();

                    }  else {
                        startActivity(new Intent(context, AccountHelpActivity.class).putExtra("come_from", "1"));
                    }
                    finish();*/

                } else{
                    Toast.makeText(context, "Please check your internet connection!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}