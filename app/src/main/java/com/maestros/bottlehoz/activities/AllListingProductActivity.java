package com.maestros.bottlehoz.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.adapter.AllListingAdapter;
import com.maestros.bottlehoz.adapter.FeatureRecommentAdapter;
import com.maestros.bottlehoz.adapter.RecommentAdapter;
import com.maestros.bottlehoz.databinding.ActivityAllListingProductBinding;
import com.maestros.bottlehoz.databinding.ActivityRecommendedBinding;
import com.maestros.bottlehoz.model.AllListingModel;
import com.maestros.bottlehoz.model.FeatureRecommentModel;
import com.maestros.bottlehoz.model.RecommentModel;

import java.util.ArrayList;
import java.util.List;

public class AllListingProductActivity extends AppCompatActivity {
    ActivityAllListingProductBinding binding;

    private AllListingAdapter adapter;
    private List<AllListingModel> allList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllListingProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(AllListingProductActivity.this,2);
        binding.rvAllList.setLayoutManager(layoutManager);

        getAllListData();
    }

    private void getAllListData() {


        AllListingModel listObj = new AllListingModel("Smirnoff", "N1,800", R.drawable.drink);

        for (int i = 0; i < 4; i++) {
            allList.add(listObj);
        }
        adapter = new AllListingAdapter(AllListingProductActivity.this, allList);
        binding.rvAllList.setAdapter(adapter);

    }
}