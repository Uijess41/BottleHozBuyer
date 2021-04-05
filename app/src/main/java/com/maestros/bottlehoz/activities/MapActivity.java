package com.maestros.bottlehoz.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.maestros.bottlehoz.Map.ManuallyMapActivity;
import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.utils.AppConstats;
import com.maestros.bottlehoz.utils.GPSTracker;
import com.maestros.bottlehoz.utils.SharedHelper;
import com.skyfishjy.library.RippleBackground;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    LatLng latLng;
    double latitude;
    double longtitude;
    GPSTracker gpsTracker;
    ImageView centerImage;
     TextView txtAddress,txtCity;
     Button btnChange,btnNext;
     String st_Lat="",st_Long="",st_address="",st_City="";
     public static final String ADDRESS="ADDRESS";
     public static final String LAT="LAT";
     public static final String LNG="LNG";


    RippleBackground rippleBackground;
    private static final int AUTOCOMPLETE_REQUEST_CODE_SEARCH = 1111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        txtAddress=findViewById(R.id.txtAddress);
        centerImage=findViewById(R.id.centerImage);
        txtCity=findViewById(R.id.txtCity);
        btnChange=findViewById(R.id.btnChange);
        btnNext=findViewById(R.id.btnNext);
        rippleBackground = findViewById(R.id.rippleLayout);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);


        //rippleBackground.stopRippleAnimation();
        gpsTracker = new GPSTracker(this);

        latitude = gpsTracker.getLatitude();
        longtitude = gpsTracker.getLongitude();

        Log.e("fgngvbnbv", latitude + "");
        Log.e("fgngvbnbv", longtitude + "");

        if (!Places.isInitialized()) {
            Places.initialize(MapActivity.this, getString(R.string.api_key), Locale.getDefault());


        }


        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                        .build(MapActivity.this);
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE_SEARCH);

            }
        });


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //Place current location marker
        latLng = new LatLng(latitude, longtitude);
        //mMap.setMyLocationEnabled( true );
        mMap.setTrafficEnabled(true);
        ////set direction circle marker bootom right on map
        mMap.setPadding(10, 800, 10, 10);


        updateLocation(latLng);
        ////SHOW LOCATION OF MARKER AND COLOUR
        mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title("My position")
               .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));

              mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));





    }


    private void updateLocation(final LatLng centerLatLng) {


        if (centerLatLng != null) {


            try {
                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(this, Locale.getDefault());

                Log.e("xzlkclkk", centerLatLng.latitude + "");
                Log.e("xzlkclkk", centerLatLng.longitude + "");
                addresses = geocoder.getFromLocation(centerLatLng.latitude, centerLatLng.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                String    address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String knownName = addresses.get(0).getFeatureName();
                String select_lat = Double.toString(addresses.get(0).getLatitude());
                String select_long = Double.toString(addresses.get(0).getLongitude());

            /*    Log.e("MapActivity", "address: " +address);
                Log.e("MapActivity", "city: " +city);
                Log.e("MapActivity", "state: " +state);*/

                txtCity.setText(city);
                txtAddress.setText(address);


                btnNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent();
                        intent.putExtra(ADDRESS,address);
                        intent.putExtra(LAT, select_lat);
                        intent.putExtra(LNG, select_long);
                        setResult(Activity.RESULT_OK, intent);
                        finish();

                    }
                });




            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE_SEARCH) {
            if (resultCode == RESULT_OK) {

                if (data != null) {

                    Place place = Autocomplete.getPlaceFromIntent(data);
                    Log.e("Place", "Place: " + place.getName() + ", " + place.getLatLng() +
                            place.getAddress());

                    LatLng pickUplatlng = place.getLatLng();

                    Log.e("MapActivity", "onActivityResult: " +pickUplatlng);
                    mMap.clear();


                    try {
                        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

                        if (pickUplatlng != null) {

                            double st_SearchLat = pickUplatlng.latitude;
                            double st_SearchLong = pickUplatlng.longitude;
                            Log.e("Scflkl", st_SearchLat + "");
                            Log.e("Scflkl", st_SearchLong + "");

                            st_Lat = Double.toString(st_SearchLat);
                            st_Long = Double.toString(st_SearchLong);

                            Log.e("sdkmcxkzkl", "Lat: " + st_Lat);
                            Log.e("sdkmcxkzkl", "Lng: " + st_Long);

                         /*   SharedHelper.putKey(getApplicationContext(), AppConstats.SEARCH_LOCATION_LAT, pickUplatlng.latitude + "");
                            SharedHelper.putKey(getApplicationContext(), AppConstats.SEARCH_LOCATION_LNG, pickUplatlng.longitude + "");*/

                            Log.e("rtrere", "latlng" + pickUplatlng.latitude + "," + pickUplatlng.longitude);
                            List<Address> addressList = geocoder.getFromLocation(pickUplatlng.latitude, pickUplatlng.longitude, 1);

                            if (addressList != null) {
                                st_address = addressList.get(0).getAddressLine(0);
                                st_City = addressList.get(0).getSubAdminArea();
                                st_Lat = Double.toString( addressList.get(0).getLatitude());
                                st_Long = Double.toString( addressList.get(0).getLongitude());

                                Log.e("dsgvsdfgb", "st_address : " + addressList.get(0).getAdminArea());
                                Log.e("dsgvsdfgb", "st_City : " +  addressList.get(0).getSubAdminArea());
                                Log.e("dsgvsdfgb", "locality : " +  addressList.get(0).getSubLocality());
                                Log.e("dsgvsdfgb", "lati : " +  addressList.get(0).getLatitude());
                                Log.e("dsgvsdfgb", "long : " +  addressList.get(0).getLongitude());


                                txtAddress.setText(st_address);
                                txtCity.setText(st_City);


                                btnNext.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        /*SharedHelper.putKey(getApplicationContext(), AppConstats.SELECTADDRESS, st_address);
                                        SharedHelper.putKey(getApplicationContext(), AppConstats.SELECTCITY, st_City);
                                        SharedHelper.putKey(getApplicationContext(), AppConstats.SELECTLAT, st_Lat);
                                        SharedHelper.putKey(getApplicationContext(), AppConstats.SELECTLONG, st_Long);*/

                                        Intent intent = new Intent();
                                        intent.putExtra(ADDRESS,st_address);
                                        intent.putExtra(LAT, st_Lat);
                                        intent.putExtra(LNG, st_Long);
                                        setResult(Activity.RESULT_OK, intent);
                                        finish();

                                    }
                                });

                                ////SHOW LOCATION OF MARKER AND COLOUR


                                Log.e("sdfsfd", " " +pickUplatlng);
                                mMap.addMarker(new MarkerOptions()
                                        .position(pickUplatlng)
                                        .title("My position")
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
                                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pickUplatlng, 15));







                            }
                        }


                    } catch (Exception e) {
                        Log.e("gfvdfrgvd", e.getMessage(), e);
                    }


                }


            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                Status status = Autocomplete.getStatusFromIntent(data);
                assert status.getStatusMessage() != null;
                Log.i("oireuftoe", status.getStatusMessage());
            }

        }

    }
}