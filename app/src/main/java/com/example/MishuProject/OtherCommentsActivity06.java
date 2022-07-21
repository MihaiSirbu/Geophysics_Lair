package com.example.MishuProject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class OtherCommentsActivity06 extends AppCompatActivity {
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
    private String additionalComments;

    private EditText additionalCommentEditText;

    private ArrayList<String> All_Collected_Data = new ArrayList<String>();

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
            operator = extras.getString("Operator");
            profile = extras.getString("Profile");
            terenData = extras.getStringArray("terenData");
            zgomotData = extras.getStringArray("zgomotData");
            localizat = extras.getString("localizat");
            if(localizat.equals("yes")){
                pichet = extras.getString("pichet");
                distance = extras.getString("distance");
            }
            else{
                pichet = "NONE";
                distance="NONE";
            }
        }

        // getting additional comments
        additionalCommentEditText = findViewById(R.id.additionalCommentsEditTextView);

        All_Collected_Data.add(operator);
        All_Collected_Data.add(profile);
        All_Collected_Data.add(projectStartTime);
        for(int i=0;i< terenData.length;i++){
            All_Collected_Data.add(terenData[i]);
        }
        for(int j=0;j< zgomotData.length;j++){
            All_Collected_Data.add(zgomotData[j]);
        }
        All_Collected_Data.add(localizat);
        All_Collected_Data.add(pichet);
        All_Collected_Data.add(distance);






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
        // finished !
        additionalComments = additionalCommentEditText.getText().toString().trim();
        All_Collected_Data.add(additionalComments);
        addFormsToDb(All_Collected_Data);
        startActivity(intent);
    }

    public void addFormsToDb(ArrayList<String> allFormData){
        DatabaseHelper db = new DatabaseHelper(OtherCommentsActivity06.this);
            db.addForms(All_Collected_Data.get(0),All_Collected_Data.get(1),All_Collected_Data.get(2),All_Collected_Data.get(3),All_Collected_Data.get(4),All_Collected_Data.get(5),All_Collected_Data.get(6),All_Collected_Data.get(7),All_Collected_Data.get(8),All_Collected_Data.get(9),All_Collected_Data.get(10),All_Collected_Data.get(11),All_Collected_Data.get(12),All_Collected_Data.get(13),All_Collected_Data.get(14),All_Collected_Data.get(15),All_Collected_Data.get(16),All_Collected_Data.get(17),All_Collected_Data.get(18),All_Collected_Data.get(19),All_Collected_Data.get(20),All_Collected_Data.get(21));



    }
}