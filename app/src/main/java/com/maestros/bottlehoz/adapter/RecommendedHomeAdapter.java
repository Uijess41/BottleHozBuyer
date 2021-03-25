package com.maestros.bottlehoz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.databinding.RowRecommendLayoutBinding;
import com.maestros.bottlehoz.databinding.RowRecommendedHomeLayoutBinding;
import com.maestros.bottlehoz.model.RecommendedHomeModel;
import com.maestros.bottlehoz.model.RecommentModel;

import java.util.List;

public class RecommendedHomeAdapter extends RecyclerView.Adapter<RecommendedHomeAdapter.MyViewHolder> {


    private Context mContext;
    private List<RecommendedHomeModel> recommentList;

    public RecommendedHomeAdapter(Context mContext, List<RecommendedHomeModel> recommentList) {
        this.mContext = mContext;
        this.recommentList = recommentList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowRecommendedHomeLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RecommendedHomeModel modelObject = recommentList.get(position);
        holder.rowRecommendedHomeLayoutBinding.txtName.setText(modelObject.getProductName());
        holder.rowRecommendedHomeLayoutBinding.txtCan.setText(modelObject.getDescription());
        holder.rowRecommendedHomeLayoutBinding.txPriceRecommend.setText("₦"+modelObject.getPrice());

     try {
            Glide.with(mContext).load(modelObject.getPath()+modelObject.getImage())
                    .placeholder(R.drawable.imageb).override(250, 250)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.rowRecommendedHomeLayoutBinding.imgRecommended);
        } catch (Exception e) {

        }


    }

    @Override
    public int getItemCount() {
        return recommentList == null ? 0 : recommentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowRecommendedHomeLayoutBinding rowRecommendedHomeLayoutBinding;

        public MyViewHolder(RowRecommendedHomeLayoutBinding rowRecommendedHomeLayoutBinding) {
            super(rowRecommendedHomeLayoutBinding.getRoot());
            this.rowRecommendedHomeLayoutBinding = rowRecommendedHomeLayoutBinding;
        }

    }
}
