package com.maestros.bottlehoz.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.activities.CartActivity;
import com.maestros.bottlehoz.activities.CartData;
import com.maestros.bottlehoz.activities.CheckInterface;
import com.maestros.bottlehoz.databinding.RowCartProductLayoutBinding;
import com.maestros.bottlehoz.retrofit.BaseUrl;
import com.maestros.bottlehoz.utils.AppConstats;
import com.maestros.bottlehoz.utils.SharedHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.maestros.bottlehoz.retrofit.BaseUrl.DELETE_CART;
import static com.maestros.bottlehoz.retrofit.BaseUrl.UPDATE_QUANTITY;

public class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.MyViewHolder> {

    String strProdQ = "", new_str = "";
    Context mContext;
    Boolean check;
    CheckInterface checkInterface;
    // int stock_count = 0;
    int count = 0;
    List<CartData.Data.SellersInfo.Product> catProductList;

    public CartProductAdapter(Context mContext, List<CartData.Data.SellersInfo.Product> catProductList, Boolean check, CheckInterface checkInterface) {
        this.mContext = mContext;
        this.catProductList = catProductList;
        this.check = check;
        this.checkInterface = checkInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowCartProductLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CartData.Data.SellersInfo.Product modelObject = catProductList.get(position);
        holder.rowCartProductLayoutBinding.txtPName.setText(modelObject.getProduct_info().getName());
        holder.rowCartProductLayoutBinding.txtMedium.setText(modelObject.getProduct_info().getDescription());
        holder.rowCartProductLayoutBinding.txtPrice.setText(modelObject.getProduct_info().getPrice());
        holder.rowCartProductLayoutBinding.txtItemCount.setText(modelObject.getQuantity());
        if (check) {
            holder.rowCartProductLayoutBinding.checkCategory.setChecked(true);
        } else {
            holder.rowCartProductLayoutBinding.checkCategory.setChecked(false);
        }

        Toast.makeText(mContext, "else", Toast.LENGTH_SHORT).show();
        for (int i = 0; i < modelObject.getProduct_info().getImages().size(); i++) {

            Log.e("CartProductAdapter", "onBindViewHolder: " + modelObject.getProduct_info().getImages().size());

            try {
                Glide.with(mContext).load(modelObject.getProduct_info().getImages().get(i).getPath() + modelObject.getProduct_info().getImages().get(i).getImage())
                        .placeholder(R.drawable.imageb).override(250, 250)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.rowCartProductLayoutBinding.imgPremium);
            } catch (Exception e) {

            }

        }


