package com.maestros.bottlehoz.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.adapter.AdapterTablayout;
import com.maestros.bottlehoz.adapter.DataMoreLove;
import com.maestros.bottlehoz.adapter.ListingAdapter;
import com.maestros.bottlehoz.adapter.MoreAdapter;
import com.maestros.bottlehoz.adapter.ShowProductAdapter;
import com.maestros.bottlehoz.databinding.ActivityFavoriteBinding;
import com.maestros.bottlehoz.databinding.ActivityShowCatWiseProductBinding;

import com.maestros.bottlehoz.model.ListingModel;
import com.maestros.bottlehoz.model.ShowCatWiseProductModel;
import com.maestros.bottlehoz.retrofit.BaseUrl;
import com.maestros.bottlehoz.utils.AppConstats;
import com.maestros.bottlehoz.utils.Connectivity;
import com.maestros.bottlehoz.utils.ProgressBarCustom.CustomDialog;
import com.maestros.bottlehoz.utils.SharedHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.maestros.bottlehoz.retrofit.BaseUrl.SHOW_FILTER_PRODUCT;
import static com.maestros.bottlehoz.retrofit.BaseUrl.SHOW_PRODUCT_MORE;

public class ShowCatWiseProductActivity extends AppCompatActivity {
   ActivityShowCatWiseProductBinding binding;

    private List<ShowCatWiseProductModel> productArrayList=new ArrayList<>();
    String stCategoryId="",stCategoryName="";
    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowCatWiseProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        stCategoryId = SharedHelper.getKey(getApplicationContext(), AppConstats.CATEGORYID);
        stCategoryName = SharedHelper.getKey(getApplicationContext(), AppConstats.CATEGORYNAME);

        Log.e("ShowCatWiseProductActivity", "stCategoryId: " +stCategoryId);
        binding.txtCatName.setText(stCategoryName);

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
       /* for (int i = 0; i < no_of_weeks; i++) {
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText("TAB " + String.valueOf(i + 1)));
        }*/
     //   binding.tabLayout.addTab( binding.tabLayout.newTab().setText( "Sign Up" ) );
     //   binding.tabLayout.addTab( binding.tabLayout.newTab().setText( "Sign In" ) );

        final AdapterTablayout adapterTablayout = new AdapterTablayout( getSupportFragmentManager(), binding.tabLayout.getTabCount() );
        binding.viewPager.setAdapter( adapterTablayout );
        binding.viewPager.addOnPageChangeListener( new TabLayout.TabLayoutOnPageChangeListener( binding.tabLayout ) );

        binding.tabLayout.addOnTabSelectedListener( new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager.setCurrentItem( tab.getPosition() );
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        } );



        binding.rvCategory.setLayoutManager(new GridLayoutManager(this,2));


        Connectivity connectivity = new Connectivity(this);
        if (connectivity.isOnline()){
            showProduct();

        }
        else {
            Toast.makeText(this,"Please check internet connection",Toast.LENGTH_SHORT).show();
        }

    }

    private void showProduct() {
        CustomDialog dialog=new CustomDialog();
        dialog.showDialog(R.layout.progress_layout,this);
        AndroidNetworking.post(BaseUrl.BASEURL)
                .addBodyParameter("control", SHOW_FILTER_PRODUCT)
                .addBodyParameter("categoryID", stCategoryId)
                .setTag("Show Category wise  Product")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {

                    @SuppressLint("LongLogTag")
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("ShowCatWiseProductActivity", response.toString());
                        dialog.hideDialog();
                        try {
                            if (response.getBoolean("result") == true) {

                                Gson gson = new Gson();
                                DataCatViseProduct dataProduct = gson.fromJson(response.toString(), DataCatViseProduct.class);

                                ArrayList arrayList = new ArrayList<DataCatViseProduct.Data>();


                                if (!dataProduct.getData().isEmpty()) {
                                    arrayList.addAll(dataProduct.getData());

                                } else {


                                    Toast.makeText(ShowCatWiseProductActivity.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                                }


                                ShowProductAdapter productAdapter = new ShowProductAdapter(ShowCatWiseProductActivity.this, arrayList);
                                binding.rvCategory.setAdapter(productAdapter);
                            }
                            else {

                                binding.lotiAnimation.setVisibility(View.VISIBLE);
                                binding.txtNo.setVisibility(View.VISIBLE);
                                dialog.hideDialog();
                            }
                        } catch (JSONException e) {
                            Log.e("ShowCatWiseProductActivity", "e: " + e.getMessage());
                            dialog.hideDialog();
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        dialog.hideDialog();
                    }
                });


    }

    }
