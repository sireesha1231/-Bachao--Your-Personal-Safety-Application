package com.example.sireesha.bachao;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Register extends AppCompatActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

    }

    public void goQuestion(View v)
    {
        Intent myIntent = new Intent(this, Question.class);
        startActivity(myIntent);
    }

    public void goWelcome(View v)
    {
        Intent myIntent = new Intent(this, Welcome.class);
        startActivity(myIntent);
    }


}

