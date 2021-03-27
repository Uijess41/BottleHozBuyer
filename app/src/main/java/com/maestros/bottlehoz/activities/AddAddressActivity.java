package com.maestros.bottlehoz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.maestros.bottlehoz.databinding.ActivityAddAddressBinding;


public class AddAddressActivity extends AppCompatActivity {
    private ActivityAddAddressBinding binding;
    private Context context;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddAddressBinding.inflate(getLayoutInflater());
        view=binding.getRoot();
        setContentView(view);

        context=this;

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             finish();
            }
        });

        binding.rlCurrentLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(AddAddressActivity.this,ChooseLocationActivity.class));
            }
        });
    }
}