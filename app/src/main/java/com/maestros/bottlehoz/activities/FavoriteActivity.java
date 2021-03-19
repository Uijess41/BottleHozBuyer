package com.maestros.bottlehoz.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.adapter.DiscountAdapter;
import com.maestros.bottlehoz.adapter.FavoriteAdapter;
import com.maestros.bottlehoz.adapter.PopularAdapter;
import com.maestros.bottlehoz.databinding.ActivityFavoriteBinding;
import com.maestros.bottlehoz.model.DiscountPercentModel;
import com.maestros.bottlehoz.model.FavoriteModel;
import com.maestros.bottlehoz.model.MoreModel;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {
    ActivityFavoriteBinding binding;

    private FavoriteAdapter adapter;
    private List<FavoriteModel> favoriteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.txtCart.setPaintFlags(binding.txtCart.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FavoriteActivity.this, RecyclerView.VERTICAL, false);
        binding.rvFavorite.setLayoutManager(layoutManager);


        getFavoriteItemData();

        binding.txtItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.txtDeal.setVisibility(View.VISIBLE);
                binding.btnItems.setVisibility(View.VISIBLE);
                binding.btnDeal.setVisibility(View.GONE);
                binding.txtItems.setVisibility(View.GONE);
                binding.txtStores.setVisibility(View.VISIBLE);
                binding.btnStores.setVisibility(View.GONE);
                favoriteList.clear();
                getFavoriteItemData();
            }
        });


        binding.txtStores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnStores.setVisibility(View.VISIBLE);
                binding.txtItems.setVisibility(View.VISIBLE);
                binding.txtDeal.setVisibility(View.VISIBLE);
                binding.btnDeal.setVisibility(View.GONE);
                binding.txtStores.setVisibility(View.GONE);
                binding.btnItems.setVisibility(View.GONE);
                favoriteList.clear();
                getFavoriteStoreData();
            }
        });


        binding.txtDeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.txtDeal.setVisibility(View.GONE);
                binding.btnItems.setVisibility(View.GONE);
                binding.btnDeal.setVisibility(View.GONE);
                binding.txtItems.setVisibility(View.VISIBLE);
                binding.txtStores.setVisibility(View.VISIBLE);
                binding.btnStores.setVisibility(View.GONE);
                binding.btnDeal.setVisibility(View.VISIBLE);
                favoriteList.clear();
                getFavoriteDealData();
            }
        });


    }

    private void getFavoriteDealData() {


        FavoriteModel favObj = new FavoriteModel("Buy 1 get 1 free", "Xee Cellar", "Wines", R.drawable.bttle);

        for (int i = 0; i < 4; i++) {
            favoriteList.add(favObj);
        }
        adapter = new FavoriteAdapter(FavoriteActivity.this, favoriteList);
        binding.rvFavorite.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void getFavoriteItemData() {


        FavoriteModel favObj = new FavoriteModel("Long Island", "Cockails cocks", "N 2500", R.drawable.item);

        for (int i = 0; i < 4; i++) {
            favoriteList.add(favObj);
        }
        adapter = new FavoriteAdapter(FavoriteActivity.this, favoriteList);
        binding.rvFavorite.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void getFavoriteStoreData() {


        FavoriteModel favObj = new FavoriteModel("Market Bistro", "Wine and Spirit", "", R.drawable.store);

        for (int i = 0; i < 4; i++) {
            favoriteList.add(favObj);
        }


        for (int i = 0; i < favoriteList.size(); i++) {
            Log.e("owidwsd", favoriteList.get(i).getBrandName());
        }
        adapter = new FavoriteAdapter(FavoriteActivity.this, favoriteList);
        binding.rvFavorite.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}