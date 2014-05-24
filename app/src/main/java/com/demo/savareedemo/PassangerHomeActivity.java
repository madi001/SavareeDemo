package com.demo.savareedemo;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class PassangerHomeActivity extends ActionBarActivity {

    private GoogleMap googleMap;
    ListView list;
    CustomAdapter adapter;
    public  PassangerHomeActivity CustomListView = null;
    public ArrayList<ListModel> CustomListViewValuesArr = new ArrayList<ListModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passanger_home);
        CustomListView = this;
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.app_name);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#374258"));
        actionBar.setBackgroundDrawable(colorDrawable);
        try {
            // Loading map
            initilizeMap();

        } catch (Exception e) {
            e.printStackTrace();
        }

        /******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
        setListData();

        Resources res =getResources();
        list = ( ListView )findViewById( R.id.list );  // List defined in XML ( See Below )

        /**************** Create Custom Adapter *********/
        adapter=new CustomAdapter( CustomListView, CustomListViewValuesArr,res );
        list.setAdapter( adapter );

        Button search_ride = (Button)findViewById(R.id.search_ride);
        search_ride.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PassangerHomeActivity.this,SearchRideActivity.class);
                startActivity(intent);
            }
        });

        Button near_me = (Button)findViewById(R.id.near_me);
        near_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    // Loading map
                    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    Criteria criteria = new Criteria();

                    Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
                    if (location != null) {
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                new LatLng(location.getLatitude(), location.getLongitude()), 13));

                        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                                .zoom(14)                   // Sets the zoom
                                .bearing(90)                // Sets the orientation of the camera to east
                                .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                                .build();


                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



    }

    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.routemap)).getMap();
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();

            Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
            if (location != null)
            {
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng(location.getLatitude(), location.getLongitude()), 13));

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                        .zoom(14)                   // Sets the zoom
                        .bearing(90)                // Sets the orientation of the camera to east
                        .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                        .build();                   // Creates a CameraPosition from the builder
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(location.getLatitude(), location.getLongitude()))
                        .title("Hamad Hassan")
                        .alpha(0.7f)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));

                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(location.getLatitude()+0.008, location.getLongitude()+0.008))
                        .title("Mohsin Saleem")
                        .alpha(0.7f)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.markerdriver)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(location.getLatitude()-0.011, location.getLongitude()-0.011))
                        .title("Aleena Aslam")
                        .alpha(0.7f)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.markerdriver)));

            }

            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    public void setListData()
    {

        for (int i = 0; i < 2; i++) {

            final ListModel sched = new ListModel();

            /******* Firstly take data in model object ******/
            sched.setName("Hamad Hassan");
            sched.setRides("No of Rides: 4");
            sched.setAddress("Phase 2, DHA");
            sched.setSeats("1 available seats");
            sched.setImage("@drawable/userpic");
            /******** Take Model Object in ArrayList **********/
            CustomListViewValuesArr.add( sched );
        }

    }

    public void onItemClick(int mPosition)
    {
        ListModel tempValues = ( ListModel ) CustomListViewValuesArr.get(mPosition);
        // SHOW ALERT

        Toast.makeText(CustomListView,
                "" + tempValues.getName(),
        Toast.LENGTH_LONG)
        .show();

        Intent intent = new Intent(PassangerHomeActivity.this,Detail_DriverActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.passanger_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
