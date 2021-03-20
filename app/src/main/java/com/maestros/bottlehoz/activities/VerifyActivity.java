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
import com.maestros.bottlehoz.utils.Connectivity;
import com.maestros.bottlehoz.utils.ProgressBarCustom.CustomDialog;

import org.json.JSONException;
import org.json.JSONObject;

import static com.maestros.bottlehoz.retrofit.BaseUrl.HELP_EMAIL;
import static com.maestros.bottlehoz.retrofit.BaseUrl.OTPVERIFY;

public class VerifyActivity extends AppCompatActivity {
    private ActivityVerifyBinding binding;
    private Context context;
    private View view;
    private String email="",pwd="",mobile="",userType="",age="",strPin;
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
            email=getIntent().getStringExtra("email");
            pwd=getIntent().getStringExtra("pwd");
            mobile=getIntent().getStringExtra("mobile");
            userType=getIntent().getStringExtra("userType");
            age=getIntent().getStringExtra("age");

            Log.e("VerifyActivity", "onCreate: " +email);
            Log.e("VerifyActivity", "onCreate: " +pwd);
            Log.e("VerifyActivity", "onCreate: " +mobile);
            Log.e("VerifyActivity", "onCreate: " +userType);
            Log.e("VerifyActivity", "onCreate: " +age);
        }

        binding.tvMail.setText("A verification code sent to "+email);

        binding.btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userType.equals("3")){
                    strPin=binding.firstPinView.getText().toString();
                    if (connectivity.isOnline()){
                        sendData();
                    }else {

                    }

                }else {
                    startActivity(new Intent(context, AccountHelpActivity.class).putExtra("come_from", "1"));
                    finish();
                }
            }
        });
    }

    private void sendData() {
      CustomDialog dialog=new CustomDialog();
        dialog.showDialog(R.layout.progress_layout,this);
        AndroidNetworking.post(BaseUrl.BASEURL)
                .addBodyParameter("control",OTPVERIFY )
                .addBodyParameter("email", email)
                .addBodyParameter("otp", strPin)
                .setTag("OTPVERIFY")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dialog.hideDialog();
                        Log.e("response",response+"");

                        try {

                            if (response.getBoolean("result")==true){
                                startActivity(new Intent(context, BasicInfoActivity.class)
                                        .putExtra("email", email)
                                        .putExtra("pwd", pwd)
                                        .putExtra("mobile", mobile)
                                        .putExtra("userType", userType)
                                        .putExtra("age", age));
                                finish();

                            }else {
                                Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                                dialog.hideDialog();
                            }


                        } catch (JSONException e) {
                            Log.e("VerifyActivity", "e: " +e.getMessage());
                            dialog.hideDialog();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        Log.e("VerifyActivity", "error: " +error.getMessage());
                        dialog.hideDialog();
                    }
                });
    }
}