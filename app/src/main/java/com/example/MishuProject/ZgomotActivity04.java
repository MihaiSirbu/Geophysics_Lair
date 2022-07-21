package com.example.MishuProject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class ZgomotActivity04 extends AppCompatActivity {
    // from activity 2
    private String profile;
    private String operator;
    private String projectStartTime;
    // taken from activity 3

    private String[] terenData;

    // Activity 4 zgomot specific

    private String[] zgomotData;
    List<String> StrengthData;
    private String vant = "moderat";
    private String trafic = "moderat";
    private String altZgomot;
    private String altZgomot_Distance;

    // VANT textview and seekbar
    private SeekBar vantSeekBar;
    private TextView vantTextviewDisplay;

    // Trafic textview and seekbar
    private SeekBar traficSeekbar;
    private TextView traficTextviewDisplay;

    // 2 edit texts for altzgomot and distance

    private EditText altzgomotEditText;
    private EditText altZgomot_DistanceEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zgomot04);

        // action bar




        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setTitle("Zgomot");
        }
        actionBar.setDisplayHomeAsUpEnabled(true);


        // retrieving project start time
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            projectStartTime = extras.getString("timeStartKEY");
            operator = extras.getString("Operator");
            profile = extras.getString("Profile");
            terenData = extras.getStringArray("terenData");


        }

        // getting the values for the 2 seekbars

        StrengthData= Arrays.asList(getResources().getStringArray(R.array.StrengthRanking_WIND_and_TRAFIC));

        // vant seekbar and textview
        vantSeekBar = (SeekBar) findViewById(R.id.VantSeekBar);
        vantTextviewDisplay = findViewById(R.id.VantStrengthDisplayTextView);

        vantSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                switch (seekBar.getId()) {

                    case R.id.VantSeekBar:
                        vant = StrengthData.get(i);
                        vantTextviewDisplay.setText(vant);
                        break;

                    case R.id.traficSeekBar:
                        trafic = StrengthData.get(i);
                        traficTextviewDisplay.setText(trafic);
                        break;
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });





        // Trafic seekbar and Textview
        traficSeekbar = findViewById(R.id.traficSeekBar);
        traficTextviewDisplay = findViewById(R.id.traficdisplayTextView);




        traficSeekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                switch (seekBar.getId()) {

                    case R.id.VantSeekBar:
                        vant = StrengthData.get(i);
                        vantTextviewDisplay.setText(vant);
                        break;

                    case R.id.traficSeekBar:
                        trafic = StrengthData.get(i);
                        traficTextviewDisplay.setText(trafic);
                        break;
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // 2 edit texts

        altzgomotEditText = findViewById(R.id.altZgomotEditText1);
        altZgomot_DistanceEditText = findViewById(R.id.AltZgomotDISTANCEeditTExt);












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

        altZgomot = altzgomotEditText.getText().toString().trim();
        altZgomot_Distance = altZgomot_DistanceEditText.getText().toString().trim();
        zgomotData = new String[]{vant,trafic,altZgomot,altZgomot_Distance};

        intent.putExtra("timeStartKEY",projectStartTime);
        intent.putExtra("Operator",operator);
        intent.putExtra("Profile",profile);
        intent.putExtra("terenData", terenData);
        intent.putExtra("zgomotData", zgomotData);

        startActivity(intent);
    }



}