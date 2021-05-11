package com.maestros.bottlehoz.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;


import android.widget.RatingBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;
import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.adapter.AllListingAdapter;
import com.maestros.bottlehoz.adapter.CategoryHome;
import com.maestros.bottlehoz.adapter.SearchResultAdapter;
import com.maestros.bottlehoz.adapter.SearchResultCategorytAdapter;
import com.maestros.bottlehoz.adapter.ShowAllCategorytAdapter;
import com.maestros.bottlehoz.databinding.ActivityAllListingProductBinding;
import com.maestros.bottlehoz.databinding.ActivitySearchResultBinding;
import com.maestros.bottlehoz.model.AllListingModel;
import com.maestros.bottlehoz.model.SearchResultModel;
import com.maestros.bottlehoz.retrofit.BaseUrl;
import com.maestros.bottlehoz.utils.AppConstats;
import com.maestros.bottlehoz.utils.ProgressBarCustom.CustomDialog;
import com.maestros.bottlehoz.utils.SharedHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.maestros.bottlehoz.retrofit.BaseUrl.SHOW_CATEGORY;

public class SearchResultActivity extends AppCompatActivity {
    ActivitySearchResultBinding binding;
    RecyclerView rvCategory;
    private ArrayList<SearchResultModel> searchList = new ArrayList<>();
    String StrStartPrice="",StrEndPrice="",strRating="",strWord="",stCategoryId="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.ivFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(SearchResultActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialog_rating_layout);

                getCategoryData();
                Button btnSearch = (Button) dialog.findViewById(R.id.btnSearch);
                rvCategory = (RecyclerView) dialog.findViewById(R.id.rvCategory);
                final EditText etPriceEnd=dialog.findViewById(R.id.etPriceEnd);
                final EditText etPriceStart=dialog.findViewById(R.id.etPriceStart);
                AppCompatRatingBar ratingStar=dialog.findViewById(R.id.ratingStar);
                rvCategory.setLayoutManager(new GridLayoutManager(SearchResultActivity.this, 3));
                btnSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        stCategoryId = SharedHelper.getKey(getApplicationContext(), AppConstats.CATEGORYID);

                        Log.e("SearchResultActivity", "stCategoryId: " +stCategoryId);
                        StrStartPrice=etPriceStart.getText().toString().trim();
                        StrEndPrice=etPriceEnd.getText().toString().trim();
                        strRating=String.valueOf(ratingStar.getRating());
                        Log.e("SearchResultActivity", "StrStartPrice: " +StrStartPrice);
                        Log.e("SearchResultActivity", "StrEndPrice: " +StrEndPrice);
                        Log.e("SearchResultActivity", "strRating: " +strRating);

                        search(strWord,StrStartPrice,StrEndPrice,strRating);
                    }
                });

                dialog.show();

            }
        });

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(SearchResultActivity.this, 2);
        binding.rvSearch.setLayoutManager(layoutManager);



        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                strWord=String.valueOf(s);
                search(strWord,StrStartPrice,StrEndPrice,strRating);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void search(String strWord,String strStartPrice,String strEndPrice,String strRating) {


        Log.e("SearchResultActivity", "search: " +strWord);
        Log.e("SearchResultActivity", "search: " +strStartPrice);
        Log.e("SearchResultActivity", "search: " +strEndPrice);
        Log.e("SearchResultActivity", "search: " +strRating);
      /*  CustomDialog dialog =new CustomDialog();
        dialog.showDialog(R.layout.progress_layout,this);*/
        AndroidNetworking.post(BaseUrl.BASEURL)
                .addBodyParameter("control", "search_product")
                .addBodyParameter("word", strWord)
                .addBodyParameter("price1",strStartPrice)
                .addBodyParameter("price2",strEndPrice)
               // .addBodyParameter("categoryID","price1")
                .addBodyParameter("rating",strRating)
                .setTag("Product is Showing Successfully")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //   dialog.hideDialog();
                        Log.e("SearchResultActivity", "onResponse: " + response);
                        searchList = new ArrayList<>();
                        try {
                            if (response.getString("result").equals("true")) {
                                if (!response.getString("data").isEmpty()) {
                                    JSONArray jsonArray = new JSONArray(response.getString("data"));
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                                        String product_image = jsonObject.getString("product_image");
                                        JSONArray jsonArray1 = new JSONArray(product_image);
                                        for (int j = 0; j < jsonArray1.length(); j++) {
                                            JSONObject object = jsonArray1.getJSONObject(j);


                                            SearchResultModel model = new SearchResultModel();
                                            model.setProductName(jsonObject.getString("name"));
                                            model.setPrice(jsonObject.getString("price"));
                                            model.setImage(object.getString("image"));
                                            model.setPath(object.getString("path"));
                                            // model.setRating(jsonObject.getString(""));
                                            searchList.add(model);

                                        }


                                    }


                                    if (jsonArray.length() == 0) {
                                        binding.rlLottie.setVisibility(View.VISIBLE);
                                        binding.rvSearch.setVisibility(View.GONE);
                                    } else {
                                        binding.rlLottie.setVisibility(View.GONE);
                                        binding.rvSearch.setVisibility(View.VISIBLE);
                                        SearchResultAdapter adapter = new SearchResultAdapter(SearchResultActivity.this, searchList);
                                        binding.rvSearch.setAdapter(adapter);

                                    }

                                }
                            } else {

                                Toast.makeText(SearchResultActivity.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                                // dialog.hideDialog();
                            }
                        } catch (JSONException e) {
                            Log.e("SearchResultActivity", "onResponse: " + e.getMessage());
                            // dialog.hideDialog();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("SearchResultActivity", "onResponse: " + anError.getMessage());
                        //dialog.hideDialog();
                    }
                });
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
                                    Toast.makeText(SearchResultActivity.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                                }


                                SearchResultCategorytAdapter adapterCat = new SearchResultCategorytAdapter(SearchResultActivity.this, arrayList);
                                rvCategory.setAdapter(adapterCat);
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
}