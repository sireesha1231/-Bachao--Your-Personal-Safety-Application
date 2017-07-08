package com.example.sireesha.bachao;

import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class Settings extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
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

    public void goSettings(View v){
        Intent myIntent = new Intent(this, Settings.class);
        startActivity(myIntent);
    }

    public void goTutorial1(View v)
    {
        Intent myIntent = new Intent(this, Tutorial1.class);
        startActivity(myIntent);
    }

    public void goWelcome(View v)
    {
        Intent myIntent = new Intent(this, Welcome.class);
        startActivity(myIntent);
    }
}
