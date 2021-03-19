package com.maestros.bottlehoz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maestros.bottlehoz.databinding.RowFavoriteDealsLayoutBinding;
import com.maestros.bottlehoz.databinding.RowProductSimilarLayoutBinding;
import com.maestros.bottlehoz.model.FavoriteModel;
import com.maestros.bottlehoz.model.ProductSimilarModel;

import java.util.List;

public class ProductDetailSimilarAdapter extends RecyclerView.Adapter<ProductDetailSimilarAdapter.MyViewHolder>{


    private Context mContext;
    private List<ProductSimilarModel> similarList;

    public ProductDetailSimilarAdapter(Context mContext, List<ProductSimilarModel> similarList) {
        this.mContext = mContext;
        this.similarList = similarList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowProductSimilarLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ProductSimilarModel modelObject = similarList.get(position);
        holder.productSimilarLayoutBinding.txtProName.setText(modelObject.getProductName());
        holder.productSimilarLayoutBinding.ivSimProduct.setImageResource(modelObject.getImage());
    }

    @Override
    public int getItemCount() {
        return similarList == null ? 0 : similarList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowProductSimilarLayoutBinding productSimilarLayoutBinding;
        public MyViewHolder(RowProductSimilarLayoutBinding productSimilarLayoutBinding) {
            super(productSimilarLayoutBinding.getRoot());
            this.productSimilarLayoutBinding = productSimilarLayoutBinding;
        }

    }
}
