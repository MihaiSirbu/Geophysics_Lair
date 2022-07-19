package com.example.MishuProject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ZgomotActivity04 extends AppCompatActivity {
    // taken from activity 3
    private String projectStartTime;
    private String[] terenData;

    // Activity 4 zgomot specific

    private String[] zgomotData;
    private String vant;
    private String trafic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zgomot04);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setTitle("Zgomot");
        }
        actionBar.setDisplayHomeAsUpEnabled(true);


        // retrieving projhect start time
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            projectStartTime = extras.getString("timeStartKEY");
            terenData = extras.getStringArray("terenData");


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
        Intent intent = new Intent(ZgomotActivity04.this, ForajActivity05.class);
        zgomotData = new String[]{vant,trafic};

        intent.putExtra("timeStartKEY",projectStartTime);
        intent.putExtra("terenData", terenData);
        intent.putExtra("zgomotData", zgomotData);
        startActivity(intent);
    }

}