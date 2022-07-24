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

import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;


public class MainActivity01 extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener{

    private Spinner spinner;
    private EditText projectTextNumber;

    private EditText PasswordEditTxt;
    private Button passwordCheck;
    private Button ExportDbButton;
    private Button Shot_Activity_Button;
    private TextInputLayout layoutinput;

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
        spinner = findViewById(R.id.spinner1);
        PasswordEditTxt = findViewById(R.id.PasswordInputText);
        passwordCheck = findViewById(R.id.ValidatePasswordButton);
        ExportDbButton = findViewById(R.id.ExtractDataToCsvButton);
        Shot_Activity_Button = findViewById(R.id.newProjectButton);
        layoutinput = findViewById(R.id.textInputLayout);




    // DROPDOWN MENU FOR OPERATORS
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.OperatorNames, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);



    // Password Check
        passwordCheck.setOnClickListener(this);

        ExportDbButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                DatabaseHelper helper = new DatabaseHelper(MainActivity01.this);
                try {
                    SqliteExporter.export(helper.getReadableDatabase());
                    Toast.makeText(MainActivity01.this,"Works to export!",Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity01.this,"Failed to Export db",Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });







    // GO TO NEXT ACTIVITY
        Shot_Activity_Button.setOnClickListener(this);


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
        if(text.equals("Mishu")){
            // if mishu is working, then we can export db
            PasswordEditTxt.setVisibility(View.VISIBLE);
            passwordCheck.setVisibility(View.VISIBLE);
            layoutinput.setVisibility(View.VISIBLE);

        }
        else{
            // otherwise hide the buttons.
            PasswordEditTxt.setVisibility(View.INVISIBLE);
            passwordCheck.setVisibility(View.INVISIBLE);
            ExportDbButton.setVisibility(View.INVISIBLE);
            layoutinput.setVisibility(View.INVISIBLE);

        }
        Toast.makeText(adapterView.getContext(),text,Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.ValidatePasswordButton:
                if(PasswordEditTxt.getText().toString().equals(getString(R.string.password))){
                    ExportDbButton.setVisibility(View.VISIBLE);

                }
                else{
                    Toast.makeText(MainActivity01.this,"incorrect password",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.newProjectButton:
                openNextActivity();
                break;
        }
    }
}