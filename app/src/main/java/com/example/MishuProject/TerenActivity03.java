package com.example.MishuProject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TerenActivity03 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String projectStartTime;
    private String FolosintaTeren;
    private String FolosintaTeren_Other;
    private String cultura;
    private String cultura_Other;
    private String tipSol;
    private String suprafata;
    private String suprafata_OtherMeters;
    private String umed;
    private String priza;

    private TextView elevationChangeTxtView;
    private EditText elevationmetersEditText;
    private EditText folosintaTerenEditText;
    private EditText culturaEditText;


    Spinner dropdownTeren;

    Spinner dropdownCultura;

    Spinner dropdownSuprafata;
    // string array to pass to future activities containing all the data from this activity
    String[] terenData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teren03);

        // action bar
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setTitle("Teren Information");
        }

        // retrieving project start time
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            projectStartTime = extras.getString("timeStartKEY");
        }

        // setting up textview and edittext

        elevationChangeTxtView = findViewById(R.id.textViewifSuprafataSelected);
        elevationmetersEditText = findViewById(R.id.addMetersTExt);

        folosintaTerenEditText = findViewById(R.id.OtherCommentsFolosintaTerenText);
        culturaEditText = findViewById(R.id.OtherCommentsculturaText);



        // setting up spinners

        dropdownTeren = findViewById(R.id.spinnerfolosintateren);
        ArrayAdapter<CharSequence> adapterteren = ArrayAdapter.createFromResource(this,R.array.folosintaTeren, android.R.layout.simple_spinner_item);
        dropdownTeren.setAdapter(adapterteren);

        dropdownCultura = findViewById(R.id.spinnertipCultura);
        ArrayAdapter<CharSequence> adapterCultura = ArrayAdapter.createFromResource(this,R.array.cultura, android.R.layout.simple_spinner_item);
        dropdownCultura.setAdapter(adapterCultura);

        dropdownSuprafata = findViewById(R.id.spinnerSuprafata);
        ArrayAdapter<CharSequence> adapterSuprafata = ArrayAdapter.createFromResource(this,R.array.Suprafata, android.R.layout.simple_spinner_item);
        dropdownSuprafata.setAdapter(adapterSuprafata);

        dropdownTeren.setOnItemSelectedListener(this);
        dropdownCultura.setOnItemSelectedListener(this);
        dropdownSuprafata.setOnItemSelectedListener(this);

        // end of spinners

        // radio groups and buttons






















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
        // getting spinner information -
        FolosintaTeren = dropdownTeren.getSelectedItem().toString();
        cultura = dropdownCultura.getSelectedItem().toString();
        suprafata = dropdownSuprafata.getSelectedItem().toString();

        // getting spinner's Other information

        FolosintaTeren_Other = folosintaTerenEditText.getText().toString().trim();
        cultura_Other = culturaEditText.getText().toString().trim();
        suprafata_OtherMeters = elevationmetersEditText.getText().toString().trim();

        // rest of data is handled by the radio button functions below




        terenData = new String[] {FolosintaTeren,FolosintaTeren_Other, cultura,cultura_Other,suprafata,suprafata_OtherMeters,tipSol,umed,priza};

        intent.putExtra("timeStartKEY",projectStartTime);
        intent.putExtra("terenData", terenData);
        startActivity(intent);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        if(text.equals("schimbare de elevatie")){
            elevationChangeTxtView.setVisibility(View.VISIBLE);
            elevationmetersEditText.setVisibility(View.VISIBLE);

        }
        else{
            elevationChangeTxtView.setVisibility(View.INVISIBLE);
            elevationmetersEditText.setVisibility(View.INVISIBLE);

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void onRadioButtonClickedTIPSOL(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.afanat:
                if (checked)
                    tipSol = "afanat";
                    break;
            case R.id.semiAfanat:
                if (checked)
                    tipSol = "semi-afanat";
                    break;
            case R.id.tare:
                if (checked)
                    tipSol = "tare";
                    break;
        }
    }

    public void onRadioButtonClickedUmed(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.uscatButton:
                if (checked)
                    umed = "uscat";
                break;
            case R.id.umedButton:
                if (checked)
                    umed = "umed";
                break;

        }
    }

    public void onRadioButtonClickedPriza(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.foartebunbutton:
                if (checked)
                    priza = "foarte buna";
                break;
            case R.id.bunbutton:
                if (checked)
                    priza = "buna";
                break;
            case R.id.mediumbutton:
                if (checked)
                    priza = "medie";
                break;
            case R.id.slabbutton:
                if (checked)
                    priza = "slaba";
                break;
            case R.id.foarteproastbutton:
                if (checked)
                    priza = "foarte proasta";
                break;
        }
    }
}