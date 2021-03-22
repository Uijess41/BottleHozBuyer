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
import com.maestros.bottlehoz.databinding.RowMoreLayoutBinding;
import com.maestros.bottlehoz.databinding.RowPopularLayoutBinding;
import com.maestros.bottlehoz.model.MoreModel;
import com.maestros.bottlehoz.model.PopularModel;

import java.util.List;

public class MoreAdapter extends RecyclerView.Adapter<MoreAdapter.MyViewHolder>{


    private Context mContext;
    private List<DataMoreLove.Data> moreList;

    public MoreAdapter(Context mContext, List<DataMoreLove.Data> moreList) {
        this.mContext = mContext;
        this.moreList = moreList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowMoreLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataMoreLove.Data modelObject = moreList.get(position);

        holder.rowMoreLayoutBinding.txtPrice.setText("₦"+modelObject.getPrice());
        holder.rowMoreLayoutBinding.txtName.setText(modelObject.getName());
  //    holder.rowMoreLayoutBinding.txtCount.setText(modelObject.getSoldCoun());

   //   holder.rowMoreLayoutBinding.txtCRate.setText(modelObject.getRating());


        if (modelObject.getImages().size()==0){

        }else {

            Log.e("MoreAdapter", "onBindViewHolder: " +modelObject.getImages().get(0).getPath()+modelObject.getImages().get(0).getImage());
            try {
                Glide.with(mContext).load(modelObject.getImages().get(0).getPath()+modelObject.getImages().get(0).getImage())
                        .error(R.drawable.dummy).override(150, 150)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.rowMoreLayoutBinding.imgPopular);
            }catch (Exception e){

            }
        }




    }

    @Override
    public int getItemCount() {
        return moreList == null ? 0 : moreList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowMoreLayoutBinding rowMoreLayoutBinding;
        public MyViewHolder(RowMoreLayoutBinding rowMoreLayoutBinding) {
            super(rowMoreLayoutBinding.getRoot());
            this.rowMoreLayoutBinding = rowMoreLayoutBinding;
        }

    }
}
