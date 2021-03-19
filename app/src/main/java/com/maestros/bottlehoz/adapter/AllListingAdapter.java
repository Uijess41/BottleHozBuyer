package com.maestros.bottlehoz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.databinding.RowAllListingLayoutBinding;
import com.maestros.bottlehoz.databinding.RowCatproductLayoutBinding;
import com.maestros.bottlehoz.model.AllListingModel;
import com.maestros.bottlehoz.model.CategoryModel;

import java.util.List;

public class AllListingAdapter extends RecyclerView.Adapter<AllListingAdapter.MyViewHolder> {


    private Context mContext;
    private List<AllListingModel> allList;

    public AllListingAdapter(Context mContext, List<AllListingModel> allList) {
        this.mContext = mContext;
        this.allList = allList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowAllListingLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AllListingModel modelObject = allList.get(position);
        holder.rowAllListingLayoutBinding.txtName.setText(modelObject.getProductName());
        holder.rowAllListingLayoutBinding.txtPrice.setText(modelObject.getPrice());
        holder.rowAllListingLayoutBinding.imgPopular.setImageResource(modelObject.getImage());

       /* try {
            Glide.with(mContext).load(R.drawable.imageb)
                    .placeholder(R.drawable.imageb).override(250, 250)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.rowCatproductLayoutBinding.imgcategory);
        } catch (Exception e) {

        }*/


    }

    @Override
    public int getItemCount() {
        return allList == null ? 0 : allList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowAllListingLayoutBinding rowAllListingLayoutBinding;

        public MyViewHolder(RowAllListingLayoutBinding rowAllListingLayoutBinding) {
            super(rowAllListingLayoutBinding.getRoot());
            this.rowAllListingLayoutBinding = rowAllListingLayoutBinding;
        }

    }
}
