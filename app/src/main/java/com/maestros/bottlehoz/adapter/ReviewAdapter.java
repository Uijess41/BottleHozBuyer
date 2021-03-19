package com.maestros.bottlehoz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maestros.bottlehoz.databinding.RowChatLayoutBinding;
import com.maestros.bottlehoz.databinding.RowReviewLayoutBinding;
import com.maestros.bottlehoz.model.ChatModel;
import com.maestros.bottlehoz.model.ReviewModel;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {


    private Context mContext;
    private List<ReviewModel> reviewList;

    public ReviewAdapter(Context mContext, List<ReviewModel> reviewList) {
        this.mContext = mContext;
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowReviewLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ReviewModel modelObject = reviewList.get(position);
        holder.rowReviewLayoutBinding.txtTitle.setText(modelObject.getUserName());
        holder.rowReviewLayoutBinding.txtDes.setText(modelObject.getComment());



    }

    @Override
    public int getItemCount() {
        return reviewList == null ? 0 : reviewList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowReviewLayoutBinding rowReviewLayoutBinding;

        public MyViewHolder(RowReviewLayoutBinding rowReviewLayoutBinding) {
            super(rowReviewLayoutBinding.getRoot());
            this.rowReviewLayoutBinding = rowReviewLayoutBinding;
        }

    }
}
