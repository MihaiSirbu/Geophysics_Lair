package com.example.MishuProject;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddShot extends AppCompatActivity {

    String operator;
    String projectNumber;
    String projectStartTime;
    TextView ShotNumberDisplay;
    TextView LatitudeDisplay;
    TextView LongitudeDisplay;

    int shotNumber;

    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shot);

        // init shot number and displaying shot 0
        ShotNumberDisplay = findViewById(R.id.ShotNumberDisplayView);
        shotNumber = 0;
        ShotNumberDisplay.setText("Shot "+String.valueOf(shotNumber));


        // action bar
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setTitle("AddShot Page");
        }
        actionBar.setDisplayHomeAsUpEnabled(true);

        // RETREIVING EXTRAS FROM PREVIOUS ACTIVITY

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            operator = extras.getString("operatorKEY");
            projectNumber = extras.getString("projectKEY");
            projectStartTime = extras.getString("timeStartKEY");
        }

        LatitudeDisplay = findViewById(R.id.LatituteTxtView);
        LongitudeDisplay = findViewById(R.id.LongitudeTxtView);

        // GETTING GPS COORDINATES

        // initializing fusedlocationproviderclient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);



        // DATABASE

        DatabaseHelper db = new DatabaseHelper(AddShot.this);

        // start project time display

        TextView projectStartTimeDisplay = findViewById(R.id.projectStartTimeText);
        projectStartTimeDisplay.setText("Project Start Time: "+projectStartTime.toString());


        // operator display
        TextView OperatorDisplayText = findViewById(R.id.OperatorDisplayTextView);
        OperatorDisplayText.setText("Operator: "+operator);


        // project number display
        TextView ProjectNumberDisplay = findViewById(R.id.ProjectNumberDisplayTextView);
        ProjectNumberDisplay.setText("Project Number: "+projectNumber);


        // TIME DISPLAY REC BUTTON

        Button timeButtonDisplay = findViewById(R.id.RecButton);

        timeButtonDisplay.setOnClickListener(v -> {

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat(" d MMM yyyy HH:mm:ss.SSS ");
            String shot_time =  format.format(calendar.getTime());

            // DISPLAYING THE TIME OF THE SHOT

            TextView textView = findViewById(R.id.ShotTimeDisplay);
            shot_time.toString().trim();
            textView.setText("Last recorded shot time: "+shot_time);

            // GPS STUFF

            // check permissions
            if(ActivityCompat.checkSelfPermission(AddShot.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                // permission is granted
                getLocation();
            }
            else{
                // when permission isn't granted
                ActivityCompat.requestPermissions(AddShot.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
            }

            // ADDING TO DATABASE INFO
            shotNumber += 1;
            ShotNumberDisplay.setText("Shot "+String.valueOf(shotNumber));
            db.addShot(operator,projectNumber,String.valueOf(shotNumber),shot_time);
        });

        // deleting last entry in database button work

        Button DeleteLastEntry = findViewById(R.id.deleteDBEntryButton);
        DeleteLastEntry.setOnLongClickListener(view -> {
            // updating shot number
            if(shotNumber == 0){
                Toast.makeText(this,"No shots taken in this project!", Toast.LENGTH_SHORT).show();
                return true;
            }
            shotNumber -= 1;
            ShotNumberDisplay.setText("Shot "+String.valueOf(shotNumber));
            db.deletelastEntry();
            Toast.makeText(this,"removed last entry from database!", Toast.LENGTH_SHORT).show();
            return true;

        });






        // GO TO NEXT PAGE
        Button TerenActivityButton = (Button) findViewById(R.id.nextButton);
        TerenActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2page = new Intent(AddShot.this,TerenActivity03.class);
                startActivity(intent2page);
            }
        });
    }

    private void getLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                // init location
                Location loc = task.getResult();
                if(loc != null){
                    // init geocoder
                    Geocoder geocoder = new Geocoder(AddShot.this, Locale.getDefault());
                    // init address list
                    try {
                        List<Address> addresses = geocoder.getFromLocation(loc.getLatitude(),loc.getLongitude(),1);

                        LatitudeDisplay.setText("Latitude: "+ addresses.get(0).getLatitude());
                        LongitudeDisplay.setText("Longitude: "+ addresses.get(0).getLongitude());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }
}