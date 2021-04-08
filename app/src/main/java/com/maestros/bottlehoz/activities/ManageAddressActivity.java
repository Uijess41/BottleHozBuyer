package com.maestros.bottlehoz.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.adapter.ManageAddressAdapter;
import com.maestros.bottlehoz.databinding.ActivityManageAddressBinding;
import com.maestros.bottlehoz.model.ManageAddressModel;
import com.maestros.bottlehoz.retrofit.BaseUrl;
import com.maestros.bottlehoz.utils.AppConstats;
import com.maestros.bottlehoz.utils.ProgressBarCustom.CustomDialog;
import com.maestros.bottlehoz.utils.SharedHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.maestros.bottlehoz.retrofit.BaseUrl.SHOW_ADDRESS;
import static com.maestros.bottlehoz.retrofit.BaseUrl.SHOW_STATE;

public class ManageAddressActivity extends AppCompatActivity {
ActivityManageAddressBinding binding;
String stUserId="";
ArrayList<ManageAddressModel>addressModelArrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding=ActivityManageAddressBinding.inflate(getLayoutInflater());
       setContentView(binding.getRoot());


       stUserId=SharedHelper.getKey(getApplicationContext(),AppConstats.USER_ID);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ManageAddressActivity.this,RecyclerView.VERTICAL,false);
        binding.rvAddress.setLayoutManager(layoutManager);

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
       binding.txtAddress.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               SharedHelper.putKey(getApplicationContext(), AppConstats.SELECTADDRESS, "");
               SharedHelper.putKey(getApplicationContext(), AppConstats.SELECTLONG, "");
               SharedHelper.putKey(getApplicationContext(), AppConstats.SELECTLAT, "");
               startActivity(new Intent(ManageAddressActivity.this, AddAddressActivity.class));
           }
       });
       showAddress(stUserId);
    }

    private void showAddress(String stUserId){

        CustomDialog dialog = new CustomDialog();
        dialog.showDialog(R.layout.progress_layout, ManageAddressActivity.this);

        AndroidNetworking.post(BaseUrl.BASEURL)
                .addBodyParameter("control", SHOW_ADDRESS)
                .addBodyParameter("userID",stUserId)
                .setTag("showing address Successfully")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dialog.hideDialog();
                        Log.e("ManageAddressActivity", "response: " +response);
                        addressModelArrayList=new ArrayList<>();
                        try {
                            if (response.getString("result").equals("true")){
                                String data=response.getString("data");
                                if (!data.isEmpty()){

                                    JSONArray jsonArray=new JSONArray(data);
                                    for (int i = 0; i <jsonArray.length() ; i++) {
                                        JSONObject object=jsonArray.getJSONObject(i);
                                        String addressId=object.getString("addressID");
                                        ManageAddressModel model=new ManageAddressModel();
                                        model.setAddressId(object.getString("addressID"));
                                        model.setAddress(object.getString("address"));
                                        model.setName(object.getString("name"));
                                        addressModelArrayList.add(model);
                                    }
                                    ManageAddressAdapter addressAdapter=new ManageAddressAdapter(ManageAddressActivity.this,addressModelArrayList);
                                    binding.rvAddress.setAdapter(addressAdapter);

                                }


                            }
                        } catch (JSONException e) {
                            dialog.hideDialog();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        dialog.hideDialog();
                    }
                });

    }
}