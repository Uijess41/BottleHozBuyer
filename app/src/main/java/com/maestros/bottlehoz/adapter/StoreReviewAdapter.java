package com.maestros.bottlehoz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maestros.bottlehoz.databinding.RowStoreReviewLayoutBinding;
import com.maestros.bottlehoz.model.StoreReviewModel;

import java.util.List;

public class StoreReviewAdapter extends RecyclerView.Adapter<StoreReviewAdapter.MyViewHolder> {


    private Context mContext;
    private List<StoreReviewModel> reviewList;

    public StoreReviewAdapter(Context mContext, List<StoreReviewModel> reviewList) {
        this.mContext = mContext;
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowStoreReviewLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StoreReviewModel modelObject = reviewList.get(position);
        holder.rowStoreReviewLayoutBinding.txtTitle.setText(modelObject.getUserName());
        holder.rowStoreReviewLayoutBinding.txtDes.setText(modelObject.getComment());



    }

    @Override
    public int getItemCount() {
        return reviewList == null ? 0 : reviewList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowStoreReviewLayoutBinding rowStoreReviewLayoutBinding;

        public MyViewHolder(RowStoreReviewLayoutBinding rowStoreReviewLayoutBinding) {
            super(rowStoreReviewLayoutBinding.getRoot());
            this.rowStoreReviewLayoutBinding = rowStoreReviewLayoutBinding;
        }

    }
}
