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
import com.maestros.bottlehoz.databinding.RowManageAddressLayoutBinding;
import com.maestros.bottlehoz.model.ManageAddressModel;
import com.maestros.bottlehoz.utils.AppConstats;
import com.maestros.bottlehoz.utils.SharedHelper;

import java.util.List;

public class ManageAddressAdapter extends RecyclerView.Adapter<ManageAddressAdapter.MyViewHolder> {


    private Context mContext;
    private List<ManageAddressModel> addressList;

    public ManageAddressAdapter(Context mContext, List<ManageAddressModel> addressList) {
        this.mContext = mContext;
        this.addressList = addressList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowManageAddressLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ManageAddressModel modelObject = addressList.get(position);
        holder.rowManageAddressLayoutBinding.txtName.setText(modelObject.getName());
        holder.rowManageAddressLayoutBinding.txtAddress.setText(modelObject.getAddress());
        holder.rowManageAddressLayoutBinding.txtType.setText(modelObject.getType());

     /*   try {
            Glide.with(mContext).load(modelObject.getPath()+modelObject.getImage())
                    .placeholder(R.drawable.imageb).override(250, 250)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.rowCatproductLayoutBinding.imgcategory);
        } catch (Exception e) {

        }*/

       /* if (position %2==1){
            holder.rowCatproductLayoutBinding.card.setBackground(mContext.getResources().getDrawable(R.drawable.wine_round));
        }

        else if (position %4==0){
            holder.rowCatproductLayoutBinding.card.setBackground(mContext.getResources().getDrawable(R.drawable.cocktail_round));
        }
       */

   /*  holder.rowCatproductLayoutBinding.rlMain.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             SharedHelper.putKey(mContext, AppConstats.CATEGORYID, modelObject.getCategoryID());
             SharedHelper.putKey(mContext, AppConstats.CATEGORYNAME, modelObject.getName());
             mContext.startActivity(new Intent(mContext, ShowCatWiseProductActivity.class));
         }
     });*/
    }

    @Override
    public int getItemCount() {
        return addressList == null ? 0 : addressList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowManageAddressLayoutBinding rowManageAddressLayoutBinding;

        public MyViewHolder(RowManageAddressLayoutBinding rowManageAddressLayoutBinding) {
            super(rowManageAddressLayoutBinding.getRoot());
            this.rowManageAddressLayoutBinding = rowManageAddressLayoutBinding;
        }

    }
}
