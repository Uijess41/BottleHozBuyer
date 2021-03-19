package com.maestros.bottlehoz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maestros.bottlehoz.databinding.RowFavoriteDealsLayoutBinding;
import com.maestros.bottlehoz.databinding.RowLayoutFeedBinding;
import com.maestros.bottlehoz.model.FavoriteModel;
import com.maestros.bottlehoz.model.Feed;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>{


    private Context mContext;
    private List<FavoriteModel> favoriteList;

    public FavoriteAdapter(Context mContext, List<FavoriteModel> favoriteList) {
        this.mContext = mContext;
        this.favoriteList = favoriteList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowFavoriteDealsLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FavoriteModel modelObject = favoriteList.get(position);
        holder.rowFavoriteDealsLayoutBinding.txtBuy.setText(modelObject.getProductName());
        holder.rowFavoriteDealsLayoutBinding.txtName.setText(modelObject.getBrandName());
        holder.rowFavoriteDealsLayoutBinding.txtCat.setText(modelObject.getCatName());
        holder.rowFavoriteDealsLayoutBinding.imgFavorite.setImageResource(modelObject.getImage());
    }

    @Override
    public int getItemCount() {
        return favoriteList == null ? 0 : favoriteList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowFavoriteDealsLayoutBinding rowFavoriteDealsLayoutBinding;
        public MyViewHolder(RowFavoriteDealsLayoutBinding rowFavoriteDealsLayoutBinding) {
            super(rowFavoriteDealsLayoutBinding.getRoot());
            this.rowFavoriteDealsLayoutBinding = rowFavoriteDealsLayoutBinding;
        }

    }
}
