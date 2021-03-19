package com.maestros.bottlehoz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maestros.bottlehoz.databinding.RowIndoxCouponLayoutBinding;
import com.maestros.bottlehoz.databinding.RowIndoxMsgLayoutBinding;
import com.maestros.bottlehoz.model.InboxCouponModel;
import com.maestros.bottlehoz.model.InboxMsgModel;

import java.util.List;

public class InboxCouponAdapter extends RecyclerView.Adapter<InboxCouponAdapter.MyViewHolder>{


    private Context mContext;
    private List<InboxCouponModel> couponList;

    public InboxCouponAdapter(Context mContext, List<InboxCouponModel> couponList) {
        this.mContext = mContext;
        this.couponList = couponList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowIndoxCouponLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        InboxCouponModel modelObject = couponList.get(position);
        holder.rowIndoxCouponLayoutBinding.tvName.setText(modelObject.getUserName());
        holder.rowIndoxCouponLayoutBinding.tvPrice.setText(modelObject.getPrice());
        holder.rowIndoxCouponLayoutBinding.tvPriceOver.setText(modelObject.getOver());
        holder.rowIndoxCouponLayoutBinding.tvDate.setText(modelObject.getDate());
        holder.rowIndoxCouponLayoutBinding.tvTime.setText(modelObject.getTime());
        holder.rowIndoxCouponLayoutBinding.ivCoupon.setImageResource(modelObject.getImage());
    }

    @Override
    public int getItemCount() {
        return couponList == null ? 0 : couponList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowIndoxCouponLayoutBinding rowIndoxCouponLayoutBinding;
        public MyViewHolder(RowIndoxCouponLayoutBinding rowIndoxCouponLayoutBinding) {
            super(rowIndoxCouponLayoutBinding.getRoot());
            this.rowIndoxCouponLayoutBinding = rowIndoxCouponLayoutBinding;
        }

    }
}
