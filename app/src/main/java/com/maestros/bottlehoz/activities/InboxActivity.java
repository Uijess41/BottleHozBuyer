package com.maestros.bottlehoz.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.adapter.FavoriteAdapter;
import com.maestros.bottlehoz.adapter.InboxCouponAdapter;
import com.maestros.bottlehoz.adapter.InboxMsgAdapter;
import com.maestros.bottlehoz.adapter.InboxPromotionAdapter;
import com.maestros.bottlehoz.databinding.ActivityFavoriteBinding;
import com.maestros.bottlehoz.databinding.ActivityInboxBinding;
import com.maestros.bottlehoz.model.FavoriteModel;
import com.maestros.bottlehoz.model.InboxCouponModel;
import com.maestros.bottlehoz.model.InboxMsgModel;
import com.maestros.bottlehoz.model.InboxPromotionModel;

import java.util.ArrayList;
import java.util.List;

public class InboxActivity extends AppCompatActivity {
      ActivityInboxBinding binding;

    private InboxMsgAdapter adapter;
    private List<InboxMsgModel> inboxList = new ArrayList<>();


    private InboxCouponAdapter adapterCoupon;
    private List<InboxCouponModel> couponList = new ArrayList<>();


    private InboxPromotionAdapter adapterPromo;
    private List<InboxPromotionModel> promoList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInboxBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.txtCart.setPaintFlags(binding.txtCart.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(InboxActivity.this, RecyclerView.VERTICAL, false);
        binding.rvMsg.setLayoutManager(layoutManager);
        getInboxMsgData();



       binding.ivedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InboxActivity.this, NewMsgActivity.class));
            }
        });



        binding.txtMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnMessage.setVisibility(View.VISIBLE);
                binding.txtCoupon.setVisibility(View.VISIBLE);
                binding.txtPromo.setVisibility(View.VISIBLE);
                binding.txtMessage.setVisibility(View.GONE);
                binding.btnPromo.setVisibility(View.GONE);
                binding.btnCoupon.setVisibility(View.GONE);
                getInboxMsgData();
            }
        });



        binding.txtCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnMessage.setVisibility(View.GONE);
                binding.txtCoupon.setVisibility(View.GONE);
                binding.txtPromo.setVisibility(View.VISIBLE);
                binding.txtMessage.setVisibility(View.VISIBLE);
                binding.btnPromo.setVisibility(View.GONE);
                binding.btnCoupon.setVisibility(View.VISIBLE);
                getInboxCouponData();
            }
        });

        binding.txtPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnMessage.setVisibility(View.GONE);
                binding.txtCoupon.setVisibility(View.VISIBLE);
                binding.txtPromo.setVisibility(View.GONE);
                binding.txtMessage.setVisibility(View.VISIBLE);
                binding.btnPromo.setVisibility(View.VISIBLE);
                binding.btnCoupon.setVisibility(View.GONE);
                getInboxPromoData();
            }
        });

    }



    private void getInboxMsgData() {


        InboxMsgModel favObj = new InboxMsgModel("jack Gamble", "Lorem Ipsum is simply dummy", "5 min ago", R.drawable.demo);

        for (int i = 0; i < 4; i++) {
            inboxList.add(favObj);
        }
        adapter = new InboxMsgAdapter(InboxActivity.this, inboxList);
        binding.rvMsg.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    private void getInboxCouponData() {


        InboxCouponModel couponObj = new InboxCouponModel("Breakfast Corner", "N 1500", "4am to 10Pm","Oct 20 - 25","",R.drawable.brk);

        for (int i = 0; i < 4; i++) {
            couponList.add(couponObj);
        }
        adapterCoupon = new InboxCouponAdapter(InboxActivity.this, couponList);
        binding.rvMsg.setAdapter(adapterCoupon);
        adapterCoupon.notifyDataSetChanged();

    }
    private void getInboxPromoData() {


        InboxPromotionModel promoObj = new InboxPromotionModel("Are you hungery", "Chinese in Restaurant", "Don't wait visit us and enjoy the lowest rates before these raises again","Today");

        for (int i = 0; i < 4; i++) {
            promoList.add(promoObj);
        }
        adapterPromo = new InboxPromotionAdapter(InboxActivity.this, promoList);
        binding.rvMsg.setAdapter(adapterPromo);
        adapterPromo.notifyDataSetChanged();

    }
}