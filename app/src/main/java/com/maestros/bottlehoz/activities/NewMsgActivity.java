package com.maestros.bottlehoz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.databinding.ActivityChatBinding;
import com.maestros.bottlehoz.databinding.ActivityNewMsgBinding;

public class NewMsgActivity extends AppCompatActivity {
    ActivityNewMsgBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewMsgBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}