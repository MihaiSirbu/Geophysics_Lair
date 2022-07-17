package com.example.MishuProject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ForacActivity05 extends AppCompatActivity {

    CheckBox myCheckbox;
    TextInputLayout Pichet;
    TextInputLayout Distance;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forac05);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setTitle("Foraj");
        }
        actionBar.setDisplayHomeAsUpEnabled(true);

        //


        // Visibility of Distance and Pichet based on checkbox

        myCheckbox = findViewById(R.id.LocalizatCheckbox);
        Pichet = findViewById(R.id.PichetID);
        Distance = findViewById(R.id.DistanceID);


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
            Pichet.setVisibility(View.VISIBLE);
            Distance.setVisibility(View.VISIBLE);

        }
        else{
            Pichet.setVisibility(View.INVISIBLE);
            Distance.setVisibility(View.INVISIBLE);

        }
    }
    // next activity opener
    public void openNextActivity(){
        Intent intent = new Intent(ForacActivity05.this,OtherCommentsActivity06.class);
        startActivity(intent);
    }

}