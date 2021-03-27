package com.maestros.bottlehoz.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.adapter.CartProductAdapter;
import com.maestros.bottlehoz.adapter.CartTittleAdapter;
import com.maestros.bottlehoz.databinding.ActivityAddAddressBinding;
import com.maestros.bottlehoz.databinding.ActivityCartBinding;
import com.maestros.bottlehoz.model.CartTittleModel;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
 ActivityCartBinding binding;
 View view;
RecyclerView.LayoutManager layoutManagerTittle;
ArrayList<CartTittleModel>cartTittleList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityCartBinding.inflate(getLayoutInflater());
        view=binding.getRoot();
        setContentView(view);

        layoutManagerTittle = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        binding.rvCart.setLayoutManager(layoutManagerTittle);
        binding.rvCart.setHasFixedSize(true);
        CartTittleAdapter cartTittleAdapter = new CartTittleAdapter(this,cartTittleList);
        binding.rvCart.setAdapter(cartTittleAdapter);

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        show_cart();

    }

    private void show_cart(){

        CartTittleModel model=new CartTittleModel("Sexy Dimples Drinks");

        for (int i = 0; i < 2; i++) {
            cartTittleList.add(model);
        }
    }
}