package com.maestros.bottlehoz.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.activities.BottomNavActivity;
import com.maestros.bottlehoz.adapter.SellerMenuAdapter;
import com.maestros.bottlehoz.databinding.FragmentMyAccountScreenBinding;
import com.maestros.bottlehoz.model.MenuItemModel;

import java.util.List;
import java.util.Optional;

import kotlin.collections.ArrayDeque;

public class MyAccountScreen extends Fragment {
    private SellerMenuAdapter adapterSeller;
    private List<MenuItemModel> listMenu;
    FragmentMyAccountScreenBinding binding;
    View view;
    Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentMyAccountScreenBinding.inflate(getLayoutInflater(),container,false);
        view=binding.getRoot();
        context=getActivity();
        listMenu=new ArrayDeque<>();
        adapterSeller = new SellerMenuAdapter(context, listMenu);
        binding.rvMenuGrid.setLayoutManager(new GridLayoutManager(context, 3));
        binding.rvMenuGrid.setAdapter(adapterSeller);
        binding.rlPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileFragment fragment = new ProfileFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        binding.ivOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomNavActivity.drawer.openDrawer(GravityCompat.START);
            }
        });
        getData();
        return view;
    }
    private void getData() {
        MenuItemModel a = new MenuItemModel(R.drawable.message, "Messages","","120");
        listMenu.add(a);a = new MenuItemModel(R.drawable.message, "Rewards","","05");
        listMenu.add(a);a = new MenuItemModel(R.drawable.message, "Following","","280");
        listMenu.add(a);a = new MenuItemModel(R.drawable.message, "Promotions","","16");
        listMenu.add(a);a = new MenuItemModel(R.drawable.message, "Coupons","","10");
        listMenu.add(a);a = new MenuItemModel(R.drawable.message, "Cards","","100");
        listMenu.add(a);
        adapterSeller.notifyDataSetChanged();
    }

}