package com.example.MishuProject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OtherCommentsActivity06 extends AppCompatActivity {
    // taken from activity 3
    private String projectStartTime;
    private String[] terenData;

    // Activity 4 zgomot specific

    private String[] zgomotData;

    // Activity 5 Foraj specific

    // since localizat is by default unchecked, we set this to false
    private boolean topass = false;
    private String localizat;
    private String pichet;
    private String distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_comments06);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setTitle("Additional Comments");
        }
        actionBar.setDisplayHomeAsUpEnabled(true);



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            projectStartTime = extras.getString("timeStartKEY");
            terenData = extras.getStringArray("terenData");
            zgomotData = extras.getStringArray("zgomotData");
            localizat = extras.getString("localizat");
            if(localizat == "yes"){
                pichet = extras.getString("pichet");
                distance = extras.getString("distance");
            }
        }





        // GOING TO NEXT ACTIVITY

        Button NextButton = findViewById(R.id.NextButton);
        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNextActivity();

            }
        });




    }
    // next activity opener
    public void openNextActivity(){
        Intent intent = new Intent(OtherCommentsActivity06.this, MainActivity01.class);
        startActivity(intent);
    }
}