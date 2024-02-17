package com.example.week4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Table extends AppCompatActivity {
    EditText firstNameInput;
    EditText lastNameInput;
    EditText stateInput;

    EditText phoneInput;
    EditText genderInput;

    EditText emailInput;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        firstNameInput = (EditText)  findViewById(R.id.first_name);
        lastNameInput = (EditText)  findViewById(R.id.last_name);
        emailInput = (EditText) findViewById(R.id.email);
        stateInput = (EditText)   findViewById(R.id.state);
        phoneInput =  (EditText) findViewById(R.id.phone);
        genderInput = (EditText) findViewById(R.id.gender);

     }


    @Override
    public void onBackPressed() {

        String firstName = firstNameInput.getText().toString();
        String lastName = lastNameInput.getText().toString();
        String email = emailInput.getText().toString();
        String state = stateInput.getText().toString();
        String phone = phoneInput.getText().toString();
        String gender = genderInput.getText().toString();

        Person person = new Person(firstName,lastName,email,state,phone,gender);

        Intent resultIntent = new Intent();
        ArrayList<Person> personData = new ArrayList<>();
        personData.add(person);
        resultIntent.putParcelableArrayListExtra("personData",personData);
        setResult(RESULT_OK,resultIntent);
        super.onBackPressed();
    }

}