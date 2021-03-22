package com.maestros.bottlehoz.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.activities.ShowCatWiseProductActivity;
import com.maestros.bottlehoz.databinding.RowCatproductLayoutBinding;
import com.maestros.bottlehoz.utils.AppConstats;
import com.maestros.bottlehoz.utils.SharedHelper;

import java.util.List;

public class CategorytAdapter extends RecyclerView.Adapter<CategorytAdapter.MyViewHolder> {


    private Context mContext;
    private List<CategoryHome.Data> catList;

    public CategorytAdapter(Context mContext, List<CategoryHome.Data> catList) {
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
        CategoryHome.Data modelObject = catList.get(position);
        holder.rowCatproductLayoutBinding.txtName.setText(modelObject.getName());

        try {
            Glide.with(mContext).load(modelObject.getPath()+modelObject.getImage())
                    .placeholder(R.drawable.imageb).override(250, 250)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.rowCatproductLayoutBinding.imgcategory);
        } catch (Exception e) {

        }

        if (position %2==1){
            holder.rowCatproductLayoutBinding.card.setBackground(mContext.getResources().getDrawable(R.drawable.wine_round));
        }

        else if (position %4==0){
            holder.rowCatproductLayoutBinding.card.setBackground(mContext.getResources().getDrawable(R.drawable.cocktail_round));
        }
        else if (position %3==0){
            holder.rowCatproductLayoutBinding.card.setBackground(mContext.getResources().getDrawable(R.drawable.beer_round));
        }
     else {
            holder.rowCatproductLayoutBinding.card.setBackground(mContext.getResources().getDrawable(R.drawable.whiskey_round));
        }


     holder.rowCatproductLayoutBinding.rlMain.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             SharedHelper.putKey(mContext, AppConstats.CATEGORYID, modelObject.getCategoryID());
             SharedHelper.putKey(mContext, AppConstats.CATEGORYNAME, modelObject.getName());
             mContext.startActivity(new Intent(mContext, ShowCatWiseProductActivity.class));
         }
     });
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
