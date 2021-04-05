package com.maestros.bottlehoz.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.adapter.ManageAddressAdapter;
import com.maestros.bottlehoz.databinding.ActivityAddAddressBinding;
import com.maestros.bottlehoz.retrofit.BaseUrl;
import com.maestros.bottlehoz.utils.AppConstats;
import com.maestros.bottlehoz.utils.Connectivity;
import com.maestros.bottlehoz.utils.ProgressBarCustom.CustomDialog;
import com.maestros.bottlehoz.utils.SharedHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

import static com.maestros.bottlehoz.retrofit.BaseUrl.ADD_ADDRESS;
import static com.maestros.bottlehoz.retrofit.BaseUrl.BASEURL;
import static com.maestros.bottlehoz.retrofit.BaseUrl.SHOW_CITY;
import static com.maestros.bottlehoz.retrofit.BaseUrl.SHOW_STATE;


public class AddAddressActivity extends AppCompatActivity {
    private ActivityAddAddressBinding binding;
    private Context context;
    private View view;
    String st_address = "", stStateId = "", StCityId = "", stFullName = "", stMobile = "", st_UserId = "", st_Lat = "", st_Lng = "", stAddressTittle = "";
    private static final int REQ_CODE = 140;
    ArrayList<String> arrayListStateId;
    ArrayList<String> arrayListState;
    ArrayAdapter<String> adapterState;

    ArrayList<String> arrayListCityId;
    ArrayList<String> arrayListCity;
    ArrayAdapter<String> adapterCity;
    // String address = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddAddressBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);
        st_address = SharedHelper.getKey(getApplicationContext(), AppConstats.SELECTADDRESS);
        st_UserId = SharedHelper.getKey(getApplicationContext(), AppConstats.USER_ID);
        stFullName = SharedHelper.getKey(getApplicationContext(), AppConstats.USER_NAME);

        Log.e("MapActivity", "st_address: " + st_address);
        Log.e("MapActivity", "st_UserId: " + st_UserId);
        Log.e("MapActivity", "stFullName: " + stFullName);
        Connectivity connectivity = new Connectivity(this);
        if (st_address.equals("")) {

        } else {
            binding.etAddress.setText(st_address);
        }


        showState();

