package com.maestros.bottlehoz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.maestros.bottlehoz.databinding.RowLayoutSellerHomeMenuBinding;
import com.maestros.bottlehoz.model.MenuItemModel;

import java.util.List;

public class SellerMenuAdapter extends RecyclerView.Adapter<SellerMenuAdapter.MyViewHolder>{


    private Context context;
    private List<MenuItemModel> feedList;

    public SellerMenuAdapter(Context context, List<MenuItemModel> feedList) {
        this.context = context;
        this.feedList = feedList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowLayoutSellerHomeMenuBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MenuItemModel modelObject = feedList.get(position);
        holder.binding.tvMenuName.setText(modelObject.getMenuTitle());
        holder.binding.tvRecords.setText(modelObject.getRecordscount());
        Glide.with(context).load(modelObject.getMenuImage()).into(holder.binding.ivIcon);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (modelObject.getMenuTitle().equals("Profile")){
                   // loadFragment(new SellerProfileFragment());
                }else if (modelObject.getMenuTitle().equals("Settings")){
                   // context.startActivity(new Intent(context, SellerSettingActivity.class));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return feedList == null ? 0 : feedList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowLayoutSellerHomeMenuBinding binding;
        public MyViewHolder(RowLayoutSellerHomeMenuBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }

    private void loadFragment(Fragment fragment) {
       /* FragmentTransaction transaction = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);
        ((AppCompatActivity)context).getSupportFragmentManager().popBackStack();
        transaction.commit();*/
    }
}
