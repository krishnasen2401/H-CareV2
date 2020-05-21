package com.infosys.connected.h_carev2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.infosys.connected.h_carev2.Database.sqlhelper;

public class login extends AppCompatActivity {
    EditText login,password;
    SharedPreferences sharedPref ;
    SharedPreferences.Editor editor;
    private static   String DEFAULT_CHANNEL_ID = "default_channel";
    private static   String DEFAULT_CHANNEL_NAME = "Default";
    private static NotificationManager mNotificationManager;

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

    public void loginauth(View v){
        String logins,passwords;
        logins=login.getText().toString();
        passwords=password.getText().toString();
        if(logins.equals("david2401") && passwords.equals("1234")){
            editor = sharedPref.edit();
            editor.putString("Loginid",logins);
            editor.commit();
            Intent resultIntent = new Intent(this, notifications.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addParentStack(videonotifications.class);
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent =stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            createNotificationChannel(mNotificationManager);
            Notification notification = new NotificationCompat.Builder(this,DEFAULT_CHANNEL_ID)
                    .setContentTitle("Welcome")
                    .setContentText("Thank you for using H-Care")
                    .setSmallIcon(R.drawable.ic_home_black_24dp)
                    .setAutoCancel(true)
                   .setContentIntent(resultPendingIntent)
                    .build();

            mNotificationManager.notify("H-Care",1, notification);
            intentlaunch();
            Toast.makeText(this,"Welcome "+logins,Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Invalid Details Entered",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=findViewById(R.id.loginid);
        password=findViewById(R.id.password);
        sharedPref= this.getSharedPreferences("logins", Context.MODE_PRIVATE);
        if(sharedPref.getString("Loginid","null").equals("null")){
            editor = sharedPref.edit();
            editor.putString("Loginid", "null");
            editor.commit();
            sqlhelper sqlhelper1=new sqlhelper(this);
            sqlhelper1.initialcreations();
        }else{
            intentlaunch();
        }
    }
    public void intentlaunch(){
        Intent i=new Intent(this,loading_screen.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}
