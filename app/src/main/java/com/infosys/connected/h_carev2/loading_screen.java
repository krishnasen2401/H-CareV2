package com.infosys.connected.h_carev2;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Timer;
import java.util.TimerTask;

public class loading_screen extends AppCompatActivity {
    private static final int permissioncode = 101;
    SharedPreferences sharedPref ;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                //permission is not granted ,request it
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, permissioncode);
            } else {
                //permission already granted

            }
        } else {
            //system is less than marshmello
         }
        ImageView imageView =  findViewById(R.id.imageView101);
        Glide.with(this)
                .load(R.drawable.loading)
                .into(imageView);
        TextView tv=findViewById(R.id.userwelcome);
        sharedPref= this.getSharedPreferences("logins", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        editor.putString("notification","not called");
        editor.commit();

        tv.setText("Welcome "+sharedPref.getString("Loginid",""));

        final Intent i=new Intent(this,MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(i);
            }
        },5000);
    }
}