        context = this;


        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.rlAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AddAddressActivity.this, MapActivity.class);
                startActivityForResult(intent, REQ_CODE);


            }
        });


        binding.spinState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                stStateId = arrayListStateId.get(position);
                Log.e("AddAddressActivity", "stStateId: " + stStateId);
                showCity(stStateId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        binding.spinCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (arrayListCityId.size() == 0) {

                } else {
                    StCityId = arrayListCityId.get(position);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stFullName = binding.etFullname.getText().toString().trim();
                stMobile = binding.etMobile.getText().toString().trim();
                stAddressTittle = binding.etAddressTitle.getText().toString().trim();
                if (Validation()) {
                    if (connectivity.isOnline()) {

                        add_address(stFullName, stMobile, st_address, stStateId, StCityId, stAddressTittle);


                    } else {
                        Toast.makeText(AddAddressActivity.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
                    }

                } else {

                }
            }


        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQ_CODE && resultCode == RESULT_OK) {


            if (data!=null){
                st_address = data.getStringExtra(MapActivity.ADDRESS);
                Log.e("name", "onActivityResult: " + st_address);
                binding.etAddress.setText(st_address);
            }


        }







    }

    private Boolean Validation() {

        Boolean boolen = false;

        if (binding.etFullname.getText().toString().isEmpty()) {

            binding.etFullname.setError("Full Name  Must Required");
        } else if (binding.etMobile.getText().toString().isEmpty()) {

            binding.etMobile.setError("Mobile Must Required");
        } else if (binding.etMobile.length() < 10) {

            binding.etMobile.setError("Must Required 10 digit ");
        } else if (binding.etAddressTitle.getText().toString().isEmpty()) {

            binding.etAddressTitle.setError("Address Tittle Must Required");
        } else if (binding.etAddress.getText().toString().isEmpty()) {

            binding.etAddress.setError("Address  Must Required");
        } else {
            boolen = true;

        }
        return boolen;
    }

    private void showState() {
        CustomDialog dialog = new CustomDialog();
        dialog.showDialog(R.layout.progress_layout, AddAddressActivity.this);

        AndroidNetworking.post(BaseUrl.BASEURL)
                .addBodyParameter("control", SHOW_STATE)
                .setTag("showing state Successfully")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("wsfcdsvdf", response.toString());
                        dialog.hideDialog();
                        arrayListState = new ArrayList<>();
                        arrayListStateId = new ArrayList<>();

                        try {
                            if (response.getString("result").equals("true")) {
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                if (jsonArray.length() != 0) {
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);


                                        String stateID = jsonObject.getString("stateID");
                                        String name = jsonObject.getString("name");

                                        arrayListStateId.add(stateID);
                                        Log.e("dsvdsvds", stateID);
                                        arrayListState.add(name);
                                    }
                                } else {
                                    arrayListState.add("Not Available");
                                }


                            }


                            adapterState = new ArrayAdapter<>(AddAddressActivity.this, android.R.layout.simple_list_item_1, arrayListState);
                            adapterState.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                            binding.spinState.setAdapter(adapterState);
                            dialog.hideDialog();

                        } catch (JSONException e) {
                            Log.e("dsfkdsk", e.getMessage());
                            dialog.hideDialog();
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("rtfrgf", anError.getMessage());
                        dialog.hideDialog();
                    }
                });


    }


    private void showCity(String stStateId) {
        CustomDialog dialog = new CustomDialog();
        dialog.showDialog(R.layout.progress_layout, AddAddressActivity.this);

        AndroidNetworking.post(BaseUrl.BASEURL)
                .addBodyParameter("control", SHOW_CITY)
                .addBodyParameter("stateID", stStateId)
                .setTag("showing city Successfully")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("wsfcdsvdf", response.toString());
                        dialog.hideDialog();
                        arrayListCity = new ArrayList<>();
                        arrayListCityId = new ArrayList<>();

                        try {
                            if (response.getString("result").equals("true")) {
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                if (jsonArray.length() != 0) {
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);


                                        String cityID = jsonObject.getString("stateID");
                                        String stateID = jsonObject.getString("cityID");
                                        String name = jsonObject.getString("name");

                                        arrayListCityId.add(cityID);
                                        Log.e("dsvdsvds", cityID);
                                        arrayListCity.add(name);
                                    }
                                } else {
                                    arrayListCity.add("Not Available");
                                }


                            }


                            adapterCity = new ArrayAdapter<>(AddAddressActivity.this, android.R.layout.simple_list_item_1, arrayListCity);
                            adapterState.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                            binding.spinCity.setAdapter(adapterCity);
                            dialog.hideDialog();

                        } catch (JSONException e) {
                            Log.e("tyuyt", e.getMessage());
                            dialog.hideDialog();
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("rfhytr", anError.getMessage());
                        dialog.hideDialog();
                    }
                });


    }

    private void add_address(String stFullName, String stMobile, String st_address, String stStateId, String stCityId, String stAddressTittle) {
        st_Lat = SharedHelper.getKey(getApplicationContext(), AppConstats.SELECTLAT);
        st_Lng = SharedHelper.getKey(getApplicationContext(), AppConstats.SELECTLONG);

        Log.e("AddAddressActivity", "st_Lat: " + st_Lat);
        Log.e("AddAddressActivity", "st_Lat: " + st_Lng);
        CustomDialog dialog = new CustomDialog();
        dialog.showDialog(R.layout.progress_layout, AddAddressActivity.this);

        AndroidNetworking.post(BaseUrl.BASEURL)
                .addBodyParameter("control", ADD_ADDRESS)
                .addBodyParameter("userID", st_UserId)
                .addBodyParameter("name", stFullName)
                .addBodyParameter("mobile", stMobile)
                .addBodyParameter("stateID", stStateId)
                .addBodyParameter("cityID", stCityId)
                .addBodyParameter("address", st_address)
                .addBodyParameter("title", stAddressTittle)
                .addBodyParameter("latitude", st_Lat)
                .addBodyParameter("longitude", st_Lng)
                .setTag("address added Successfully")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("tyhtry", response.toString());
                        dialog.hideDialog();
                        try {
                            if (response.getString("result").equals("true")) {
                                String data = response.getString("data");
                                if (!data.isEmpty()){
                                    JSONObject jsonObject = new JSONObject(data);
                                    String addressID=jsonObject.getString("addressID");
                                    String userID=jsonObject.getString("userID");
                                    String stateID=jsonObject.getString("stateID");
                                    String cityID=jsonObject.getString("cityID");
                                    String address=jsonObject.getString("address");
                                    String mobile=jsonObject.getString("mobile");
                                    String title=jsonObject.getString("title");
                                    String latitude=jsonObject.getString("latitude");
                                    String longitude=jsonObject.getString("longitude");


                                    Toasty.success(context, response.getString("message"), Toast.LENGTH_SHORT).show();
                                   startActivity(new Intent(AddAddressActivity.this, ManageAddressActivity.class));
                                   finish();
                                }

                            }

                            else {
                                Toast.makeText(context, response.getString("message"), Toast.LENGTH_SHORT).show();
                                dialog.hideDialog();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            dialog.hideDialog();
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("tyuyti", anError.getMessage());
                        dialog.hideDialog();
                    }
                });

    }
}