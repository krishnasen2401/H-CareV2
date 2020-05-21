package com.infosys.connected.h_carev2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.infosys.connected.h_carev2.background.notificationsservice;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class videonotifications extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videonotifications);
        TextView tv1=findViewById(R.id.videotitle1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        tv1.setText(tv1.getText()+"\n"+currentDateandTime);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customtoolbar);
        MediaController mediaController = new MediaController(this);
        VideoView simpleVideoView = findViewById(R.id.videoView3);
        mediaController.setAnchorView(simpleVideoView);
        simpleVideoView.setMediaController(mediaController);
        simpleVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sample1));

        Calendar cal = Calendar.getInstance();
// remove next line if you're always using the current time.
        cal.add(Calendar.MINUTE, -23);
        cal.add(Calendar.HOUR, -1);
        Date oneHourBack = cal.getTime();
        String currentDateandTime1 = sdf.format(oneHourBack);
        TextView tv2=findViewById(R.id.videotitle2);
        tv2.setText(tv2.getText()+"\n"+currentDateandTime1);
        MediaController mediaController1 = new MediaController(this);
        VideoView videoView2=findViewById(R.id.videoView);
        mediaController1.setAnchorView(videoView2);
        videoView2.setMediaController(mediaController1);
        videoView2.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sample2));
    }
}
