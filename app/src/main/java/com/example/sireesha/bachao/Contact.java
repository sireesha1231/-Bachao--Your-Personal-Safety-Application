package com.example.sireesha.bachao;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * Created by Sireesha on 4/7/2017.
 */

public class Contact extends AppCompatActivity {

    private static int PICK_CONTACT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

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

    public void goSettings(View v)
    {
        Intent myIntent = new Intent(this, Settings.class);
        startActivity(myIntent);
    }

    public void addContact(View v) {

        Intent it= new Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI);

        startActivityForResult(it, PICK_CONTACT);

    }

}
