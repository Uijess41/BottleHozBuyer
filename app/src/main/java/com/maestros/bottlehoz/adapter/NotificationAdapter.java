package com.maestros.bottlehoz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maestros.bottlehoz.databinding.RowLayoutFeedBinding;
import com.maestros.bottlehoz.databinding.RowLayoutNotificationBinding;
import com.maestros.bottlehoz.model.Feed;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder>{


    private Context mContext;
    private List<Feed> feedList;

    public NotificationAdapter(Context mContext, List<Feed> feedList) {
        this.mContext = mContext;
        this.feedList = feedList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowLayoutNotificationBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false));
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
        private RowLayoutNotificationBinding rowLayoutFeedBinding;
        public MyViewHolder(RowLayoutNotificationBinding rowLayoutFeedBinding) {
            super(rowLayoutFeedBinding.getRoot());
            this.rowLayoutFeedBinding = rowLayoutFeedBinding;
        }

    }
}
