package com.maestros.bottlehoz.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.adapter.FavoriteAdapter;
import com.maestros.bottlehoz.adapter.FeatureRecommentAdapter;
import com.maestros.bottlehoz.adapter.RecommentAdapter;
import com.maestros.bottlehoz.databinding.ActivityFavoriteBinding;
import com.maestros.bottlehoz.databinding.ActivityRecommendedBinding;
import com.maestros.bottlehoz.model.FavoriteModel;
import com.maestros.bottlehoz.model.FeatureRecommentModel;
import com.maestros.bottlehoz.model.RecommentModel;

import java.util.ArrayList;
import java.util.List;

public class RecommendedActivity extends AppCompatActivity {


    ActivityRecommendedBinding binding;

    private RecommentAdapter adapter;
    private List<RecommentModel> recommentList = new ArrayList<>();

    private FeatureRecommentAdapter featureadapter;
    private List<FeatureRecommentModel> featureList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecommendedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(RecommendedActivity.this,2);
        binding.rvFeature.setLayoutManager(layoutManager);


        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(RecommendedActivity.this,RecyclerView.VERTICAL,false);
        binding.rvRecomment.setLayoutManager(layoutManager1);

        getFeatureData();
        getRecommentData();


    }

    private void getFeatureData() {


        FeatureRecommentModel favObj = new FeatureRecommentModel("Tipsy Liquors", "Chinese Food Spacialists", "4.5", R.drawable.drinksss);

        for (int i = 0; i < 4; i++) {
            featureList.add(favObj);
        }
        featureadapter = new FeatureRecommentAdapter(RecommendedActivity.this, featureList);
        binding.rvFeature.setAdapter(featureadapter);

    }

    private void getRecommentData(){

        RecommentModel recommentObj = new RecommentModel("Sexy Dimples Liquors", "Breakfast", "4.5", R.drawable.bttle);

        for (int i = 0; i < 4; i++) {
            recommentList.add(recommentObj);
        }
        adapter = new RecommentAdapter(RecommendedActivity.this, recommentList);
        binding.rvRecomment.setAdapter(adapter);


    }
}