package com.maestros.bottlehoz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import com.maestros.bottlehoz.databinding.ActivityAddAddressBinding;
import com.maestros.bottlehoz.utils.AppConstats;
import com.maestros.bottlehoz.utils.SharedHelper;


public class AddAddressActivity extends AppCompatActivity {
    private ActivityAddAddressBinding binding;
    private Context context;
    private View view;
    String st_address = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddAddressBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);
        st_address = SharedHelper.getKey(getApplicationContext(), AppConstats.SELECTADDRESS);
        Log.e("MapActivity", "st_address: " + st_address);

        if (st_address.equals("")) {

        } else {
            binding.etAddress.setText(st_address);
        }
        context = this;

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.rlCurrentLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddAddressActivity.this, MapActivity.class));
            }
        });
    }
}