package com.maestros.bottlehoz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maestros.bottlehoz.databinding.RowIndoxCouponLayoutBinding;
import com.maestros.bottlehoz.databinding.RowIndoxPromoLayoutBinding;
import com.maestros.bottlehoz.model.InboxCouponModel;
import com.maestros.bottlehoz.model.InboxPromotionModel;

import java.util.List;

public class InboxPromotionAdapter extends RecyclerView.Adapter<InboxPromotionAdapter.MyViewHolder>{


    private Context mContext;
    private List<InboxPromotionModel> promotionList;

    public InboxPromotionAdapter(Context mContext, List<InboxPromotionModel> promotionList) {
        this.mContext = mContext;
        this.promotionList = promotionList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowIndoxPromoLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        InboxPromotionModel modelObject = promotionList.get(position);
        holder.rowIndoxPromoLayoutBinding.txtTitle.setText(modelObject.getTitle());
        holder.rowIndoxPromoLayoutBinding.txtMsg.setText(modelObject.getMsg());
        holder.rowIndoxPromoLayoutBinding.txtDes.setText(modelObject.getDescription());
        holder.rowIndoxPromoLayoutBinding.txtTime.setText(modelObject.getDay());
    }

    @Override
    public int getItemCount() {
        return promotionList == null ? 0 : promotionList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowIndoxPromoLayoutBinding rowIndoxPromoLayoutBinding;
        public MyViewHolder(RowIndoxPromoLayoutBinding rowIndoxPromoLayoutBinding) {
            super(rowIndoxPromoLayoutBinding.getRoot());
            this.rowIndoxPromoLayoutBinding = rowIndoxPromoLayoutBinding;
        }

    }
}
