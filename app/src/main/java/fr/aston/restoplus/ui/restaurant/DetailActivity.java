package fr.aston.restoplus.ui.restaurant;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.mapbox.directions.DirectionsCriteria;
import com.mapbox.directions.MapboxDirections;
import com.mapbox.directions.service.models.DirectionsRoute;
import com.mapbox.directions.service.models.Waypoint;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import java.io.IOException;

import fr.aston.restoplus.R;
import fr.aston.restoplus.ui.restaurant.model.Restaurant;
import retrofit.Response;

public class DetailActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_FINE_LOCATION = 1111;
    private TextView textViewRestaurant;
    private TextView textViewLoc;
    private MapView mapView;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.access_token));
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textViewRestaurant = findViewById(R.id.textViewRestaurant);
        textViewLoc = findViewById(R.id.textViewLoc);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        // Envoie des données du restaurant sur la vue detail
        Restaurant restaurant = (Restaurant) getIntent().getExtras().get("restaurant");
        textViewRestaurant.setText(restaurant.getFields().getEnseigne());
        // Préparation de l'itineraire
        Waypoint origin = new Waypoint(latLng.getLatitude(),latLng.getLongitude());
        Waypoint destination = new Waypoint(restaurant.getFields().getLat_ok(),restaurant.getFields().getLong_ok());

        // Build the client object
        MapboxDirections client = new MapboxDirections.Builder()
                .setAccessToken(getString(R.string.access_token))
                .setOrigin(origin)
                .setDestination(destination)
                .setProfile(DirectionsCriteria.PROFILE_DRIVING)
                .build();
        try {
            Response response = client.execute();
            // getRoutes() a prévoire
            DirectionsRoute route = response.body().getRoutes().get(0);
            int distance = route.getDistance(); // 26446 (in meters)
        } catch (IOException e) {
            e.printStackTrace();
        }

        mapView = (MapView) findViewById(R.id.mapView);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder()
                                .target(new LatLng(48.86448095,2.354))
                                .zoom(15)
                                .tilt(45.0)
                                .build()),
                        10000);
                mapboxMap.addMarker(new MarkerOptions()
                                .position(new LatLng( 48.86448095,2.354))
                                .title(restaurant.getFields().getEnseigne())
                                .snippet("France")
                );
            }
        });
        mapView.onCreate(savedInstanceState);


        getLastKnowLocation();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


    }

    private void getLastKnowLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, REQUEST_CODE_FINE_LOCATION);
            }
            return;
        }
        mFusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                latLng = new LatLng(location.getLatitude(),location.getLongitude());
                textViewLoc.setText(latLng.toString());
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_CODE_FINE_LOCATION && permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION)) {

            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                getLastKnowLocation();

            } else {
                Toast.makeText(this, "Itinéraire impossible", Toast.LENGTH_SHORT).show();
            }

        }

    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

}
