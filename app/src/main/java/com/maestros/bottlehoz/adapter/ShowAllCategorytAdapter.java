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
import com.maestros.bottlehoz.databinding.RowAllcatLayoutBinding;
import com.maestros.bottlehoz.databinding.RowCatproductLayoutBinding;
import com.maestros.bottlehoz.utils.AppConstats;
import com.maestros.bottlehoz.utils.SharedHelper;

import java.util.List;

public class ShowAllCategorytAdapter extends RecyclerView.Adapter<ShowAllCategorytAdapter.MyViewHolder> {


    private Context mContext;
    private List<CategoryHome.Data> catList;

    public ShowAllCategorytAdapter(Context mContext, List<CategoryHome.Data> catList) {
        this.mContext = mContext;
        this.catList = catList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowAllcatLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CategoryHome.Data modelObject = catList.get(position);
        holder.rowAllcatLayoutBinding.txtName.setText(modelObject.getName());

        try {
            Glide.with(mContext).load(modelObject.getPath()+modelObject.getImage())
                    .placeholder(R.drawable.imageb).override(250, 250)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.rowAllcatLayoutBinding.imgcategory);
        } catch (Exception e) {

        }





     holder.rowAllcatLayoutBinding.rlMain.setOnClickListener(new View.OnClickListener() {
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
        private RowAllcatLayoutBinding rowAllcatLayoutBinding;

        public MyViewHolder(RowAllcatLayoutBinding rowAllcatLayoutBinding) {
            super(rowAllcatLayoutBinding.getRoot());
            this.rowAllcatLayoutBinding = rowAllcatLayoutBinding;
        }

    }
}
