package com.maestros.bottlehoz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.maestros.bottlehoz.databinding.ActivityBasicInfoBinding;
import com.maestros.bottlehoz.databinding.ActivityChooseLocationBinding;

public class ChooseLocationActivity extends AppCompatActivity {
    private ActivityChooseLocationBinding binding;
    private Context context;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChooseLocationBinding.inflate(getLayoutInflater());
        view=binding.getRoot();
        setContentView(view);

        context=this;

        binding.rlCurrentLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}