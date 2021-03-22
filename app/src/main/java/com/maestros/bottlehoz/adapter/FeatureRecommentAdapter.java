package com.maestros.bottlehoz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maestros.bottlehoz.databinding.RowFeatureRecommendLayoutBinding;
import com.maestros.bottlehoz.model.FeatureRecommentModel;

import java.util.List;

public class FeatureRecommentAdapter extends RecyclerView.Adapter<FeatureRecommentAdapter.MyViewHolder> {


    private Context mContext;
    private List<FeatureRecommentModel> featureList;

    public FeatureRecommentAdapter(Context mContext, List<FeatureRecommentModel> featureList) {
        this.mContext = mContext;
        this.featureList = featureList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowFeatureRecommendLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FeatureRecommentModel modelObject = featureList.get(position);
        holder.rowFeatureRecommendLayoutBinding.txtName.setText(modelObject.getProductTitle());
        holder.rowFeatureRecommendLayoutBinding.txtMsg.setText(modelObject.getDiscription());
        holder.rowFeatureRecommendLayoutBinding.txtCRate.setText(modelObject.getRating());
        holder.rowFeatureRecommendLayoutBinding.imgPopular.setImageResource(modelObject.getImage());

      /*  try {
            Glide.with(mContext).load(R.drawable.imageb)
                    .placeholder(R.drawable.imageb).override(250, 250)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.rowCatproductLayoutBinding.imgcategory);
        } catch (Exception e) {

        }*/


    }

    @Override
    public int getItemCount() {
        return featureList == null ? 0 : featureList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowFeatureRecommendLayoutBinding rowFeatureRecommendLayoutBinding;

        public MyViewHolder(RowFeatureRecommendLayoutBinding rowFeatureRecommendLayoutBinding) {
            super(rowFeatureRecommendLayoutBinding.getRoot());
            this.rowFeatureRecommendLayoutBinding = rowFeatureRecommendLayoutBinding;
        }

    }
}
