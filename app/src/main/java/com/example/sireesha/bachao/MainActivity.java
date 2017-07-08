package com.example.sireesha.bachao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button backto_main = (Button) findViewById(R.id.btnSpotCrime);
        backto_main.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Starting Spotcrime", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), SpotCrime.class);
                startActivity(intent);
            }
        });
    }

    public void goHome(View v)
    {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }

    public void goContact(View v)
    {
        Intent myIntent = new Intent(this, Contact.class);
        startActivity(myIntent);
    }

    public void goTrack(View v)
    {
        Intent myIntent = new Intent(this, Track.class);
        startActivity(myIntent);
    }

    public void goBachao(View v)
    {
        Intent myIntent = new Intent(this, Bachao.class);
        startActivity(myIntent);
    }

    public void goSettings(View v)
    {
        Intent myIntent = new Intent(this, Settings.class);
        startActivity(myIntent);
    }

}