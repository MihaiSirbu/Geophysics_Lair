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
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;


public class MainActivity01 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private EditText projectTextNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main01);
    // BAR TITLE
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Main Page");
        }



    // PROJECT NUMBER

        projectTextNumber = findViewById(R.id.ProjectTextNumber);




    // DROPDOWN MENU FOR OPERATORS
        spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.OperatorNames, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);










    // GO TO NEXT ACTIVITY
        Button Shot_Activity_Button = findViewById(R.id.addShotButton);
        Shot_Activity_Button.setOnClickListener(v -> openNextActivity());


    }

    public void openNextActivity(){
        Intent intent = new Intent(MainActivity01.this, AddShot02.class);


        // Variables to pass unto next activity
        String myoperator = spinner.getSelectedItem().toString();
        String projectNumber = projectTextNumber.getText().toString().trim();
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        // adding the vars to the intent
        intent.putExtra("operatorKEY",myoperator);
        intent.putExtra("projectKEY",projectNumber);
        intent.putExtra("timeStartKEY",currentDateTimeString);

        startActivity(intent);
    }


    //OPERATOR SELECT
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),text,Toast.LENGTH_LONG).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}