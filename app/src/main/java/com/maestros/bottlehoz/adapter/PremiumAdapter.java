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
import com.maestros.bottlehoz.activities.AllListingProductActivity;
import com.maestros.bottlehoz.databinding.RowLayoutFeedBinding;
import com.maestros.bottlehoz.databinding.RowPremiumLayoutBinding;
import com.maestros.bottlehoz.model.Feed;
import com.maestros.bottlehoz.model.PremiumModel;

import java.util.List;

public class PremiumAdapter extends RecyclerView.Adapter<PremiumAdapter.MyViewHolder>{


    private Context mContext;
    private List<PremiumModel> premiumList;

    public PremiumAdapter(Context mContext, List<PremiumModel> premiumList) {
        this.mContext = mContext;
        this.premiumList = premiumList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowPremiumLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       // PremiumModel modelObject = premiumList.get(position);
        try {
            Glide.with(mContext).load(R.drawable.premium)
                    .placeholder(R.drawable.premium).override(520, 520)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.rowPremiumLayoutBinding.imgPremium);
        }catch (Exception e){

        }

        holder.rowPremiumLayoutBinding.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, AllListingProductActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return premiumList == null ? 0 : premiumList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowPremiumLayoutBinding rowPremiumLayoutBinding;
        public MyViewHolder(RowPremiumLayoutBinding rowPremiumLayoutBinding) {
            super(rowPremiumLayoutBinding.getRoot());
            this.rowPremiumLayoutBinding = rowPremiumLayoutBinding;
        }

    }
}
