package com.maestros.bottlehoz.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.activities.DataDiscount;
import com.maestros.bottlehoz.activities.DealOftheDayActivity;
import com.maestros.bottlehoz.databinding.RowDiscountLayoutBinding;
import com.maestros.bottlehoz.databinding.RowLayoutFeedBinding;
import com.maestros.bottlehoz.model.DiscountPercentModel;
import com.maestros.bottlehoz.model.Feed;

import java.util.List;

public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.MyViewHolder>{


    private Context mContext;
    private List<DataDiscount.Data> discountList;

    public DiscountAdapter(Context mContext, List<DataDiscount.Data> discountList) {
        this.mContext = mContext;
        this.discountList = discountList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowDiscountLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataDiscount.Data modelObject = discountList.get(position);
      holder.rowDiscountLayoutBinding.txtPercent.setText(modelObject.getCoupon_value()+"%");


        Log.e("DiscountAdapter", "onBindViewHolder: " +modelObject.getPath()+modelObject.getImage());
   try {
       Glide.with(mContext).load(modelObject.getPath()+modelObject.getImage())
               .error(R.drawable.dummy).override(250, 250)
               .diskCacheStrategy(DiskCacheStrategy.ALL)
               .into(holder.rowDiscountLayoutBinding.imgDiscount);
   }catch (Exception e){

   }

   holder.rowDiscountLayoutBinding.rlSelect.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           mContext.startActivity(new Intent(mContext, DealOftheDayActivity.class));
       }
   });

    }

    @Override
    public int getItemCount() {
        return discountList == null ? 0 : discountList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowDiscountLayoutBinding rowDiscountLayoutBinding;
        public MyViewHolder(RowDiscountLayoutBinding rowDiscountLayoutBinding) {
            super(rowDiscountLayoutBinding.getRoot());
            this.rowDiscountLayoutBinding = rowDiscountLayoutBinding;
        }

    }
}
