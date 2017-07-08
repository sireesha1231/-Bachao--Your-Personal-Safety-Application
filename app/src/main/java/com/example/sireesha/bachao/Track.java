package com.example.sireesha.bachao;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.BreakIterator;
import java.util.List;
import java.util.Locale;

import static com.example.sireesha.bachao.R.id.progressBar;

/**
 * Created by Debkanya Mazumder on 4/7/2017.
 */

public class Track extends AppCompatActivity {

    int minteger = 0;
    int time = 0;
    String address;
    private TrackGPS gps;
    double longitude;
    double latitude;
    static final String API_KEY = "USE_YOUR_OWN_API_KEY";
    static final String API_URL = "http://api1.chicagopolice.org/clearpath/api/1.0/crimes/nearbyXY?";
    static String location;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        Button btnConfirm = (Button) findViewById(R.id.button8);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                time = time == 0 ? 10000 : time *  3600000; //hours to milliseconds
                int gap = 5000; //5 seconds update
                new CountDownTimer(time, gap) {

                    public void onTick(long millisUntilFinished) {
                        executeTrack();
                    }

                    public void onFinish() {

                        NotificationCompat.Builder builder =
                                new NotificationCompat.Builder(Track.this)
                                        .setSmallIcon(R.drawable.abc)
                                        .setContentTitle("Bachao:")
                                        .setContentText("Tracking has been turned off.");

                        Intent notificationIntent = new Intent(Track.this, Track.class);
                        PendingIntent contentIntent = PendingIntent.getActivity(Track.this, 0, notificationIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT);
                        builder.setContentIntent(contentIntent);

                        // Add as notification
                        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        manager.notify(1, builder.build());
                    }

                }.start();

            }
        });
    }


    public void executeTrack() {
        new RetrieveFeedTask().execute();
        gps = new TrackGPS(getApplication());

        //location = locationText.toString().isEmpty() ? "Taylor Street" : locationText.toString();

        if(gps.canGetLocation()){
            longitude = gps.getLongitude();
            latitude = gps .getLatitude();
            Toast.makeText(getApplicationContext(),"Longitude:"+Double.toString(longitude)+"\nLatitude:"+Double.toString(latitude),Toast.LENGTH_SHORT).show();
            try {
                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 2);
                address  = addresses.isEmpty() == true ? "005XX N DEARBORN ST" : addresses.get(0).toString();
                location = address;
                Toast.makeText(getApplicationContext(), "Address:\n" + location , Toast.LENGTH_SHORT).show();

            }catch(Exception ex)
            {

                Log.d("GET-LOCATION", "Exception occured while getting location");
            }

            //display in long period of time
            TextView tv = (TextView)findViewById(R.id.txt_hours);
            String hrs = tv.getText().toString();
            time = Integer.parseInt(hrs);
            String msg = "Confirmed! You are being tracked for the next " + hrs + " hours";
            Toast.makeText(getApplicationContext(), msg,
                    Toast.LENGTH_LONG).show();
        }
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

    public void increaseInteger(View view) {
        minteger = minteger + 1;
        display(minteger);

    }public void decreaseInteger(View view) {
        minteger = minteger - 1;
        display(minteger);
    }

    private void display(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.txt_hours);
        displayInteger.setText("" + number);
    }
    public static String getDestination() {

        //location = locationText.toString() == null ? "Taylor Street, Chicago, IL" : locationText.toString();
        return location;

    }
    protected  void generateNotification() {
        Log.d("NOTIFICATION", "generate notification here");
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.drawable.abc);
        mBuilder.setContentTitle("Notification: You have entered red zone!");
        mBuilder.setContentText("You have entered a red zone.");
        Intent notificationIntent = new Intent(this, Track.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Intent yesReceive = new Intent();
        yesReceive.setAction(AppConstant.YES_ACTION);
        PendingIntent pendingIntentYes = PendingIntent.getBroadcast(this, 12345, yesReceive, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.addAction(R.drawable.back_dialog, "Alternate Safer Route", pendingIntentYes);


        Intent yesReceive2 = new Intent();
        yesReceive2.setAction(AppConstant.STOP_ACTION);
        PendingIntent pendingIntentYes2 = PendingIntent.getBroadcast(this, 12345, yesReceive2, PendingIntent.FLAG_UPDATE_CURRENT);
        //mBuilder.addAction(R.drawable.back_dialog, "Cancel", pendingIntentYes2);
        mBuilder.setContentIntent(contentIntent);
        mBuilder.setSound(Uri.parse("android.resource://"
                + getApplicationContext().getPackageName() + "/" + R.raw.notificationtune));

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, mBuilder.build());
    }

    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            BreakIterator responseView;
            //responseView.setText("");
        }

        protected String doInBackground(Void... urls)  {
            String urlstr = "http://api1.chicagopolice.org/clearpath/api/1.0/crimes/major?block=" + address;
            // String location = addressText.getText().toString();
            // Do some validation here

            try {
                URL url = new URL(urlstr);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if(response == null) {

                Log.d("RESPONSE", "Response is null");
                Toast.makeText(getApplicationContext(), "Unable to get response", Toast.LENGTH_SHORT).show();
            }

            if(!response.isEmpty()) {
                Log.d("RESPONSE", "response is not null");


                //check notification settings here
                //add if statement
                // Settings s = new Settings();
                // if(s.checkNotificationSettings())

                generateNotification();
                progressBar.setVisibility(View.GONE);

                //else
                // make toast "notification is disallowed by the user"
                Log.i("INFO", response);
            }
            // TODO: check this.exception
            // TODO: do something with the feed
        }
    }
}
