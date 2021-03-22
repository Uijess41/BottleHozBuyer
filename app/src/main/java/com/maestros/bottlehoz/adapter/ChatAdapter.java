package com.maestros.bottlehoz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maestros.bottlehoz.databinding.RowChatLayoutBinding;
import com.maestros.bottlehoz.model.ChatModel;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {


    private Context mContext;
    private List<ChatModel> chatList;

    public ChatAdapter(Context mContext, List<ChatModel> chatList) {
        this.mContext = mContext;
        this.chatList = chatList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowChatLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ChatModel modelObject = chatList.get(position);
        holder.rowChatLayoutBinding.txTitle.setText(modelObject.getMsgReceive());
        holder.rowChatLayoutBinding.txUserMessage.setText(modelObject.getMsgSend());



    }

    @Override
    public int getItemCount() {
        return chatList == null ? 0 : chatList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowChatLayoutBinding rowChatLayoutBinding;

        public MyViewHolder(RowChatLayoutBinding rowChatLayoutBinding) {
            super(rowChatLayoutBinding.getRoot());
            this.rowChatLayoutBinding = rowChatLayoutBinding;
        }

    }
}
