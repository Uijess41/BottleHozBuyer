package com.maestros.bottlehoz.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;
import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.activities.CartActivity;
import com.maestros.bottlehoz.activities.DataDiscount;
import com.maestros.bottlehoz.activities.DataImagePremium;
import com.maestros.bottlehoz.activities.ManageAddressActivity;
import com.maestros.bottlehoz.activities.RecommendedActivity;
import com.maestros.bottlehoz.activities.ShowAllCategoryActivity;
import com.maestros.bottlehoz.adapter.CategoryHome;
import com.maestros.bottlehoz.adapter.CategorytAdapter;
import com.maestros.bottlehoz.adapter.DataMoreLove;
import com.maestros.bottlehoz.adapter.DiscountAdapter;
import com.maestros.bottlehoz.adapter.ListingAdapter;
import com.maestros.bottlehoz.adapter.MoreAdapter;
import com.maestros.bottlehoz.adapter.PopularAdapter;
import com.maestros.bottlehoz.adapter.PremiumAdapter;
import com.maestros.bottlehoz.adapter.RecommendedHomeAdapter;
import com.maestros.bottlehoz.adapter.SliderAdapterExample;
import com.maestros.bottlehoz.databinding.FragmentHomeBinding;
import com.maestros.bottlehoz.model.ListingModel;
import com.maestros.bottlehoz.model.PopularModel;
import com.maestros.bottlehoz.model.RecommendedHomeModel;
import com.maestros.bottlehoz.model.SliderModel;
import com.maestros.bottlehoz.retrofit.BaseUrl;
import com.maestros.bottlehoz.utils.Connectivity;
import com.maestros.bottlehoz.utils.ProgressBarCustom.CustomDialog;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.maestros.bottlehoz.retrofit.BaseUrl.SHOW_BANNER;
import static com.maestros.bottlehoz.retrofit.BaseUrl.SHOW_BRAND;
import static com.maestros.bottlehoz.retrofit.BaseUrl.SHOW_CATEGORY;
import static com.maestros.bottlehoz.retrofit.BaseUrl.SHOW_DISCOUNT;
import static com.maestros.bottlehoz.retrofit.BaseUrl.SHOW_PREMIUM_PRODUCT;
import static com.maestros.bottlehoz.retrofit.BaseUrl.SHOW_PRODUCT_MORE;
import static com.maestros.bottlehoz.retrofit.BaseUrl.SHOW_PRODUCT_POPULAR;
import static com.maestros.bottlehoz.retrofit.BaseUrl.SHOW_RECOMMENDED_PRODUCT;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private View view;

    SliderAdapterExample sliderAdapter;
    List<SliderModel> listOfSlider = new ArrayList<>();


    List<PopularModel> popularList = new ArrayList<>();


    private ListingAdapter adapterListing;
    private List<ListingModel> listing = new ArrayList<>();

    private List<RecommendedHomeModel> recommendHomeList = new ArrayList<>();
    private Context context;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater(), container, false);
        view = binding.getRoot();


        binding.sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        binding.sliderView.setSliderTransformAnimation(SliderAnimations.CUBEOUTROTATIONTRANSFORMATION);
        binding.sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        binding.sliderView.setIndicatorSelectedColor(Color.DKGRAY);
        binding.sliderView.setIndicatorUnselectedColor(Color.LTGRAY);
        binding.sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        binding.sliderView.startAutoCycle();


        view = binding.getRoot();
        context = getActivity();


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        binding.rvDiscount.setLayoutManager(mLayoutManager);

        binding.txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RecommendedActivity.class));
            }
        });


        binding.txLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, ManageAddressActivity.class));
            }
        });

        binding.imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CartActivity.class));




            }
        });

        binding.txtCatView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ShowAllCategoryActivity
                        .class));
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        binding.rvCategory.setLayoutManager(layoutManager);


        RecyclerView.LayoutManager layoutManagerPop = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        binding.rvPopular.setLayoutManager(layoutManagerPop);


        adapterListing = new ListingAdapter(context, listing);

        RecyclerView.LayoutManager layoutManagerList = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        binding.rvListing.setLayoutManager(layoutManagerList);
        binding.rvListing.setAdapter(adapterListing);


        RecyclerView.LayoutManager layoutManagerPremium = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        binding.rvPremium.setLayoutManager(layoutManagerPremium);


        RecyclerView.LayoutManager layoutManagerRecommend = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        binding.rvRecommend.setLayoutManager(layoutManagerRecommend);


        binding.rvMore.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        onBack(view);
        Connectivity connectivity = new Connectivity(getActivity());
        if (connectivity.isOnline()) {
            //   getMoreData();
            getDiscountData();
            getCategoryData();
            getPopularData();
            getListingData();
            getBrandData();
            showBanner();
            getRecommendedProduct();
        } else {
            Toast.makeText(getActivity(), "Please check internet connection", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void showBanner() {

        CustomDialog dialog = new CustomDialog();
        dialog.showDialog(R.layout.progress_layout, getActivity());
        AndroidNetworking.post(BaseUrl.BASEURL)
                .addBodyParameter("control", SHOW_BANNER)
                .setTag("SHOWBANNER")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("response", response + "");
                        listOfSlider = new ArrayList<>();
                        dialog.hideDialog();
                        try {
                            if (response.getString("result").equals("true")) {
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    SliderModel model = new SliderModel();

                                    model.setImageBanner(jsonObject.getString("image"));
                                    model.setPath(jsonObject.getString("path"));
                                    //  model.setTitle(jsonObject.getString("title"));
                                    //  model.setDiscription(jsonObject.getString("description"));
                                    listOfSlider.add(model);

                                }

                                sliderAdapter = new SliderAdapterExample(listOfSlider, getActivity());
                                binding.sliderView.setSliderAdapter(sliderAdapter);

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
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void getDiscountData() {
        AndroidNetworking.post(BaseUrl.BASEURL)
                .addBodyParameter("control", SHOW_DISCOUNT)
                .setTag("Show Discount")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("HomeFragment", response.toString());
                        try {
                            if (response.getBoolean("result") == true) {

                                Gson gson = new Gson();
                                DataDiscount dataDiscount = gson.fromJson(response.toString(), DataDiscount.class);

                                ArrayList arrayList = new ArrayList<DataDiscount.Data>();
                                if (!dataDiscount.getData().isEmpty()) {
                                    arrayList.addAll(dataDiscount.getData());
                                } else {
                                    Toast.makeText(getActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                }


                                DiscountAdapter adapter = new DiscountAdapter(context, arrayList);
                                binding.rvDiscount.setAdapter(adapter);
                            }
                        } catch (JSONException e) {
                            Log.e("HomeFragment", "e: " + e.getMessage());
                        }


                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });


    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void getCategoryData() {
        AndroidNetworking.post(BaseUrl.BASEURL)
                .addBodyParameter("control", SHOW_CATEGORY)
                .setTag("Show Category")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("HomeFragment", response.toString());
                        try {
                            if (response.getBoolean("result") == true) {

                                Gson gson = new Gson();
                                CategoryHome dataCategory = gson.fromJson(response.toString(), CategoryHome.class);

                                ArrayList arrayList = new ArrayList<CategoryHome.Data>();
                                if (!dataCategory.getData().isEmpty()) {

                                    for (int i = 0; i < 4; i++) {
                                        arrayList.add(dataCategory.getData().get(i));
                                        //arrayList.addAll(dataCategory.getData());
                                    }

                                } else {
                                    Toast.makeText(getActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                }


                                CategorytAdapter adapterCat = new CategorytAdapter(context, arrayList);
                                binding.rvCategory.setAdapter(adapterCat);
                            }
                        } catch (JSONException e) {
                            Log.e("HomeFragment", "e: " + e.getMessage());
                        }


                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });


    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void getPopularData() {

        AndroidNetworking.post(BaseUrl.BASEURL)
                .addBodyParameter("control", SHOW_PRODUCT_POPULAR)
                .setTag("Show Popular Product")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("HomeFragmentPopular", response.toString());
                        popularList = new ArrayList<>();
                        try {
                            if (response.getBoolean("result") == true) {

                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                                    String categoryID = jsonObject.getString("categoryID");
                                    String images = jsonObject.getString("images");

                                    JSONArray array = new JSONArray(images);
                                    for (int j = 0; j < array.length(); j++) {
                                        JSONObject object = array.getJSONObject(j);


                                        if (i == 0) {
                                            PopularModel model = new PopularModel();
                                            model.setId(jsonObject.getString("productID"));
                                            model.setName(jsonObject.getString("name"));
                                            model.setPrice(jsonObject.getString("price"));
                                            model.setSellerId(jsonObject.getString("sellerID"));
                                            model.setCategoryId(jsonObject.getString("categoryID"));
                                            model.setCount(jsonObject.getString("product_sold"));
                                            if (!images.equals("")) {
                                                model.setImage(object.getString("image"));
                                                model.setPath(object.getString("path"));
                                                popularList.add(model);
                                            }
                                        }

                                        if (i == 1) {

                                            PopularModel model = new PopularModel();
                                            model.setId(jsonObject.getString("productID"));
                                            model.setCategoryId(jsonObject.getString("categoryID"));
                                            model.setName(jsonObject.getString("name"));
                                            model.setSellerId(jsonObject.getString("sellerID"));
                                            model.setPrice(jsonObject.getString("price"));
                                            model.setCount(jsonObject.getString("product_sold"));
                                            if (!images.equals("")) {
                                                model.setImage(object.getString("image"));
                                                model.setPath(object.getString("path"));
                                                popularList.add(model);
                                            }
                                        }

                                        if (i == 2) {

                                            PopularModel model = new PopularModel();
                                            model.setId(jsonObject.getString("productID"));
                                            model.setName(jsonObject.getString("name"));
                                            model.setSellerId(jsonObject.getString("sellerID"));
                                            model.setCategoryId(jsonObject.getString("categoryID"));
                                            model.setPrice(jsonObject.getString("price"));
                                            model.setCount(jsonObject.getString("product_sold"));
                                            if (!images.equals("")) {
                                                model.setImage(object.getString("image"));
                                                model.setPath(object.getString("path"));
                                                popularList.add(model);
                                            }
                                        }

                                    }


                                }
                                PopularAdapter adapterPopular = new PopularAdapter(context, popularList);
                                binding.rvPopular.setAdapter(adapterPopular);
                            }
                        } catch (JSONException e) {
                            Log.e("HomeFragment", "e: " + e.getMessage());
                        }


                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void getListingData() {
        AndroidNetworking.post(BaseUrl.BASEURL)
                .addBodyParameter("control", SHOW_PREMIUM_PRODUCT)
                .setTag("Show Premium products")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("HomeFragment", "onResponse: " + response.toString());

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

                                        if (i == 0) {
                                            ListingModel model = new ListingModel();
                                            model.setProductId(jsonObject.getString("productID"));
                                            model.setCategoryId(jsonObject.getString("categoryID"));
                                            model.setSellerId(jsonObject.getString("sellerID"));
                                            model.setName(jsonObject.getString("name"));
                                            model.setPrice(jsonObject.getString("price"));
                                            if (!images.equals("")) {
                                                model.setImage(object.getString("image"));
                                                model.setPath(object.getString("path"));
                                                listing.add(model);
                                            }

                                        }

                                        if (i == 1) {


                                            ListingModel model = new ListingModel();
                                            model.setProductId(jsonObject.getString("productID"));
                                            model.setCategoryId(jsonObject.getString("categoryID"));
                                            model.setSellerId(jsonObject.getString("sellerID"));
                                            model.setName(jsonObject.getString("name"));
                                            model.setPrice(jsonObject.getString("price"));
                                            if (!images.equals("")) {
                                                model.setImage(object.getString("image"));
                                                model.setPath(object.getString("path"));
                                                listing.add(model);
                                            }
                                        }

                                        if (i == 2) {

                                            ListingModel model = new ListingModel();
                                            model.setProductId(jsonObject.getString("productID"));
                                            model.setCategoryId(jsonObject.getString("categoryID"));
                                            model.setSellerId(jsonObject.getString("sellerID"));
                                            model.setName(jsonObject.getString("name"));
                                            model.setPrice(jsonObject.getString("price"));
                                            if (!images.equals("")) {
                                                model.setImage(object.getString("image"));
                                                model.setPath(object.getString("path"));
                                                listing.add(model);
                                            }
                                        }


                                    }


                                }
                                ListingAdapter adapterListing = new ListingAdapter(context, listing);
                                binding.rvListing.setAdapter(adapterListing);
                            }
                        } catch (JSONException e) {
                            Log.e("HomeFragment", "e: " + e.getMessage());
                        }


                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void getBrandData() {

        AndroidNetworking.post(BaseUrl.BASEURL)
                .addBodyParameter("control", SHOW_BRAND)
                .setTag("Show Brand Image")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("HomeFragment", response.toString());
                        try {
                            if (response.getBoolean("result") == true) {

                                Gson gson = new Gson();
                                DataImagePremium data = gson.fromJson(response.toString(), DataImagePremium.class);

                                ArrayList arrayList = new ArrayList<DataImagePremium.Data>();

                                if (!data.getData().isEmpty()) {
                                    arrayList.addAll(data.getData());
                                } else {
                                    Toast.makeText(getActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                }


                                PremiumAdapter adapterPremium = new PremiumAdapter(context, arrayList);
                                binding.rvPremium.setAdapter(adapterPremium);
                            }
                        } catch (JSONException e) {
                            Log.e("HomeFragment", "e: " + e.getMessage());
                        }


                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void getMoreData() {
        AndroidNetworking.post(BaseUrl.BASEURL)
                .addBodyParameter("control", SHOW_PRODUCT_MORE)
                .setTag("Show More Love Product")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("HomeFragment", response.toString());
                        try {
                            if (response.getBoolean("result") == true) {

                                Gson gson = new Gson();
                                DataMoreLove dataMore = gson.fromJson(response.toString(), DataMoreLove.class);

                                ArrayList arrayList = new ArrayList<DataMoreLove.Data>();


                                if (!dataMore.getData().isEmpty()) {
                                    for (int i = 0; i < 3; i++) {
                                        arrayList.add(dataMore.getData().get(i));

                                    }
                                    //  arrayList.addAll(dataMore.getData());

                                } else {
                                    Toast.makeText(getActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                }


                                MoreAdapter adapterMore = new MoreAdapter(context, arrayList);
                                binding.rvMore.setAdapter(adapterMore);
                            }
                        } catch (JSONException e) {
                            Log.e("HomeFragment", "e: " + e.getMessage());
                        }


                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private void getRecommendedProduct(){
        AndroidNetworking.post(BaseUrl.BASEURL)
                .addBodyParameter("control", SHOW_RECOMMENDED_PRODUCT)
                .setTag("Show Recommended Product")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("HomeFragment", "onResponse: " + response.toString());
                        try {
                            if (response.getBoolean("result") == true){
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String category_info = jsonObject.getString("category_info");
                                    if (!category_info.equals(null)) {
                                        JSONObject jsonObject1=new JSONObject(category_info);
                                        String catName=jsonObject1.getString("name");
                                        Log.e("HomeFragment", "catName: " +catName);
                                        RecommendedHomeModel model = new RecommendedHomeModel();
                                        if (i == 0) {
                                            String images = jsonObject.getString("images");
                                            JSONArray array = new JSONArray(images);
                                            for (int j = 0; j < array.length(); j++) {

                                                JSONObject object = array.getJSONObject(j);
                                                String image = object.getString("image");
                                                String path = object.getString("path");


                                                if (!images.equals("")) {
                                                    model.setImage(object.getString("image"));
                                                    model.setPath(object.getString("path"));

                                                }
                                            }
                                            model.setProductId(jsonObject.getString("productID"));
                                            model.setSellerId(jsonObject.getString("sellerID"));
                                            model.setCategoryId(jsonObject.getString("categoryID"));
                                            model.setCatName(jsonObject1.getString("name"));
                                            model.setProductName(jsonObject.getString("name"));
                                            model.setPrice(jsonObject.getString("price"));
                                            model.setDescription(jsonObject.getString("description"));

                                            recommendHomeList.add(model);
                                        }

                                        if (i == 1) {

                                            String images = jsonObject.getString("images");
                                            JSONArray array = new JSONArray(images);
                                            for (int j = 0; j < array.length(); j++) {
                                                JSONObject object = array.getJSONObject(j);
                                                if (!images.equals("")) {
                                                    model.setImage(object.getString("image"));
                                                    model.setPath(object.getString("path"));

                                                }
                                            }


                                            model.setProductId(jsonObject.getString("productID"));
                                            model.setSellerId(jsonObject.getString("sellerID"));
                                            model.setCategoryId(jsonObject.getString("categoryID"));
                                            model.setProductName(jsonObject.getString("name"));
                                            model.setCatName(jsonObject1.getString("name"));
                                            model.setPrice(jsonObject.getString("price"));
                                            model.setDescription(jsonObject.getString("description"));
                                            recommendHomeList.add(model);
                                        }

                                        if (i == 2) {

                                            String images = jsonObject.getString("images");
                                            JSONArray array = new JSONArray(images);
                                            for (int j = 0; j < array.length(); j++) {

                                                JSONObject object = array.getJSONObject(j);
                                                String image = object.getString("image");
                                                String path = object.getString("path");


                                                if (!images.equals("")) {
                                                    model.setImage(object.getString("image"));
                                                    model.setPath(object.getString("path"));

                                                }
                                            }

                                            model.setProductId(jsonObject.getString("productID"));
                                            model.setSellerId(jsonObject.getString("sellerID"));
                                            model.setCategoryId(jsonObject.getString("categoryID"));
                                            model.setCatName(jsonObject1.getString("name"));
                                            model.setProductName(jsonObject.getString("name"));
                                            model.setPrice(jsonObject.getString("price"));
                                            model.setDescription(jsonObject.getString("description"));
                                            recommendHomeList.add(model);
                                        }



                                    }

                                }
                                RecommendedHomeAdapter adapterHome = new RecommendedHomeAdapter(context, recommendHomeList);
                                binding.rvRecommend.setAdapter(adapterHome);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });


    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void onBack(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {


                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Do you want to exit?");
                    builder.setPositiveButton("Yes", new android.content.DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(android.content.DialogInterface dialogInterface, int i) {
                            getActivity().finishAffinity();
                        }
                    }).setNegativeButton("No", new android.content.DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(android.content.DialogInterface dialogInterface, int i) {

                            dialogInterface.dismiss();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();


                    return true;
                }
                return false;
            }
        });

    }



}