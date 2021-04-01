package com.maestros.bottlehoz.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.activities.CartActivity;
import com.maestros.bottlehoz.activities.CartData;
import com.maestros.bottlehoz.activities.ShowCatWiseProductActivity;
import com.maestros.bottlehoz.databinding.RowCartProductLayoutBinding;
import com.maestros.bottlehoz.databinding.RowCatproductLayoutBinding;
import com.maestros.bottlehoz.model.CartProductModel;
import com.maestros.bottlehoz.utils.AppConstats;
import com.maestros.bottlehoz.utils.SharedHelper;

import java.util.List;

public class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.MyViewHolder> {


    Context mContext;
    List<CartData.Data.SellersInfo.Product> catProductList;

    public CartProductAdapter(Context mContext, List<CartData.Data.SellersInfo.Product> catProductList) {
        this.mContext = mContext;
        this.catProductList = catProductList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowCartProductLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CartData.Data.SellersInfo.Product modelObject = catProductList.get(position);
        holder.rowCartProductLayoutBinding.txtPName.setText(modelObject.getProduct_info().getName());
        holder.rowCartProductLayoutBinding.txtMedium.setText(modelObject.getProduct_info().getDescription());
        holder.rowCartProductLayoutBinding.txtPrice.setText(modelObject.getProduct_info().getPrice());


        for (int i = 0; i < modelObject.getProduct_info().getImages().size(); i++) {

            Log.e("CartProductAdapter", "onBindViewHolder: " + modelObject.getProduct_info().getImages().size());

            try {
                Glide.with(mContext).load(modelObject.getProduct_info().getImages().get(i).getPath() + modelObject.getProduct_info().getImages().get(i).getImage())
                        .placeholder(R.drawable.imageb).override(250, 250)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.rowCartProductLayoutBinding.imgPremium);
            } catch (Exception e) {

            }

        }


        holder.rowCartProductLayoutBinding.checkCategory.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (holder.rowCartProductLayoutBinding.checkCategory.isChecked()) {
                    if (mContext instanceof CartActivity) {
                        //  ((CartActivity)mContext).draw(modelObject.getiD(),position);
                    }
                } else {
                    if (mContext instanceof CartActivity) {
                        ((CartActivity) mContext).draw("0", position);
                    }
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return catProductList == null ? 0 : catProductList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowCartProductLayoutBinding rowCartProductLayoutBinding;

        public MyViewHolder(RowCartProductLayoutBinding rowCartProductLayoutBinding) {
            super(rowCartProductLayoutBinding.getRoot());
            this.rowCartProductLayoutBinding = rowCartProductLayoutBinding;
        }

    }
}
