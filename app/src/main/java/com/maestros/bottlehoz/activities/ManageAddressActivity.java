package com.maestros.bottlehoz.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.maestros.bottlehoz.adapter.ManageAddressAdapter;
import com.maestros.bottlehoz.databinding.ActivityManageAddressBinding;
import com.maestros.bottlehoz.model.ManageAddressModel;
import com.maestros.bottlehoz.utils.AppConstats;
import com.maestros.bottlehoz.utils.SharedHelper;

import java.util.ArrayList;

public class ManageAddressActivity extends AppCompatActivity {
ActivityManageAddressBinding binding;
ArrayList<ManageAddressModel>addressModelArrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding=ActivityManageAddressBinding.inflate(getLayoutInflater());
       setContentView(binding.getRoot());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ManageAddressActivity.this,RecyclerView.VERTICAL,false);
        binding.rvAddress.setLayoutManager(layoutManager);
        ManageAddressAdapter addressAdapter=new ManageAddressAdapter(ManageAddressActivity.this,addressModelArrayList);
        binding.rvAddress.setAdapter(addressAdapter);

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
       binding.txtAddress.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               SharedHelper.putKey(getApplicationContext(), AppConstats.SELECTADDRESS, "");
               SharedHelper.putKey(getApplicationContext(), AppConstats.SELECTLONG, "");
               SharedHelper.putKey(getApplicationContext(), AppConstats.SELECTLAT, "");
               startActivity(new Intent(ManageAddressActivity.this, AddAddressActivity.class));
           }
       });
       showAddress();
    }

    private void showAddress(){
        ManageAddressModel manageAddressModel=new ManageAddressModel("Favour Agesa","701 8th Ave \n Brooklyn, NY 11215,USA","Home");
        for (int i = 0; i <1 ; i++) {
            addressModelArrayList.add(manageAddressModel);
        }


    }
}