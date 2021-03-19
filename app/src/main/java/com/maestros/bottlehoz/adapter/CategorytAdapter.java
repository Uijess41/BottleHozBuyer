package com.maestros.bottlehoz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.databinding.RowCatproductLayoutBinding;
import com.maestros.bottlehoz.databinding.RowDiscountLayoutBinding;
import com.maestros.bottlehoz.model.CategoryModel;
import com.maestros.bottlehoz.model.DiscountPercentModel;

import java.util.List;

public class CategorytAdapter extends RecyclerView.Adapter<CategorytAdapter.MyViewHolder> {


    private Context mContext;
    private List<CategoryModel> catList;

    public CategorytAdapter(Context mContext, List<CategoryModel> catList) {
        this.mContext = mContext;
        this.catList = catList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowCatproductLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CategoryModel modelObject = catList.get(position);
        holder.rowCatproductLayoutBinding.txtName.setText(modelObject.getProductName());

        try {
            Glide.with(mContext).load(R.drawable.imageb)
                    .placeholder(R.drawable.imageb).override(250, 250)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.rowCatproductLayoutBinding.imgcategory);
        } catch (Exception e) {

        }

        if (position %3==1){
            holder.rowCatproductLayoutBinding.card.setBackground(mContext.getResources().getDrawable(R.drawable.wine_round));
        }
       if (position %1==2){
            holder.rowCatproductLayoutBinding.card.setBackground(mContext.getResources().getDrawable(R.drawable.whiskey_round));
        }

    }

    @Override
    public int getItemCount() {
        return catList == null ? 0 : catList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowCatproductLayoutBinding rowCatproductLayoutBinding;

        public MyViewHolder(RowCatproductLayoutBinding rowCatproductLayoutBinding) {
            super(rowCatproductLayoutBinding.getRoot());
            this.rowCatproductLayoutBinding = rowCatproductLayoutBinding;
        }

    }
}
