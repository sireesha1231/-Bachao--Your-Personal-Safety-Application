package com.example.sireesha.bachao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Tutorial2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial2);
    }

    public void goTutorial3(View v)
    {
        Intent myIntent = new Intent(this, Tutorial3.class);
        startActivity(myIntent);
    }
}
