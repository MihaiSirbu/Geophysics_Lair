package com.example.MishuProject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TerenActivity03 extends AppCompatActivity {
    private String projectStartTime;
    private String tip;
    private String cultura;
    private String tipSol;
    private String suprafata;
    private String uscat;
    private String priza;

    String[] terenData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teren03);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setTitle("Teren Information");
        }

        // retrieving projhect start time
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            projectStartTime = extras.getString("timeStartKEY");
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
        Intent intent = new Intent(TerenActivity03.this,ZgomotActivity04.class);
        terenData = new String[] {tip, cultura,tipSol,suprafata,uscat,priza};

        intent.putExtra("timeStartKEY",projectStartTime);
        intent.putExtra("terenData", terenData);
        startActivity(intent);
    }
}