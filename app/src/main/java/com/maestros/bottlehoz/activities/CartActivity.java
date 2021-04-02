package com.maestros.bottlehoz.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;

import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.adapter.CartTittleAdapter;
import com.maestros.bottlehoz.databinding.ActivityCartBinding;
import com.maestros.bottlehoz.retrofit.BaseUrl;
import com.maestros.bottlehoz.utils.AppConstats;
import com.maestros.bottlehoz.utils.ProgressBarCustom.CustomDialog;
import com.maestros.bottlehoz.utils.SharedHelper;

import org.json.JSONObject;

import java.util.ArrayList;

import static com.maestros.bottlehoz.retrofit.BaseUrl.SHOW_CART;

interface CartActivityInterface {
    void draw(String id , int position);
    void listItem(ArrayList<String>allItemList , int position);
}

public class CartActivity extends AppCompatActivity implements CartActivityInterface {
    ActivityCartBinding binding;
    View view;
    RecyclerView.LayoutManager layoutManagerTittle;
   public static ArrayList<String> myLocalCartList = new ArrayList<>();
    public  static int CountAll=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);

        layoutManagerTittle = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        binding.rvCart.setLayoutManager(layoutManagerTittle);
        binding.rvCart.setHasFixedSize(true);

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        show_cart();

        //  test();

    }

    private void show_cart() {
        String stUserId = SharedHelper.getKey(getApplicationContext(), AppConstats.USER_ID);
        Log.e("CartActivity", "onCreate: " + stUserId);
        CustomDialog dialog = new CustomDialog();
        dialog.showDialog(R.layout.progress_layout, this);

       /* CartTittleModel model=new CartTittleModel("Sexy Dimples Drinks");

        for (int i = 0; i < 2; i++) {
            cartTittleList.add(model);
            for (int j = 0; j <3 ; j++) {
                ArraddCartList.add("0");
                Log.e("CartActivity", "show_cart: " +ArraddCartList);
            }

        }*/
        AndroidNetworking.post(BaseUrl.BASEURL)
                .addBodyParameter("control", SHOW_CART)
                .addBodyParameter("userID", stUserId)
                .setPriority(Priority.HIGH)
                .build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
               Log.e("CartActivity", "onResponse: " + response);
                dialog.hideDialog();
                try {
                    if (response.getBoolean("result") == true) {

                        Gson gson = new Gson();
                        CartData cartData = gson.fromJson(response.toString(), CartData.class);
                        ArrayList arrayList = new ArrayList<CartData.Data>();
                        if (!cartData.getData().isEmpty()) {
                            myLocalCartList.clear();
                            arrayList.addAll(cartData.getData());
                            for (int i = 0; i <cartData.getData().size() ; i++) {
                                for (int j = 0; j <cartData.getData().get(i).getSellers_info().getProducts().size() ; j++) {
                                    myLocalCartList.add("0");

                                }
                            }
                            Log.e("CartActivity", "onResponse: " +myLocalCartList);

                        }

                        CartTittleAdapter cartTittleAdapter = new CartTittleAdapter(CartActivity.this, arrayList);
                        binding.rvCart.setAdapter(cartTittleAdapter);

                    }
                    else {
                        binding.lotiAnimation.setVisibility(View.VISIBLE);
                        binding.txEmpty.setVisibility(View.VISIBLE);
                        binding.rlNoData.setVisibility(View.VISIBLE);
                      //  Toast.makeText(CartActivity.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                         binding.rlBottom.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    dialog.hideDialog();
                    Log.e("CartActivity", "onResponse: " +e.getMessage());
                }


            }


            @Override
            public void onError(ANError anError) {
                dialog.hideDialog();
                Log.e("CartActivity", "onError: " + anError.getMessage());
            }
        });
    }

   /* public void test(){
        for (int i = 0; i <2 ; i++) {
            for (int j = 0; j < 3; j++) {
                Log.e("CartActivity", "test: " +j);
            }
        }
    }*/

    @Override
    public void draw(String id,int position) {
        myLocalCartList.set(position, id);
        Log.e("CartActivity", "draw: " + myLocalCartList);
    }

    @Override
    public void listItem(ArrayList<String> allItemList, int position) {
        Log.e("CartActivity", "listItem: " +allItemList);
    }
}