package com.maestros.bottlehoz.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.activities.ShowCatWiseProductActivity;
import com.maestros.bottlehoz.databinding.RowCartOneLayoutBinding;
import com.maestros.bottlehoz.databinding.RowCatproductLayoutBinding;
import com.maestros.bottlehoz.model.CartProductModel;
import com.maestros.bottlehoz.model.CartTittleModel;
import com.maestros.bottlehoz.utils.AppConstats;
import com.maestros.bottlehoz.utils.SharedHelper;

import java.util.ArrayList;
import java.util.List;

public class CartTittleAdapter extends RecyclerView.Adapter<CartTittleAdapter.MyViewHolder> {


    Context mContext;
    List<CartTittleModel> catTittleList;

    LinearLayoutManager layoutManagerProduct;

    public CartTittleAdapter(Context mContext, List<CartTittleModel> catTittleList) {
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

        CartTittleModel modelObject = catTittleList.get(position);
        holder.rowCartOneLayoutBinding.checkitem.setText(modelObject.getTittle());

        ArrayList<CartProductModel> cartProductModelList=new ArrayList<>();

        CartProductModel cartProductModel = new CartProductModel("Moet", "Premium", "8000", "700", "700", R.drawable.store);
        for (int i = 0; i < 3; i++) {

            cartProductModelList.add(cartProductModel);
        }


        layoutManagerProduct = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
        holder.rowCartOneLayoutBinding.rvProdcut.setLayoutManager(layoutManagerProduct);
        holder.rowCartOneLayoutBinding.rvProdcut.setHasFixedSize(true);

        CartProductAdapter cartProductAdapter = new CartProductAdapter(mContext, cartProductModelList);
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

    private void showCart_Product() {



    }
}
