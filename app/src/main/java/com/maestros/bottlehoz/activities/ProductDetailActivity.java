package com.maestros.bottlehoz.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.adapter.DiscountAdapter;
import com.maestros.bottlehoz.adapter.MoreAdapter;
import com.maestros.bottlehoz.adapter.ProductDetailSimilarAdapter;
import com.maestros.bottlehoz.databinding.ActivityFavoriteBinding;
import com.maestros.bottlehoz.databinding.ActivityProductDetailBinding;
import com.maestros.bottlehoz.model.CategoryModel;
import com.maestros.bottlehoz.model.DiscountPercentModel;
import com.maestros.bottlehoz.model.ProductSimilarModel;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {
    ActivityProductDetailBinding binding;
    private ProductDetailSimilarAdapter adapter;
    private List<ProductSimilarModel> similarList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(ProductDetailActivity.this,RatingAndReviewActivity.class));
            }
        });
        adapter = new ProductDetailSimilarAdapter(ProductDetailActivity.this, similarList);

        binding.rvSimilar.setLayoutManager(new GridLayoutManager(ProductDetailActivity.this, 4));
        binding.rvSimilar.setAdapter(adapter);
        getSimilarData();

    }

    private void getSimilarData() {
        ProductSimilarModel similarObj = new ProductSimilarModel("VSOP", R.drawable.btl);

        for (int i = 0; i < 8; i++) {
            similarList.add(similarObj);
        }

    }
}