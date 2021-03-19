package com.maestros.bottlehoz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.maestros.bottlehoz.databinding.ActivityAccountHelpBinding;
import com.maestros.bottlehoz.retrofit.BaseUrl;
import com.maestros.bottlehoz.utils.Connectivity;

import org.json.JSONException;
import org.json.JSONObject;

import static com.maestros.bottlehoz.retrofit.BaseUrl.ACC_HELP_EMAIL;
import static com.maestros.bottlehoz.retrofit.BaseUrl.HELP_EMAIL;
import static com.maestros.bottlehoz.retrofit.BaseUrl.SIGNUP;

public class AccountHelpActivity extends AppCompatActivity {
    private ActivityAccountHelpBinding binding;
    private Context context;
    private View view;
    private String come_from="",strHelpEmail="";
    Connectivity connectivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAccountHelpBinding.inflate(getLayoutInflater());
        view=binding.getRoot();
        setContentView(view);

        context=this;

        if (getIntent()!=null){
            come_from=getIntent().getStringExtra("come_from");
        }

        if (come_from.equals("1")){
            binding.layoutEmail.setVisibility(View.GONE);
            binding.layoutPwdChange.setVisibility(View.VISIBLE);
            binding.btnSubmit.setText("Submit");
        }else {
            binding.layoutEmail.setVisibility(View.VISIBLE);
            binding.layoutPwdChange.setVisibility(View.GONE);
            binding.btnSubmit.setText("Send");
        }



        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strHelpEmail=binding.etEmailAcc.getText().toString();

                if (binding.btnSubmit.getText().toString().equals("Send")){
                    if (TextUtils.isEmpty(strHelpEmail)){
                        binding.etEmailAcc.setError("Please enter email !");
                        binding.etEmailAcc.requestFocus();
                    }else {
                        if (connectivity.isOnline()){
                            sendData();
                        }else {
                            Toast.makeText(context,"Please check internet connection",Toast.LENGTH_SHORT).show();
                        }
                    }


                }else if (binding.btnSubmit.getText().toString().equals("Submit")){
                    if (connectivity.isOnline()){
                        submitData();
                    }else {
                        Toast.makeText(context,"Please check internet connection",Toast.LENGTH_SHORT).show();
                    }


                }

                finish();
            }
        });
    }

    private void submitData() {
        AndroidNetworking.post(BaseUrl.BASEURL)
                .addBodyParameter("control",HELP_EMAIL )
                .addBodyParameter("email", binding.etEmailAcc.getText().toString())
                .addBodyParameter("new_password", binding.etPwdNew.getText().toString())
                .addBodyParameter("confirm_password", binding.etPwdConfirm.getText().toString())
                .setTag("HELP_EMAIL")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("response",response+"");

                        try {

                            if (response.getBoolean("result")==true){
                                startActivity(new Intent(context,LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

                            }else {

                            }

                            Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    private void sendData() {
        AndroidNetworking.post(BaseUrl.BASEURL)
                .addBodyParameter("control",ACC_HELP_EMAIL )
                .addBodyParameter("email", binding.etEmailAcc.getText().toString())
                .setTag("ACC_HELP_EMAIL")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("response",response+"");

                        try {

                            if (response.getBoolean("result")==true){
                                startActivity(new Intent(context, VerifyActivity.class)
                                        .putExtra("email",binding.etEmailAcc.getText().toString()));
                            }else {

                            }

                            Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }


}