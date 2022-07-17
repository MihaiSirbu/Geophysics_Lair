package com.example.MishuProject;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddShot extends AppCompatActivity {

    String operator;
    String projectNumber;
    String projectStartTime;
    int shotNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shot);

        shotNumber = 0;

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
        projectStartTimeDisplay.setText(projectStartTime.toString());

        // operator display
        TextView OperatorDisplayText = findViewById(R.id.OperatorDisplayTextView);
        OperatorDisplayText.setText(operator);

        // project number display
        TextView ProjectNumberDisplay = findViewById(R.id.ProjectNumberDisplayTextView);
        ProjectNumberDisplay.setText(projectNumber);

        // TIME DISPLAY REC BUTTON

        Button timeButtonDisplay = findViewById(R.id.RecButton);

        timeButtonDisplay.setOnClickListener(v -> {

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat(" d MMM yyyy HH:mm:ss.SSS ");
            String shot_time =  format.format(calendar.getTime());

            // DISPLAYING THE TIME OF THE SHOT

            TextView textView = findViewById(R.id.ShotTimeDisplay);
            shot_time.toString().trim();
            textView.setText(shot_time);

            // ADDING TO DATABASE INFO
            shotNumber += 1;
            db.addShot(operator,projectNumber,String.valueOf(shotNumber),shot_time);
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