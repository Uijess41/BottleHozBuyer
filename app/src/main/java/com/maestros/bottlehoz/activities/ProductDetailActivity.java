package com.maestros.bottlehoz.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.adapter.ProductDetailSimilarAdapter;
import com.maestros.bottlehoz.databinding.ActivityProductDetailBinding;
import com.maestros.bottlehoz.model.ProductSimilarModel;
import com.maestros.bottlehoz.retrofit.BaseUrl;
import com.maestros.bottlehoz.utils.AppConstats;
import com.maestros.bottlehoz.utils.ProgressBarCustom.CustomDialog;
import com.maestros.bottlehoz.utils.SharedHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.maestros.bottlehoz.retrofit.BaseUrl.SHOW_PRODUCT_DETAILS;
import static com.maestros.bottlehoz.retrofit.BaseUrl.SHOW_SIMILAR_PRODUCT;

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


        binding.rvSimilar.setLayoutManager(new GridLayoutManager(ProductDetailActivity.this, 4));

        getSimilarData();
        productDetails();
    }

    private void productDetails(){
        String stProductId= SharedHelper.getKey(getApplicationContext(), AppConstats.PRODUCTID);
        Log.e("ProductDetailActivity", "PRODUCTID: " +stProductId);

        AndroidNetworking.post(BaseUrl.BASEURL)
                .addBodyParameter("control",SHOW_PRODUCT_DETAILS)
                .addBodyParameter("productID",stProductId)
                .setTag("SHOW PRODUCT DETAILS ")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("ProductDetailActivity", "onResponse: " +response);

                        try {
                            if (response.getString("result").equals("true")){
                                String data=response.getString("data");
                                if (!data.equals("")){
                                    JSONObject object=new JSONObject(data);
                                    String images=object.getString("images");
                                    JSONArray jsonArray=new JSONArray(images);
                                    if (jsonArray.length()!=0){
                                        for (int i = 0; i <jsonArray.length() ; i++) {
                                            JSONObject object1=jsonArray.getJSONObject(i);

                                            try {
                                                Glide.with(ProductDetailActivity.this).load(object1.getString("path")+object1.getString("image"))
                                                        .placeholder(R.drawable.dummy).override(250, 250)
                                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                        .into( binding.ivProduct);
                                            }catch (Exception e){

                                            }
                                        }

                                        binding.txtName.setText(object.getString("name"));
                                        binding.txtDescription.setText(object.getString("description"));
                                        binding.txtPrice.setText("₦"+object.getString("price"));




                                    }

                                }



                            }
                        } catch (JSONException e) {
                            Log.e("ProductDetailActivity", "e: " +e);

                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("ProductDetailActivity", "anError: " +anError);

                    }
                });


    }

    private void getSimilarData() {
       String stCategoryId= SharedHelper.getKey(getApplicationContext(), AppConstats.CATEGORYID);
        Log.e("ProductDetailActivity", "stCategoryId: " +stCategoryId);

        CustomDialog dialog=new CustomDialog();
        dialog.showDialog(R.layout.progress_layout,this);
        AndroidNetworking.post(BaseUrl.BASEURL)
                .addBodyParameter("control",SHOW_SIMILAR_PRODUCT)
                .addBodyParameter("categoryID",stCategoryId)
                .setTag("SHOW SIMILAR PRODUCT IMAGE")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dialog.hideDialog();
                        similarList=new ArrayList<>();
                        Log.e("ProductDetailActivity", "onResponse: " +response);
                        try {
                            if (response.getString("result").equals("true")){
                                String data=response.getString("data");
                                JSONArray array=new JSONArray(data);
                                for (int j = 0; j <array.length() ; j++) {
                                    JSONObject object=array.getJSONObject(j);
                                    String name=object.getString("name");
                                    String images=object.getString("images");
                                    JSONArray jsonArray=new JSONArray(images);
                                    for (int i = 0; i <jsonArray.length() ; i++) {
                                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                                        ProductSimilarModel model=new ProductSimilarModel();
                                        model.setImage(jsonObject.getString("image"));
                                        model.setPath(jsonObject.getString("path"));
                                        model.setProductName(object.getString("name"));
                                        similarList.add(model);
                                    }
                                }

                                adapter = new ProductDetailSimilarAdapter(ProductDetailActivity.this, similarList);
                                binding.rvSimilar.setAdapter(adapter);
                            }
                        } catch (JSONException e) {
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