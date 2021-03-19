package com.maestros.bottlehoz.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maestros.bottlehoz.activities.ChatActivity;
import com.maestros.bottlehoz.activities.ProductDetailActivity;
import com.maestros.bottlehoz.databinding.RowFavoriteDealsLayoutBinding;
import com.maestros.bottlehoz.databinding.RowIndoxMsgLayoutBinding;
import com.maestros.bottlehoz.model.FavoriteModel;
import com.maestros.bottlehoz.model.InboxMsgModel;

import java.util.List;

public class InboxMsgAdapter extends RecyclerView.Adapter<InboxMsgAdapter.MyViewHolder>{


    private Context mContext;
    private List<InboxMsgModel> msgList;

    public InboxMsgAdapter(Context mContext, List<InboxMsgModel> msgList) {
        this.mContext = mContext;
        this.msgList = msgList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowIndoxMsgLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        InboxMsgModel modelObject = msgList.get(position);
        holder.rowIndoxMsgLayoutBinding.tvTitle.setText(modelObject.getUserName());
        holder.rowIndoxMsgLayoutBinding.tvMessage.setText(modelObject.getDescription());
        holder.rowIndoxMsgLayoutBinding.tvView.setText(modelObject.getTime());
        holder.rowIndoxMsgLayoutBinding.roundedImageView.setImageResource(modelObject.getImage());


        holder.rowIndoxMsgLayoutBinding.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, ChatActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return msgList == null ? 0 : msgList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowIndoxMsgLayoutBinding rowIndoxMsgLayoutBinding;
        public MyViewHolder(RowIndoxMsgLayoutBinding rowIndoxMsgLayoutBinding) {
            super(rowIndoxMsgLayoutBinding.getRoot());
            this.rowIndoxMsgLayoutBinding = rowIndoxMsgLayoutBinding;
        }

    }
}
