package com.infosys.connected.h_carev2.background;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.infosys.connected.h_carev2.R;
import com.infosys.connected.h_carev2.notifications;
import com.infosys.connected.h_carev2.videonotifications;


public class notificationsservice extends IntentService {
    Context context;
    private static String DEFAULT_CHANNEL_ID = "default_channel";
    private static String DEFAULT_CHANNEL_NAME = "Default";
    private static String groupkey="H-care";
    private NotificationManager mNotificationManager;
    public static void createNotificationChannel(NotificationManager notificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Create channel only if it is not already created
            if (notificationManager.getNotificationChannel(DEFAULT_CHANNEL_ID) == null) {
                notificationManager.createNotificationChannel(new NotificationChannel(
                        DEFAULT_CHANNEL_ID, DEFAULT_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
                ));
            }
        }
    }
    @Override
    public void onCreate() {
        super.onCreate(); // if you override onCreate(), make sure to call super().

        // If a Context object is needed, call getApplicationContext() here.
    }
    public notificationsservice() {
        super("h-care-notifcations");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("backgroundService","Service running");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("backgroundService1","Service running");
        Toast.makeText(this, "Service started by user.", Toast.LENGTH_LONG).show();
        notictiontimed();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Toast.makeText(getApplicationContext(), "Service destroyed by user.", Toast.LENGTH_LONG).show();
        Log.d("backgroundservice1","stopped");
    }

    public void notificationrealservice(String Title,String Message){
        Intent resultIntent = new Intent(this, notifications.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(videonotifications.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        createNotificationChannel(mNotificationManager);
        Notification notification = new NotificationCompat.Builder(this,DEFAULT_CHANNEL_ID)
                .setContentTitle(Title)
                .setContentText(Message)
                .setSmallIcon(R.drawable.ic_home_black_24dp)
                .setAutoCancel(true)
          .setContentIntent(resultPendingIntent)
                .build();
        mNotificationManager.notify("H-care",4, notification);

    }

    public void notictiontimed(){
        SharedPreferences sharedPref ;
        SharedPreferences.Editor editor;
        sharedPref= this.getSharedPreferences("logins", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        editor.putString("notification","called");
        editor.commit();
        Intent resultIntent = new Intent(this, videonotifications.class);
        //on touch of the notification the intents need to be opened
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(videonotifications.class);//adding parent stack so that home screen is opened as soon as the notifcation intent is closed
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        createNotificationChannel(mNotificationManager);
        Notification notification = new NotificationCompat.Builder(this,DEFAULT_CHANNEL_ID)
                .setContentTitle("Fall Detected")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("A Fall Has Been Detected By Smart Video Surveillance At Jackson Memorial Hospital Ward 1"))//Set the title of Notification
                .setSmallIcon(R.drawable.ic_home_black_24dp)
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent)
                .build();
        mNotificationManager.notify("H-care",4, notification);

        //final Intent i=new Intent(this,notificationsservice.class);
        //stopService(i);
    }
}
