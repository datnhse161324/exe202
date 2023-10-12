package com.example.exe201;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap ggMap = null;
    SupportMapFragment mapFragment;
    LatLng latLng;
    Marker marker;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        searchView = findViewById(R.id.etYourLocation);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // on below line we are getting the
                // location name from search view.
                setGgMap(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        Button btnChoose= findViewById(R.id.selectLoca);
        btnChoose.setOnClickListener(view -> {
            Intent intent= new Intent(MapActivity.this, OrderActivity.class);
            intent.putExtra("location",searchView.getQuery().toString());
            setResult(RESULT_OK, intent);
            finish();
        });
    }
    private void setGgMap(String location){
        // below line is to create a list of address
        // where we will store the list of all address.
        List<Address> addressList;

        // checking if the entered location is null or not.
        if (location != null) {
            // on below line we are creating and initializing a geo coder.
            Geocoder geocoder = new Geocoder(MapActivity.this);
            try {
                // on below line we are getting location from the
                // location name and adding that location to address list.
                addressList = geocoder.getFromLocationName(location, 1);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
            // on below line we are getting the location
            // from our list a first position.
            Address address = addressList.get(0);

            // on below line we are creating a variable for our location
            // where we will add our locations latitude and longitude.
            latLng = new LatLng(address.getLatitude(), address.getLongitude());
            if(marker!= null){
                marker.remove();
            }
            ggMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
            // on below line we are adding marker to that position.
            marker = ggMap.addMarker(new MarkerOptions().position(latLng).title(location));
            // below line is to animate camera to that position.
            ggMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11));
        }
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        ggMap = googleMap;
        String location = getIntent().getStringExtra("address");
        searchView.setQuery(location, true);
        String query =searchView.getQuery().toString();
        if(!query.isEmpty()){
            setGgMap(query);
        }
        if(latLng==null){
            latLng = new LatLng(10.762622, 106.660172);
//            marker= ggMap.addMarker(new MarkerOptions().position(latLng).title("Marker in HCM city"));
        }
        ggMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        ggMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));

    }
}

