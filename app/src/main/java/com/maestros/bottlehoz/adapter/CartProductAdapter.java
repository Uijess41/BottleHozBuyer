package com.maestros.bottlehoz.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.activities.ShowCatWiseProductActivity;
import com.maestros.bottlehoz.databinding.RowCartProductLayoutBinding;
import com.maestros.bottlehoz.databinding.RowCatproductLayoutBinding;
import com.maestros.bottlehoz.model.CartProductModel;
import com.maestros.bottlehoz.utils.AppConstats;
import com.maestros.bottlehoz.utils.SharedHelper;

import java.util.List;

public class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.MyViewHolder> {


     Context mContext;
      List<CartProductModel> catProductList;

    public CartProductAdapter(Context mContext, List<CartProductModel> catProductList) {
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
        CartProductModel modelObject = catProductList.get(position);
        holder.rowCartProductLayoutBinding.txtPName.setText(modelObject.getName());
        holder.rowCartProductLayoutBinding.txtMedium.setText(modelObject.getDescription());
        holder.rowCartProductLayoutBinding.txtPrice.setText(modelObject.getPrice());
  /*  holder.rowCartProductLayoutBinding.tvDelFee.setText(modelObject.getDeliveryFee());
     holder.rowCartProductLayoutBinding.tvAmount.setText(modelObject.getCartAmount());*/
        holder.rowCartProductLayoutBinding.imgPremium.setImageResource(modelObject.getImage());


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
