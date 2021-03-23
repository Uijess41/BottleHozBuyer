package com.maestros.bottlehoz.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
import com.maestros.bottlehoz.activities.DataDiscount;
import com.maestros.bottlehoz.activities.DataImagePremium;
import com.maestros.bottlehoz.activities.DataPopularProduct;
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
import com.maestros.bottlehoz.adapter.SliderAdapterExample;
import com.maestros.bottlehoz.databinding.FragmentHomeBinding;
import com.maestros.bottlehoz.model.DiscountPercentModel;
import com.maestros.bottlehoz.model.ListingModel;
import com.maestros.bottlehoz.model.MoreModel;
import com.maestros.bottlehoz.model.PopularModel;
import com.maestros.bottlehoz.model.PremiumModel;
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
import static com.maestros.bottlehoz.retrofit.BaseUrl.SHOW_PRODUCT_MORE;
import static com.maestros.bottlehoz.retrofit.BaseUrl.SHOW_PRODUCT_POPULAR;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private View view;

    SliderAdapterExample sliderAdapter;
    List<SliderModel> listOfSlider = new ArrayList<>();



    private ListingAdapter adapterListing;
    private List<ListingModel> listing=new ArrayList<>();


    private Context context;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentHomeBinding.inflate(getLayoutInflater(),container,false);
        view=binding.getRoot();


        binding.sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        binding.sliderView.setSliderTransformAnimation(SliderAnimations.CUBEOUTROTATIONTRANSFORMATION);
        binding.sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        binding.sliderView.setIndicatorSelectedColor(Color.DKGRAY);
        binding.sliderView.setIndicatorUnselectedColor(Color.LTGRAY);
        binding.sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        binding.sliderView.startAutoCycle();




        view=binding.getRoot();
        context=getActivity();




        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        binding.rvDiscount.setLayoutManager(mLayoutManager);



      binding.txtView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(getActivity(), RecommendedActivity.class));
          }
      });

        binding.txtCatView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ShowAllCategoryActivity
                        .class));
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        binding.rvCategory.setLayoutManager(layoutManager);






        RecyclerView.LayoutManager layoutManagerPop = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        binding.rvPopular.setLayoutManager(layoutManagerPop);




        adapterListing = new ListingAdapter(context, listing);

        RecyclerView.LayoutManager layoutManagerList = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        binding.rvListing.setLayoutManager(layoutManagerList);
        binding.rvListing.setAdapter(adapterListing);




        RecyclerView.LayoutManager layoutManagerPremium = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        binding.rvPremium.setLayoutManager(layoutManagerPremium);





        binding.rvMore.setLayoutManager(new GridLayoutManager(getActivity(),2));




        Connectivity connectivity = new Connectivity(getActivity());
        if (connectivity.isOnline()){
            getMoreData();
            getDiscountData();
            getCategoryData();
            getPopularData();
            getListingData();
            getPremiumData();
            showBanner();
        }
        else {
            Toast.makeText(getActivity(),"Please check internet connection",Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void showBanner(){

        CustomDialog dialog=new CustomDialog();
        dialog.showDialog(R.layout.progress_layout,getActivity());
        AndroidNetworking.post(BaseUrl.BASEURL)
                .addBodyParameter("control",SHOW_BANNER )
                .setTag("SHOWBANNER")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("response", response+"");
                        listOfSlider=new ArrayList<>();
                        dialog.hideDialog();
                        try {
                            if (response.getString("result").equals("true")){
                                String data=response.getString("data");
                                JSONArray jsonArray=new JSONArray(data);

                                for (int i = 0; i <jsonArray.length() ; i++) {
                                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                                    SliderModel model=new SliderModel();

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
                .addBodyParameter("control",SHOW_DISCOUNT)
                .setTag("Show Discount")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("HomeFragment", response.toString());
                        try {
                            if (response.getBoolean("result") == true){

                                Gson gson = new Gson();
                                DataDiscount dataDiscount = gson.fromJson(response.toString(), DataDiscount.class);

                                ArrayList arrayList = new ArrayList<DataDiscount.Data>();
                                if (!dataDiscount.getData().isEmpty()){
                                    arrayList.addAll(dataDiscount.getData());
                                }else {
                                    Toast.makeText(getActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                }



                                DiscountAdapter  adapter = new DiscountAdapter(context, arrayList);
                                binding.rvDiscount.setAdapter(adapter);
                            }
                        } catch (JSONException e) {
                            Log.e("HomeFragment", "e: " +e.getMessage());
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
                .addBodyParameter("control",SHOW_CATEGORY)
                .setTag("Show Category")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("HomeFragment", response.toString());
                        try {
                            if (response.getBoolean("result") == true){

                                     Gson gson = new Gson();
                                    CategoryHome dataCategory = gson.fromJson(response.toString(), CategoryHome.class);

                                ArrayList arrayList = new ArrayList<CategoryHome.Data>();
                                if (!dataCategory.getData().isEmpty()){
                                    arrayList.addAll(dataCategory.getData());
                                }else {
                                    Toast.makeText(getActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                }



                                CategorytAdapter   adapterCat = new CategorytAdapter(context, arrayList);
                                binding.rvCategory.setAdapter(adapterCat);
                            }
                        } catch (JSONException e) {
                            Log.e("HomeFragment", "e: " +e.getMessage());
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
                .addBodyParameter("control",SHOW_PRODUCT_POPULAR)
                .setTag("Show Popular Product")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("HomeFragment", response.toString());
                        try {
                            if (response.getBoolean("result") == true){

                                Gson gson = new Gson();
                                DataPopularProduct dataPopular = gson.fromJson(response.toString(), DataPopularProduct.class);

                                ArrayList arrayList = new ArrayList<DataPopularProduct.Data>();

                                if (!dataPopular.getData().isEmpty()){
                                    arrayList.addAll(dataPopular.getData());

                                }else {
                                    Toast.makeText(getActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                }


                                PopularAdapter   adapterPopular = new PopularAdapter(context, arrayList);
                                binding.rvPremium.setAdapter(adapterPopular);
                            }
                        } catch (JSONException e) {
                            Log.e("HomeFragment", "e: " +e.getMessage());
                        }


                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void getListingData() {
        ListingModel listObj=new ListingModel("Smirnoff","N1,800",R.drawable.list);

        for (int i = 0; i <3 ; i++) {
            listing.add(listObj);
        }


    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void getPremiumData() {

        AndroidNetworking.post(BaseUrl.BASEURL)
                .addBodyParameter("control",SHOW_BRAND)
                .setTag("Show Premium Image")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("HomeFragment", response.toString());
                        try {
                            if (response.getBoolean("result") == true){

                                Gson gson = new Gson();
                                DataImagePremium data = gson.fromJson(response.toString(), DataImagePremium.class);

                                ArrayList arrayList = new ArrayList<DataImagePremium.Data>();

                                if (!data.getData().isEmpty()){
                                    arrayList.addAll(data.getData());
                                }
                                else {
                                    Toast.makeText(getActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                }


                                PremiumAdapter   adapterPremium = new PremiumAdapter(context, arrayList);
                                binding.rvPremium.setAdapter(adapterPremium);
                            }
                        } catch (JSONException e) {
                            Log.e("HomeFragment", "e: " +e.getMessage());
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
                .addBodyParameter("control",SHOW_PRODUCT_MORE)
                .setTag("Show More Love Product")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("HomeFragment", response.toString());
                        try {
                            if (response.getBoolean("result") == true){

                                Gson gson = new Gson();
                                DataMoreLove dataMore = gson.fromJson(response.toString(), DataMoreLove.class);

                                ArrayList arrayList = new ArrayList<DataMoreLove.Data>();


                                    if (!dataMore.getData().isEmpty()){
                                        for (int i = 0; i <4 ; i++) {
                                          arrayList.add(dataMore.getData().get(i));

                                        }
                                      //  arrayList.addAll(dataMore.getData());

                                    }else {
                                        Toast.makeText(getActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                    }




                                MoreAdapter  adapterMore = new MoreAdapter(context, arrayList);
                                binding.rvMore.setAdapter(adapterMore);
                            }
                        } catch (JSONException e) {
                            Log.e("HomeFragment", "e: " +e.getMessage());
                        }


                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }
}