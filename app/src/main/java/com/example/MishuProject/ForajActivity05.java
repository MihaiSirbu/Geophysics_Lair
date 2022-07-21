package com.example.MishuProject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class ForajActivity05 extends AppCompatActivity {
    //taken from activity 2
    private String operator;
    private String profile;
    private String projectStartTime;
    // taken from activity 3
    private String[] terenData;

    // Activity 4 zgomot specific

    private String[] zgomotData;

    // Activity 5 Foraj specific

    // since localizat is by default unchecked, we set this to false
    private boolean topass = false;
    private String localizat;
    private String pichet;
    private String distance;

    private CheckBox myCheckbox;
    private TextInputLayout PichetLayout;
    private TextInputLayout DistanceLayout;
    private EditText Pichet;
    private EditText Distance;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foraj05);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setTitle("Foraj");
        }
        actionBar.setDisplayHomeAsUpEnabled(true);

        //

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            projectStartTime = extras.getString("timeStartKEY");
            operator = extras.getString("Operator");
            profile = extras.getString("Profile");
            terenData = extras.getStringArray("terenData");
            zgomotData = extras.getStringArray("zgomotData");
        }


        // Visibility of Distance and Pichet based on checkbox

        myCheckbox = findViewById(R.id.LocalizatCheckbox);
        Pichet = findViewById(R.id.PichetIDTEXT);
        Distance = findViewById(R.id.DistanceIDTEXT);
        PichetLayout = findViewById(R.id.PichetID);
        DistanceLayout = findViewById(R.id.DistanceID);


        boolean ischecked = myCheckbox.isChecked();

        changeTextVisibility(ischecked);





        myCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = ((CheckBox)view).isChecked();
                changeTextVisibility(isChecked);
            }
        });




        // GOING TO NEXT ACTIVITY

        Button NextButton = findViewById(R.id.NextButton);
        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNextActivity();

            }
        });




    }
    private void changeTextVisibility(boolean isChecked){
        if(isChecked){
            PichetLayout.setVisibility(View.VISIBLE);
            DistanceLayout.setVisibility(View.VISIBLE);
            topass = true;

        }
        else{
            PichetLayout.setVisibility(View.INVISIBLE);
            DistanceLayout.setVisibility(View.INVISIBLE);
            topass = false;

        }
    }
    // next activity opener
    public void openNextActivity(){
        Intent intent = new Intent(ForajActivity05.this,OtherCommentsActivity06.class);

        // if the box is checked, then we add pichet and distance to our data to transfer

        if(topass){
            localizat = "yes";
            pichet = Pichet.getText().toString().trim();
            distance = Distance.getText().toString().trim();
            intent.putExtra("pichet", pichet);
            intent.putExtra("distance", distance);

        }
        else{
            localizat = "no";
        }

        intent.putExtra("timeStartKEY",projectStartTime);
        intent.putExtra("Operator",operator);
        intent.putExtra("Profile",profile);
        intent.putExtra("terenData", terenData);
        intent.putExtra("zgomotData", zgomotData);

        intent.putExtra("localizat", localizat);


        startActivity(intent);
    }

}