package com.example.locationtracker;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements LocationListener{
	
	LocationManager locationManager ;
	String provider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Getting LocationManager object
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);   //retrieving     
        
        // Creating an empty criteria object
        Criteria criteria = new Criteria();
        
        // Getting the name of the provider that meets the criteria
        provider = locationManager.getBestProvider(criteria, false);//name of provider that meets the best criteria
        
                
        if(provider!=null && !provider.equals("")){
        	
        	// Get the location from the given provider 
            Location location = locationManager.getLastKnownLocation(provider);
                       
            locationManager.requestLocationUpdates(provider, 2000, 1, this);//keep on updating the location
            
            
            if(location!=null)
            	onLocationChanged(location);
            else
            	startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));//if gps in disabled
            
        }else{
        	Toast.makeText(getBaseContext(), "No Provider Found", Toast.LENGTH_SHORT).show();//if no provider found
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
	@Override
	public void onLocationChanged(Location location) {
		// Getting reference to TextView tv_longitude
		TextView tvLongitude = (TextView)findViewById(R.id.tv_longitude);
		
		// Getting reference to TextView tv_latitude
		TextView tvLatitude = (TextView)findViewById(R.id.tv_latitude);
		
		// Setting Current Longitude
		tvLongitude.setText("Longitude:" + location.getLongitude());
		
		// Setting Current Latitude
		tvLatitude.setText("Latitude:" + location.getLatitude() );
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub		
	}
}
    
