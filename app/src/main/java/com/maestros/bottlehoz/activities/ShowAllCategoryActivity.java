package com.maestros.bottlehoz.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;
import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.adapter.CategoryHome;
import com.maestros.bottlehoz.adapter.CategorytAdapter;
import com.maestros.bottlehoz.adapter.PremiumAdapter;
import com.maestros.bottlehoz.adapter.ShowAllCategorytAdapter;
import com.maestros.bottlehoz.databinding.ActivityShowAllCatBinding;
import com.maestros.bottlehoz.retrofit.BaseUrl;
import com.maestros.bottlehoz.utils.ProgressBarCustom.CustomDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.maestros.bottlehoz.retrofit.BaseUrl.SHOW_BRAND;
import static com.maestros.bottlehoz.retrofit.BaseUrl.SHOW_CATEGORY;

public class ShowAllCategoryActivity extends AppCompatActivity {

    ActivityShowAllCatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityShowAllCatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rvCategory.setLayoutManager(new GridLayoutManager(ShowAllCategoryActivity.this, 3));
        binding.rvBrands.setLayoutManager(new GridLayoutManager(ShowAllCategoryActivity.this, 3));
        getCategoryData();
        getPremiumData();
    }


    private void getCategoryData() {
        CustomDialog dialog = new CustomDialog();
        dialog.showDialog(R.layout.progress_layout, this);
        AndroidNetworking.post(BaseUrl.BASEURL)
                .addBodyParameter("control", SHOW_CATEGORY)
                .setTag("Show Category")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("ShowAllCategoryActivity", response.toString());
                        dialog.hideDialog();
                        try {
                            if (response.getBoolean("result") == true) {

                                Gson gson = new Gson();
                                CategoryHome dataCategory = gson.fromJson(response.toString(), CategoryHome.class);

                                ArrayList arrayList = new ArrayList<CategoryHome.Data>();
                                if (!dataCategory.getData().isEmpty()) {
                                    arrayList.addAll(dataCategory.getData());
                                } else {
                                    Toast.makeText(ShowAllCategoryActivity.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                                }


                                ShowAllCategorytAdapter adapterCat = new ShowAllCategorytAdapter(ShowAllCategoryActivity.this, arrayList);
                                binding.rvCategory.setAdapter(adapterCat);
                            }
                        } catch (JSONException e) {
                            Log.e("ShowAllCategoryActivity", "e: " + e.getMessage());
                            dialog.hideDialog();
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        dialog.hideDialog();

                    }
                });

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void getPremiumData() {

        AndroidNetworking.post(BaseUrl.BASEURL)
                .addBodyParameter("control", SHOW_BRAND)
                .setTag("Show Premium Image")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("ShowAllCategoryActivity", response.toString());
                        try {
                            if (response.getBoolean("result") == true) {

                                Gson gson = new Gson();
                                DataImagePremium data = gson.fromJson(response.toString(), DataImagePremium.class);

                                ArrayList arrayList = new ArrayList<DataImagePremium.Data>();

                                if (!data.getData().isEmpty()) {
                                    arrayList.addAll(data.getData());
                                } else {
                                    Toast.makeText(ShowAllCategoryActivity.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                                }


                                PremiumAdapter adapterPremium = new PremiumAdapter(ShowAllCategoryActivity.this, arrayList);
                                binding.rvBrands.setAdapter(adapterPremium);
                            }
                        } catch (JSONException e) {
                            Log.e("ShowAllCategoryActivity", "e: " + e.getMessage());
                        }


                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }
}


