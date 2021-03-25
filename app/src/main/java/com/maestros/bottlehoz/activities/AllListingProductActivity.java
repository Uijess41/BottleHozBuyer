package com.maestros.bottlehoz.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.adapter.AllListingAdapter;
import com.maestros.bottlehoz.adapter.FeatureRecommentAdapter;
import com.maestros.bottlehoz.adapter.RecommendedHomeAdapter;
import com.maestros.bottlehoz.adapter.RecommentAdapter;
import com.maestros.bottlehoz.databinding.ActivityAllListingProductBinding;
import com.maestros.bottlehoz.databinding.ActivityRecommendedBinding;
import com.maestros.bottlehoz.model.AllListingModel;
import com.maestros.bottlehoz.model.FeatureRecommentModel;
import com.maestros.bottlehoz.model.RecommendedHomeModel;
import com.maestros.bottlehoz.model.RecommentModel;
import com.maestros.bottlehoz.retrofit.BaseUrl;
import com.maestros.bottlehoz.utils.ProgressBarCustom.CustomDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.maestros.bottlehoz.retrofit.BaseUrl.SHOW_FILTER_PRODUCT;
import static com.maestros.bottlehoz.retrofit.BaseUrl.SHOW_RECOMMENDED_PRODUCT;

public class AllListingProductActivity extends AppCompatActivity {
    ActivityAllListingProductBinding binding;


    private List<AllListingModel> allList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllListingProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(AllListingProductActivity.this,2);
        binding.rvAllList.setLayoutManager(layoutManager);

        getAllListData();
    }

    private void getAllListData() {
        CustomDialog dialog = new CustomDialog();
        dialog.showDialog(R.layout.progress_layout, this);

        AndroidNetworking.post(BaseUrl.BASEURL)
                .addBodyParameter("control", SHOW_FILTER_PRODUCT)
                .setTag("Show Filter Product")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("AllListingProductActivity", "onResponse: " + response.toString());
                        dialog.hideDialog();
                        allList=new ArrayList<>();

                        try {
                            if (response.getBoolean("result") == true) {

                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                                    String images = jsonObject.getString("images");

                                    JSONArray array = new JSONArray(images);
                                    for (int j = 0; j < array.length(); j++) {
                                        JSONObject object = array.getJSONObject(j);

                                        AllListingModel model = new AllListingModel();
                                            model.setProductId(jsonObject.getString("productID"));
                                            model.setProductName(jsonObject.getString("name"));
                                            model.setPrice(jsonObject.getString("price"));
                                            model.setDescription(jsonObject.getString("description"));
                                            if (!images.equals("")) {
                                                model.setImage(object.getString("image"));
                                                model.setPath(object.getString("path"));
                                                allList.add(model);


                                        }



                                    }


                                }
                                AllListingAdapter adapter = new AllListingAdapter(AllListingProductActivity.this, allList);
                                binding.rvAllList.setAdapter(adapter);
                            }

                            else {

                                Toast.makeText(AllListingProductActivity.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                                dialog.hideDialog();
                            }
                        } catch (JSONException e) {
                            Log.e("AllListingProductActivity", "e: " + e.getMessage());
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