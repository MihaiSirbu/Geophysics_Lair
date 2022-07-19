package com.example.MishuProject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class TerenActivity03 extends AppCompatActivity {
    private String projectStartTime;
    private String FolosintaTeren;
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

        // retrieving project start time
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            projectStartTime = extras.getString("timeStartKEY");
        }

        // setting up spinners

        //get the spinner from the xml.
        Spinner dropdownTeren = findViewById(R.id.spinnerfolosintateren);
        ArrayAdapter<CharSequence> adapterteren = ArrayAdapter.createFromResource(this,R.array.folosintaTeren, android.R.layout.simple_spinner_item);
        dropdownTeren.setAdapter(adapterteren);

        Spinner dropdownCultura = findViewById(R.id.spinnerfolosintateren);
        ArrayAdapter<CharSequence> adapterCultura = ArrayAdapter.createFromResource(this,R.array.cultura, android.R.layout.simple_spinner_item);
        dropdownCultura.setAdapter(adapterCultura);

        Spinner downdownSuprafata = findViewById(R.id.spinnerfolosintateren);
        ArrayAdapter<CharSequence> adapterSuprafata = ArrayAdapter.createFromResource(this,R.array.Suprafata, android.R.layout.simple_spinner_item);
        downdownSuprafata.setAdapter(adapterSuprafata);


















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
        terenData = new String[] {FolosintaTeren, cultura,tipSol,suprafata,uscat,priza};

        intent.putExtra("timeStartKEY",projectStartTime);
        intent.putExtra("terenData", terenData);
        startActivity(intent);
    }
}