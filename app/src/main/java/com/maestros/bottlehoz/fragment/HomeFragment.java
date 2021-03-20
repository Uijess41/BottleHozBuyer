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

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.activities.HomeActivity;
import com.maestros.bottlehoz.activities.RecommendedActivity;
import com.maestros.bottlehoz.adapter.CategorytAdapter;
import com.maestros.bottlehoz.adapter.DiscountAdapter;
import com.maestros.bottlehoz.adapter.FeedAdapter;
import com.maestros.bottlehoz.adapter.ListingAdapter;
import com.maestros.bottlehoz.adapter.MoreAdapter;
import com.maestros.bottlehoz.adapter.PopularAdapter;
import com.maestros.bottlehoz.adapter.PremiumAdapter;
import com.maestros.bottlehoz.adapter.SliderAdapterExample;
import com.maestros.bottlehoz.databinding.ActivityRecommendedBinding;
import com.maestros.bottlehoz.databinding.FragmentFeedBinding;
import com.maestros.bottlehoz.databinding.FragmentHomeBinding;
import com.maestros.bottlehoz.model.CategoryModel;
import com.maestros.bottlehoz.model.DiscountPercentModel;
import com.maestros.bottlehoz.model.Feed;
import com.maestros.bottlehoz.model.ListingModel;
import com.maestros.bottlehoz.model.MoreModel;
import com.maestros.bottlehoz.model.PopularModel;
import com.maestros.bottlehoz.model.PremiumModel;
import com.maestros.bottlehoz.model.SliderModel;
import com.maestros.bottlehoz.retrofit.BaseUrl;
import com.maestros.bottlehoz.utils.ProgressBarCustom.CustomDialog;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.maestros.bottlehoz.retrofit.BaseUrl.OTPVERIFY;
import static com.maestros.bottlehoz.retrofit.BaseUrl.SHOW_BANNER;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private View view;

    SliderAdapterExample sliderAdapter;
    List<SliderModel> listOfSlider = new ArrayList<>();

    private DiscountAdapter adapter;
    private List<DiscountPercentModel> percentList=new ArrayList<>();

    private CategorytAdapter adapterCat;
    private List<CategoryModel> categoryList=new ArrayList<>();


    private PopularAdapter adapterPopular;
    private List<PopularModel> popularList=new ArrayList<>();


    private ListingAdapter adapterListing;
    private List<ListingModel> listing=new ArrayList<>();


    private PremiumAdapter adapterPremium;
    private List<PremiumModel> premiumList=new ArrayList<>();

    private MoreAdapter adapterMore;
    private List<MoreModel> moreList=new ArrayList<>();

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


        adapter = new DiscountAdapter(context, percentList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        binding.rvDiscount.setLayoutManager(mLayoutManager);
        binding.rvDiscount.setAdapter(adapter);


      binding.txtView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(getActivity(), RecommendedActivity.class));
          }
      });

        adapterCat = new CategorytAdapter(context, categoryList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        binding.rvCategory.setLayoutManager(layoutManager);
        binding.rvCategory.setAdapter(adapterCat);



        adapterPopular = new PopularAdapter(context, popularList);

        RecyclerView.LayoutManager layoutManagerPop = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        binding.rvPopular.setLayoutManager(layoutManagerPop);
        binding.rvPopular.setAdapter(adapterPopular);



        adapterListing = new ListingAdapter(context, listing);

        RecyclerView.LayoutManager layoutManagerList = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        binding.rvListing.setLayoutManager(layoutManagerList);
        binding.rvListing.setAdapter(adapterListing);


        adapterPremium = new PremiumAdapter(context, premiumList);

        RecyclerView.LayoutManager layoutManagerPremium = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        binding.rvPremium.setLayoutManager(layoutManagerPremium);
        binding.rvPremium.setAdapter(adapterPremium);


        adapterMore = new MoreAdapter(context, moreList);

        binding.rvMore.setLayoutManager(new GridLayoutManager(getActivity(),2));
        binding.rvMore.setAdapter(adapterMore);


        getMoreData();
        getDiscountData();
        getCategoryData();
        getPopularData();
        getListingData();
        getPremiumData();
        showBanner();
        return view;
    }



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

    private void getDiscountData() {
        DiscountPercentModel percentObj=new DiscountPercentModel("-15 %",R.drawable.image);

        for (int i = 0; i <3 ; i++) {
            percentList.add(percentObj);
        }


    }


    private void getCategoryData() {
        CategoryModel catObj=new CategoryModel("Whiskey",R.drawable.imageb);

        for (int i = 0; i <5 ; i++) {
            categoryList.add(catObj);
        }


    }

    private void getPopularData() {
        PopularModel popularObj=new PopularModel("N1,800","1200 Sold",R.drawable.imageb);

        for (int i = 0; i <3 ; i++) {
            popularList.add(popularObj);
        }


    }

    private void getListingData() {
        ListingModel listObj=new ListingModel("Smirnoff","N1,800",R.drawable.list);

        for (int i = 0; i <3 ; i++) {
            listing.add(listObj);
        }


    }
    private void getPremiumData() {
        PremiumModel premiumObj=new PremiumModel(R.drawable.premium);

        for (int i = 0; i <3 ; i++) {
            premiumList.add(premiumObj);
        }


    }


    private void getMoreData() {


      MoreModel moreObj=new MoreModel("N1,800","98 Sold |","4.2","Berio Wishkey",R.drawable.more);

        for (int i = 0; i <4 ; i++) {
            moreList.add(moreObj);
        }


    }
}