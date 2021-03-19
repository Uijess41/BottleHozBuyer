package com.maestros.bottlehoz.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maestros.bottlehoz.databinding.RowLayoutFeedBinding;
import com.maestros.bottlehoz.model.Feed;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewHolder>{


    private Context mContext;
    private List<Feed> feedList;

    public FeedAdapter(Context mContext, List<Feed> feedList) {
        this.mContext = mContext;
        this.feedList = feedList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowLayoutFeedBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Feed modelObject = feedList.get(position);
        holder.rowLayoutFeedBinding.tvTitle.setText(modelObject.getTitle());
        holder.rowLayoutFeedBinding.tvMessage.setText(modelObject.getMessage());
    }

    @Override
    public int getItemCount() {
        return feedList == null ? 0 : feedList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowLayoutFeedBinding rowLayoutFeedBinding;
        public MyViewHolder(RowLayoutFeedBinding rowLayoutFeedBinding) {
            super(rowLayoutFeedBinding.getRoot());
            this.rowLayoutFeedBinding = rowLayoutFeedBinding;
        }

    }
}
