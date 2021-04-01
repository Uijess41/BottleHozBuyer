package com.maestros.bottlehoz.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.activities.CartActivity;
import com.maestros.bottlehoz.activities.CartData;
import com.maestros.bottlehoz.activities.ShowCatWiseProductActivity;
import com.maestros.bottlehoz.databinding.RowCartOneLayoutBinding;
import com.maestros.bottlehoz.databinding.RowCatproductLayoutBinding;
import com.maestros.bottlehoz.model.CartProductModel;
import com.maestros.bottlehoz.model.CartTittleModel;
import com.maestros.bottlehoz.retrofit.BaseUrl;
import com.maestros.bottlehoz.utils.AppConstats;
import com.maestros.bottlehoz.utils.SharedHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.maestros.bottlehoz.retrofit.BaseUrl.SHOW_CART;

public class CartTittleAdapter extends RecyclerView.Adapter<CartTittleAdapter.MyViewHolder> {


    Context mContext;
    List<CartData.Data> catTittleList;
    List<CartData.Data> cartProductModelList;

    LinearLayoutManager layoutManagerProduct;

    public CartTittleAdapter(Context mContext, List<CartData.Data> catTittleList) {
        this.mContext = mContext;
        this.catTittleList = catTittleList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowCartOneLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CartData.Data modelObject = catTittleList.get(position);
        holder.rowCartOneLayoutBinding.checkitemAll.setText(modelObject.getSellers_info().getName());

        Integer i = new Integer(modelObject.getDelivery_charge());
        StringBuilder sb_deliverCharge = new StringBuilder(); // or StringBuffer
        sb_deliverCharge.append(i);
        holder.rowCartOneLayoutBinding.tvDelFee.setText(sb_deliverCharge);

        Integer j = new Integer(modelObject.getGrand_total());
        StringBuilder sb_grandTotal = new StringBuilder(); // or StringBuffer
        sb_grandTotal.append(j);
        holder.rowCartOneLayoutBinding.tvAmount.setText(sb_grandTotal);


        /*  ArrayList<CartProductModel> cartProductModelList=new ArrayList<>();

        CartProductModel cartProductModel = new CartProductModel("Moet", "Premium", "8000", "700", "700", R.drawable.store);
        for (int i = 0; i < 3; i++) {
            cartProductModelList.add(cartProductModel);
        }
*/

        ArrayList<String> arrayList = new ArrayList<>();
        for (int k = 0; k < modelObject.getSellers_info().getProducts().size(); k++) {
            arrayList.add(modelObject.getSellers_info().getProducts().get(k).getCartID());
        }



        holder.rowCartOneLayoutBinding.checkitemAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    if (mContext instanceof CartActivity) {
                        ((CartActivity) mContext).listItem(arrayList, position);
                    }

                }else {

                }

            }
        });


        layoutManagerProduct = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
        holder.rowCartOneLayoutBinding.rvProdcut.setLayoutManager(layoutManagerProduct);
        holder.rowCartOneLayoutBinding.rvProdcut.setHasFixedSize(true);

        CartProductAdapter cartProductAdapter = new CartProductAdapter(mContext, modelObject.getSellers_info().getProducts());
        holder.rowCartOneLayoutBinding.rvProdcut.setAdapter(cartProductAdapter);


    }

    @Override
    public int getItemCount() {
        return catTittleList == null ? 0 : catTittleList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowCartOneLayoutBinding rowCartOneLayoutBinding;

        public MyViewHolder(RowCartOneLayoutBinding rowCartOneLayoutBinding) {
            super(rowCartOneLayoutBinding.getRoot());
            this.rowCartOneLayoutBinding = rowCartOneLayoutBinding;
        }

    }



}
