package com.infosys.connected.h_carev2;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.infosys.connected.h_carev2.extras_libs.Screenshot;
import com.infosys.connected.h_carev2.extras_libs.storeScreenshot;


public class Glucose_monitor extends AppCompatActivity {
    ImageView screenshot;
    public void take_screenshot(View v) {
        Bitmap b = Screenshot.takescreenshotOfRootView(screenshot);
        screenshot.setImageBitmap(b);
        v.setBackgroundColor(Color.parseColor("#999999"));
        storeScreenshot.shareScreen(this,b, "Glucose");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glucose_monitor);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customtoolbar);
        screenshot = findViewById(R.id.glucose_screenshot);
        screenshot.setVisibility(View.GONE);
    }


}
