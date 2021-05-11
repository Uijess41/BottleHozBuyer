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
import com.maestros.bottlehoz.databinding.RowSearchResultLayoutBinding;
import com.maestros.bottlehoz.model.RecommentModel;
import com.maestros.bottlehoz.model.SearchResultModel;

import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.MyViewHolder> {


    private Context mContext;
    private List<SearchResultModel> searchList;

    public SearchResultAdapter(Context mContext, List<SearchResultModel> searchList) {
        this.mContext = mContext;
        this.searchList = searchList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowSearchResultLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SearchResultModel modelObject = searchList.get(position);

        if (!modelObject.equals("")){

            holder.rowSearchResultLayoutBinding.txtName.setText(modelObject.getProductName());

            try {
                Glide.with(mContext).load(modelObject.getPath()+modelObject.getImage())
                        .placeholder(R.drawable.drink).override(250, 250)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.rowSearchResultLayoutBinding.imgPopular);
            } catch (Exception e) {

            }

        }


    }

    @Override
    public int getItemCount() {
        return searchList == null ? 0 : searchList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowSearchResultLayoutBinding rowSearchResultLayoutBinding;

        public MyViewHolder(RowSearchResultLayoutBinding rowSearchResultLayoutBinding) {
            super(rowSearchResultLayoutBinding.getRoot());
            this.rowSearchResultLayoutBinding = rowSearchResultLayoutBinding;
        }

    }
}
