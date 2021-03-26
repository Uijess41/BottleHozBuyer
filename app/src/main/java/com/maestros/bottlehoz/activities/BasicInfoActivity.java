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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.maestros.bottlehoz.databinding.ActivityBasicInfoBinding;
import com.maestros.bottlehoz.model.SignupModel;
import com.maestros.bottlehoz.retrofit.BaseUrl;
import com.maestros.bottlehoz.utils.AppConstats;
import com.maestros.bottlehoz.utils.SharedHelper;

import org.json.JSONException;
import org.json.JSONObject;

import static com.maestros.bottlehoz.retrofit.BaseUrl.SIGNUP;

public class BasicInfoActivity extends AppCompatActivity {
    private ActivityBasicInfoBinding binding;
    private Context context;
    private View view;
    private String email="",pwd="",mobile="",userType="",age="",strName;
    Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityBasicInfoBinding.inflate(getLayoutInflater());
        view=binding.getRoot();
        setContentView(view);

        context=this;
        gson=new Gson();
        if (getIntent()!=null){
            email=getIntent().getStringExtra("email");
            pwd=getIntent().getStringExtra("pwd");
            mobile=getIntent().getStringExtra("mobile");
            userType=getIntent().getStringExtra("userType");
            Log.e("BasicInfoActivity", "userType: " +userType);
            age=getIntent().getStringExtra("age");
        }

        binding.btnChooseLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, ChooseLocationActivity.class));
            }
        });

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strName=binding.etName.getText().toString().trim();

                if (TextUtils.isEmpty(strName)){
                    binding.etName.setError("Please enter your name!");
                    binding.etName.requestFocus();
                }else {
                    sendData();
                }
            }
        });
    }

    private void sendData() {
        AndroidNetworking.post(BaseUrl.BASEURL)
                .addBodyParameter("control",SIGNUP )
                .addBodyParameter("email", email)
                .addBodyParameter("mobile", mobile)
                .addBodyParameter("age_validation", age)
                .addBodyParameter("type", "3")
                .addBodyParameter("name", strName)
                .addBodyParameter("password",pwd)
                .addBodyParameter("address", "sdjkf")
                .addBodyParameter("longitude", "23.345345")
                .addBodyParameter("latitude", "45.353")
                .setTag("SIGNUP")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("response",response+"");

                        try {

                            /* SignupModel model = gson.fromJson(response.getJSONObject("data").toString(), SignupModel.class);
                           if (gson.fromJson(response.getJSONObject("result").toString().equals(""),))
                           Log.e("model>>",model.getData().getEmail());*/

                            if (response.getBoolean("result")==true){

                                SharedHelper.putKey(getApplicationContext(), AppConstats.USER_ID, "");
                                startActivity(new Intent(context, BottomNavActivity.class));

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