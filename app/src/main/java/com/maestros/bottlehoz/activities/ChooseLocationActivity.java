package com.maestros.bottlehoz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.databinding.ActivityAddAddressBinding;
import com.maestros.bottlehoz.databinding.ActivityChooseLocationBinding;
public class ChooseLocationActivity extends AppCompatActivity {

   private ActivityChooseLocationBinding binding;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityChooseLocationBinding.inflate(getLayoutInflater());
        view=binding.getRoot();
        setContentView(view);

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}