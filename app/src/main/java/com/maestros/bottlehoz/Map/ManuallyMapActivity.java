package com.maestros.bottlehoz.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.activities.MapActivity;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ManuallyMapActivity extends AppCompatActivity  {
    AutoCompleteTextView autoCompleteSearch;
    String st_Lat="",st_Long="";
    private static final int AUTOCOMPLETE_REQUEST_CODE_SEARCH = 1111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manually_map);
        autoCompleteSearch = findViewById(R.id.autoCompleteSearch);

        if (!Places.isInitialized()) {
            Places.initialize(ManuallyMapActivity.this, getString(R.string.api_key), Locale.getDefault());
        }

        autoCompleteSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                        .build(ManuallyMapActivity.this);
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE_SEARCH);

            }
        });
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


                                //autoCompleteSearch.setText(address);

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