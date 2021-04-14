package com.maestros.bottlehoz.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.activities.ProductDetailActivity;
import com.maestros.bottlehoz.databinding.RowMoreLayoutBinding;
import com.maestros.bottlehoz.databinding.RowPopularLayoutBinding;
import com.maestros.bottlehoz.model.MoreModel;
import com.maestros.bottlehoz.model.PopularModel;
import com.maestros.bottlehoz.retrofit.BaseUrl;
import com.maestros.bottlehoz.utils.AppConstats;
import com.maestros.bottlehoz.utils.SharedHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import es.dmoral.toasty.Toasty;

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

        if (!modelObject.equals("")){
            holder.rowMoreLayoutBinding.txtPrice.setText("₦"+modelObject.getPrice());
            holder.rowMoreLayoutBinding.txtName.setText(modelObject.getName());
            holder.rowMoreLayoutBinding.txtCount.setText(modelObject.getProduct_sold()+"Sold");
            holder.rowMoreLayoutBinding.txtCRate.setText(modelObject.getRating());


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



        holder.rowMoreLayoutBinding.cardNew1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedHelper.putKey(mContext, AppConstats.CATEGORYID, modelObject.getCategoryID());
                SharedHelper.putKey(mContext, AppConstats.PRODUCTID, modelObject.getProductID());
                SharedHelper.putKey(mContext, AppConstats.SELLERID, modelObject.getSellerID());
                mContext.startActivity(new Intent(mContext, ProductDetailActivity.class));
            }
        });

        holder.rowMoreLayoutBinding.imgADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart (modelObject.getProductID(),modelObject.getSellerID());
            }
        });

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

    private void addToCart(String productID, String sellerID) {
      String  stUSER_Id = SharedHelper.getKey(mContext, AppConstats.USER_ID);
        AndroidNetworking.post(BaseUrl.BASEURL)
                .addBodyParameter("control", "add_to_cart")
                .addBodyParameter("productID", productID)
                .addBodyParameter("userID", stUSER_Id)
                .addBodyParameter("sellerID", sellerID)
                .addBodyParameter("quantity", "1")
                .setTag("ADD TO CART")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {


                        try {
                            if (response.getBoolean("result") == true) {


                                Toasty.success(mContext, response.getString("message"), Toasty.LENGTH_SHORT).show();
                            } else {

                                Toasty.error(mContext, response.getString("message"), Toasty.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });


    }
}
