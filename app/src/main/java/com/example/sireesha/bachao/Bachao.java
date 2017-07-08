package com.example.sireesha.bachao;

import android.Manifest;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

/**
 * Created by Sireesha on 4/7/2017.
 */

/**
 *  Modified by Debkanya on 04/01/2017
 *  Fixed calling
 *  Added Widget
 *  Fixed messaging
 *  Tweaked Permissions
 */

public class Bachao extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1234;
    private static String SENT = "SMS_SENT";
    private static String DELIVERED = "SMS_DELIVERED";
    private static int MAX_SMS_MESSAGE_LENGTH = 160;
    private Button callBtn;
    Button sendBtn, sendBtn2, sendBtn3;
    String txtphoneNo;
    String txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bachao);

        sendBtn = (Button) findViewById(R.id.buttonFollowed);
        sendBtn2 = (Button) findViewById(R.id.buttonHouse);
        sendBtn3 = (Button) findViewById(R.id.buttonLoudNoises);
        callBtn = (Button) findViewById(R.id.alert_911);

        txtphoneNo = "4695717991";
        txtMessage = "I'm being followed right now. I urgently seek your assistance.";
        sendBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                sendSMSMessage();
            }
        });


        sendBtn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                SmsManager smsManager2 = SmsManager.getDefault();
                smsManager2.sendTextMessage(txtphoneNo, null, "Help! I think there is a stranger in my house. I need assistance.", null, null);
                Toast.makeText(getApplicationContext(), "SMS sent!",
                        Toast.LENGTH_LONG).show();

            }
        });

        sendBtn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                SmsManager smsManager3 = SmsManager.getDefault();
                smsManager3.sendTextMessage(txtphoneNo, null, "Help! I just heard a gun shot near by. I need assistance.", null, null);
                Toast.makeText(getApplicationContext(), "SMS sent!",
                        Toast.LENGTH_LONG).show();

            }
        });

        Button sendBtn4 = (Button) findViewById(R.id.alert_911);
        sendBtn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("Call", "Inside setOnClickListener");
                try {

                    Log.d("Call", "tries calling");

                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:4695717991"));
                    if (ActivityCompat.checkSelfPermission(Bachao.this,
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Log.d("Call", "not granted so doesn't call");
                        }
                    Log.d("Call", "makes the call");
                    startActivity(callIntent);
                    Toast.makeText(getApplicationContext(), "Call successful!", Toast.LENGTH_LONG).show();
                }
                catch (Exception ex)
                {
                    Log.d("Call", "comes to exception");
                    Toast.makeText(getApplicationContext(), "Error in your phone call"+ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });


        Button sendBtn5 = (Button) findViewById(R.id.button4);
        sendBtn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("Call", "Inside setOnClickListener");
                try {

                    Log.d("Call", "tries calling");

                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:4695717991"));
                    if (ActivityCompat.checkSelfPermission(Bachao.this,
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Log.d("Call", "not granted so doesn't call");
                    }
                    Log.d("Call", "makes the call");
                    startActivity(callIntent);
                    Toast.makeText(getApplicationContext(), "Call successful!", Toast.LENGTH_LONG).show();
                }
                catch (Exception ex)
                {
                    Log.d("Call", "comes to exception");
                    Toast.makeText(getApplicationContext(), "Error in your phone call"+ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });


    }



    protected void sendSMSMessage() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(txtphoneNo, null, txtMessage, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent!",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }


    Button btnsiren = (Button) findViewById(R.id.Siren);
        btnsiren.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                MediaPlayer siren=MediaPlayer.create(Bachao.this, R.raw.siren );
                siren.start();
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                siren.stop();
            }
        });
    }

    // ---sends an SMS message to another device---
    public void sendSMS(String phoneNumber, String message) {

        Context mContext = this;
        PendingIntent piSent = PendingIntent.getBroadcast(mContext, 0, new Intent(SENT), 0);
        PendingIntent piDelivered = PendingIntent.getBroadcast(mContext, 0,new Intent(DELIVERED), 0);
        SmsManager smsManager = SmsManager.getDefault();

        int length = message.length();
        if(length > MAX_SMS_MESSAGE_LENGTH) {
            ArrayList<String> messagelist = smsManager.divideMessage(message);
            smsManager.sendMultipartTextMessage(phoneNumber, null, messagelist, null, null);
        }
        else
            smsManager.sendTextMessage(phoneNumber, null, message, piSent, piDelivered);
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
}