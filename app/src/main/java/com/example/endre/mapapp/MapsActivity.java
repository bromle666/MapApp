package com.example.endre.mapapp;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    List<MarkerOptions> toilets = new ArrayList<MarkerOptions>();
    Location mLastLocation = null;
    MarkerOptions currentPos = null;
    private GoogleApiClient mGoogleApiClient = null;
    private LocationRequest mLocationRequest = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        buildGoogleApiClient();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {

        populateToiletList();

        for (int i = 0; i < toilets.size(); i++)
            mMap.addMarker(toilets.get(i));
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                 .addApi(LocationServices.API)
                 .build();
    }

    private void populateToiletList() {
        toilets.add(new MarkerOptions().position(new LatLng(60.3920969, 5.32812829999999)).title("RÅDHUSET"));
        toilets.add(new MarkerOptions().position(new LatLng(60.394554, 5.324099)).title("TORGHALLEN"));
        toilets.add(new MarkerOptions().position(new LatLng(60.392209, 5.324011)).title("SUNDT MOTEHUS"));
        toilets.add(new MarkerOptions().position(new LatLng(60.3929282999999, 5.32457879999999)).title("GALLERIET"));
        toilets.add(new MarkerOptions().position(new LatLng(60.3928930999999, 5.32662989999999)).title("XHIBITION"));
        toilets.add(new MarkerOptions().position(new LatLng(60.3888359, 5.3341752)).title("NONNESETER TERMINAL"));
        toilets.add(new MarkerOptions().position(new LatLng(60.388298, 5.333801)).title("TERMINAL NORD"));
        toilets.add(new MarkerOptions().position(new LatLng(60.394116, 5.32278)).title("KLØVERHUSET"));
        toilets.add(new MarkerOptions().position(new LatLng(60.397359,5.313963)).title("C. SUNDTSGATE"));
        toilets.add(new MarkerOptions().position(new LatLng(60.397557, 5.307858)).title("NORDNES SKOLE"));
        toilets.add(new MarkerOptions().position(new LatLng(60.3685595, 5.3620731)).title("HAUKELANDSVEIEN 51"));
    }

    private void setUpCurrentLocation(Location currLoc) {

        LatLng coords = new LatLng(currLoc.getLatitude(), currLoc.getLongitude());
        currentPos = new MarkerOptions().position(coords).title("Current position");
        mMap.addMarker(currentPos);
    }

    @Override
    public void onConnected(Bundle bundle) {

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
