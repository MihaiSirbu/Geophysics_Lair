package com.example.MishuProject;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddShot extends AppCompatActivity {

    String operator;
    String projectNumber;
    String projectStartTime;
    TextView ShotNumberDisplay;
    int shotNumber;

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

            // ADDING TO DATABASE INFO
            shotNumber += 1;
            ShotNumberDisplay.setText("Shot "+String.valueOf(shotNumber));
            db.addShot(operator,projectNumber,String.valueOf(shotNumber),shot_time);
        });

        // deleting last entry in database button work

        Button DeleteLastEntry = findViewById(R.id.deleteDBEntryButton);
        DeleteLastEntry.setOnLongClickListener(view -> {
            // updating shot number
            /*if(shotNumber == 0){
                Toast.makeText(this,"No shots taken in this project!", Toast.LENGTH_SHORT).show();
                return true;
            }*/
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
}