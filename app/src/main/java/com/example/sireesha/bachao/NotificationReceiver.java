package com.example.sireesha.bachao;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
/**
 * Created by Debkanya Mazumder <dmazum2@uic.edu> on 02-05-2017.
 */

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        String action = intent.getAction();
        if (AppConstant.YES_ACTION.equals(action)) {
            //String location = MainActivity.getDestination();
            String location = "West Taylor Street, Chicago, IL";
            Uri gmmIntentUri = Uri.parse("google.navigation:q="+location);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            context.startActivity(mapIntent);
            Toast.makeText(context, "YES CALLED", Toast.LENGTH_SHORT).show();

        }
    }


}