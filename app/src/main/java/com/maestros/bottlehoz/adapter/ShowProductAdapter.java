package com.maestros.bottlehoz.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.activities.DataCatViseProduct;
import com.maestros.bottlehoz.databinding.RowAllListingLayoutBinding;
import com.maestros.bottlehoz.databinding.RowShowCategoryLayoutBinding;
import com.maestros.bottlehoz.model.AllListingModel;
import com.maestros.bottlehoz.model.ShowCatWiseProductModel;

import java.util.List;

public class ShowProductAdapter extends RecyclerView.Adapter<ShowProductAdapter.MyViewHolder> {


    private Context mContext;
    private List<DataCatViseProduct .Data> allList;

    public ShowProductAdapter(Context mContext, List<DataCatViseProduct.Data> allList) {
        this.mContext = mContext;
        this.allList = allList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowShowCategoryLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataCatViseProduct.Data modelObject = allList.get(position);
        holder.rowShowCategoryLayoutBinding.txtName.setText(modelObject.getName());
        holder.rowShowCategoryLayoutBinding.txtPrice.setText(modelObject.getPrice());


       if (modelObject.getImages().size()==0){

        }else {

            Log.e("MoreAdapter", "onBindViewHolder: " +modelObject.getImages().get(0).getPath()+modelObject.getImages().get(0).getImage());
            try {
                Glide.with(mContext).load(modelObject.getImages().get(0).getPath()+modelObject.getImages().get(0).getImage())
                        .error(R.drawable.dummy).override(250, 250)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.rowShowCategoryLayoutBinding.imgPopular);
            }catch (Exception e){

            }
        }




    }

    @Override
    public int getItemCount() {
        return allList == null ? 0 : allList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowShowCategoryLayoutBinding rowShowCategoryLayoutBinding;

        public MyViewHolder(RowShowCategoryLayoutBinding rowShowCategoryLayoutBinding) {
            super(rowShowCategoryLayoutBinding.getRoot());
            this.rowShowCategoryLayoutBinding = rowShowCategoryLayoutBinding;
        }

    }
}
