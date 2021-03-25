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
import com.maestros.bottlehoz.activities.DataPopularProduct;
import com.maestros.bottlehoz.activities.ProductDetailActivity;
import com.maestros.bottlehoz.databinding.RowPopularLayoutBinding;
import com.maestros.bottlehoz.model.PopularModel;
import com.maestros.bottlehoz.utils.AppConstats;
import com.maestros.bottlehoz.utils.SharedHelper;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.MyViewHolder>{


    private Context mContext;
    private List<PopularModel> popularList;

    public PopularAdapter(Context mContext, List<PopularModel> popularList) {
        this.mContext = mContext;
        this.popularList = popularList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowPopularLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PopularModel modelObject = popularList.get(position);
      holder.rowPopularLayoutBinding.txtPrice.setText("₦"+modelObject.getPrice());
      holder.rowPopularLayoutBinding.txtName.setText(modelObject.getName());
       holder.rowPopularLayoutBinding.txtCount.setText(modelObject.getCount()+"Sold");

   try {
       Glide.with(mContext).load(modelObject.getPath()+modelObject.getImage())
               .placeholder(R.drawable.dummy).override(250, 250)
               .diskCacheStrategy(DiskCacheStrategy.ALL)
               .into(holder.rowPopularLayoutBinding.imgPopular);
   }catch (Exception e){

   }
     holder.rowPopularLayoutBinding.cardNew1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             SharedHelper.putKey(mContext, AppConstats.CATEGORYID, modelObject.getCategoryId());
             SharedHelper.putKey(mContext, AppConstats.PRODUCTID, modelObject.getId());
             mContext.startActivity(new Intent(mContext, ProductDetailActivity.class));
         }
     });
    }

    @Override
    public int getItemCount() {
        return popularList == null ? 0 : popularList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowPopularLayoutBinding rowPopularLayoutBinding;
        public MyViewHolder(RowPopularLayoutBinding rowPopularLayoutBinding) {
            super(rowPopularLayoutBinding.getRoot());
            this.rowPopularLayoutBinding = rowPopularLayoutBinding;
        }

    }
}