        holder.rowCartProductLayoutBinding.checkCategory.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkInterface.checkData("dsds", false, 0);
                Toast.makeText(mContext, "if", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mContext, "else", Toast.LENGTH_SHORT).show();
                checkInterface.checkData("dsds", false, 0);
            }
            if (holder.rowCartProductLayoutBinding.checkCategory.isChecked()) {
               /* if (mContext instanceof CartActivity) {
                    ((CartActivity) mContext).draw(modelObject.getCartID(), position);

                }*/


            } else {
                /*if (mContext instanceof CartActivity) {
                    ((CartActivity) mContext).draw("0", position);
                }*/

            }

        });


        holder.rowCartProductLayoutBinding.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_cart(modelObject.getProductID(), modelObject.getCartID());
            }
        });


        //////////////////////////////////////////////// QUANTITY//////////////////////////////////////////////////////
        int stock_count = Integer.parseInt(modelObject.getProduct_info().getStock());

        if (!modelObject.getProduct_info().getStock().equals("")) {

            Log.e("CartProductAdapter", "stock_count: " + stock_count);
        }


        holder.rowCartProductLayoutBinding.imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {


                strProdQ = holder.rowCartProductLayoutBinding.txtItemCount.getText().toString();

                new_str = String.valueOf(Integer.parseInt(strProdQ) + 1);

                holder.rowCartProductLayoutBinding.txtItemCount.setText(new_str);

                int qty = Integer.parseInt(new_str);


                if (qty > stock_count) {
                    Log.e("CartProductAdapter", "qty: " + qty);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                    alertDialogBuilder.setMessage("Can't purchase more than stock Please select under the stock ");
                    alertDialogBuilder.setPositiveButton("ok",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {

                                    Intent plusActivity = new Intent(mContext, CartActivity.class);
                                    mContext.startActivity(plusActivity);
                                    ((Activity) mContext).finish();


                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                } else {

                    update_quantity(modelObject.getCartID(), modelObject.getProductID(), new_str, holder);

                }


            }
        });

        holder.rowCartProductLayoutBinding.imgMinus.setOnClickListener(v -> {


            if (count == 0) {
                checkInterface.checkData("dsds", false, 0);
                Log.e("dksjda", count+"");
                count = 1;
                Toast.makeText(mContext, "if", Toast.LENGTH_SHORT).show();
            } else {
                checkInterface.checkData("dsds", false, 0);

                Log.e("dksjda", count+"");
                count = 0;
            }



            strProdQ = holder.rowCartProductLayoutBinding.txtItemCount.getText().toString();

            new_str = String.valueOf(Integer.parseInt(strProdQ) - 1);

            int xyz = Integer.parseInt(strProdQ) - 1;
            if (xyz < 1) {

                // holder.rowCartProductLayoutBinding.txtItemCount.setText("1");

            } else {

                holder.rowCartProductLayoutBinding.txtItemCount.setText(new_str);

                update_quantity(modelObject.getCartID(),modelObject.getProductID(), new_str,holder);

            }
        });


    }

    @Override
    public int getItemCount() {

        return catProductList == null ? 0 : catProductList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowCartProductLayoutBinding rowCartProductLayoutBinding;

        public MyViewHolder(RowCartProductLayoutBinding rowCartProductLayoutBinding) {
            super(rowCartProductLayoutBinding.getRoot());
            this.rowCartProductLayoutBinding = rowCartProductLayoutBinding;
        }

    }


    private void delete_cart(String productID, String showCartId) {
        String strUserId = SharedHelper.getKey(mContext, AppConstats.USER_ID);

        Log.e("fdskftgfdr", showCartId);
        Log.e("fdskftgfdr", strUserId);
        Log.e("fdskftgfdr", productID);
        AndroidNetworking.post(BaseUrl.BASEURL)
                .addBodyParameter("control", DELETE_CART)
                .addBodyParameter("productID", productID)
                .addBodyParameter("userID", strUserId)
                .addBodyParameter("cartID", showCartId)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("edgregr", response.toString());

                        try {
                            if (response.getString("result").equals("true")) {

                                Toast.makeText(mContext, response.getString("message"), Toast.LENGTH_SHORT).show();

                                mContext.startActivity(new Intent(mContext, CartActivity.class));
                                ((Activity) mContext).finish();
                            }
                        } catch (JSONException e) {
                            Log.e("kllgb", e.getMessage());

                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("tyuyfb", anError.getMessage());

                    }
                });

    }


    public void update_quantity(String cartId, String showProductId, String new_str, MyViewHolder holder) {

        String strUserId = SharedHelper.getKey(mContext, AppConstats.USER_ID);
        Log.e("fkdkg", new_str);
        Log.e("fkdkg", strUserId);
        Log.e("fkdkg", showProductId);
        Log.e("fkdkg", cartId);
        AndroidNetworking.post(BaseUrl.BASEURL)
                .addBodyParameter("control", UPDATE_QUANTITY)
                .addBodyParameter("cartID", cartId)
                .addBodyParameter("productID", showProductId)
                .addBodyParameter("quantity", new_str)
                .addBodyParameter("userID", strUserId)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("regtdfrh", response.toString());
                        try {
                            if (response.getString("result").equals("true")) {


                               /* AppConstant.sharedpreferences = context.getSharedPreferences(AppConstant.MyPREFERENCES, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = AppConstant.sharedpreferences.edit();
                                editor.putString(AppConstant.TotalAmount,total_pay );
                                editor.commit();
*/
                                mContext.startActivity(new Intent(mContext, CartActivity.class));
                                ((Activity) mContext).finish();
                            }
                        } catch (JSONException e) {
                            Log.e("tyhth", e.getMessage());
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("ukuihj", anError.getMessage());
                    }
                });


    }
}
