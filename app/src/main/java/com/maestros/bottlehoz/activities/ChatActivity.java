package com.maestros.bottlehoz.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.adapter.ChatAdapter;
import com.maestros.bottlehoz.adapter.ProductDetailSimilarAdapter;
import com.maestros.bottlehoz.databinding.ActivityChatBinding;
import com.maestros.bottlehoz.databinding.ActivityProductDetailBinding;
import com.maestros.bottlehoz.model.ChatModel;
import com.maestros.bottlehoz.model.ProductSimilarModel;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    ActivityChatBinding binding;
    private ChatAdapter adapter;
    private List<ChatModel> chatList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        adapter = new ChatAdapter(ChatActivity.this, chatList);

        binding.rvChat.setLayoutManager(new LinearLayoutManager(ChatActivity.this, RecyclerView.VERTICAL,false));
        binding.rvChat.setAdapter(adapter);
        getChatData();
    }

    private void getChatData() {
        ChatModel chatObj = new ChatModel("Hello user whats up", "Hey i am fine");

        for (int i = 0; i < 4; i++) {
            chatList.add(chatObj);
        }

    }
}