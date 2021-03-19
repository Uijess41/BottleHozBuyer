package com.maestros.bottlehoz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.databinding.RowCatproductLayoutBinding;
import com.maestros.bottlehoz.databinding.RowListingLayoutBinding;
import com.maestros.bottlehoz.model.CategoryModel;
import com.maestros.bottlehoz.model.ListingModel;

import java.util.List;

public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.MyViewHolder>{


    private Context mContext;
    private List<ListingModel> List;

    public ListingAdapter(Context mContext, List<ListingModel> List) {
        this.mContext = mContext;
        this.List = List;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowListingLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ListingModel modelObject = List.get(position);
      holder.rowListingLayoutBinding.txtListName.setText(modelObject.getName());
      holder.rowListingLayoutBinding.txtPrice.setText(modelObject.getPrice());

   try {
       Glide.with(mContext).load(R.drawable.imageb)
               .placeholder(R.drawable.imageb).override(150, 150)
               .diskCacheStrategy(DiskCacheStrategy.ALL)
               .into(holder.rowListingLayoutBinding.imgPremium);
   }catch (Exception e){

   }

    }

    @Override
    public int getItemCount() {
        return List == null ? 0 : List.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowListingLayoutBinding rowListingLayoutBinding;
        public MyViewHolder(RowListingLayoutBinding rowListingLayoutBinding) {
            super(rowListingLayoutBinding.getRoot());
            this.rowListingLayoutBinding = rowListingLayoutBinding;
        }

    }
}
