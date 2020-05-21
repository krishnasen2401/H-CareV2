package com.infosys.connected.h_carev2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.infosys.connected.h_carev2.background.notificationsservice;
import com.infosys.connected.h_carev2.ui.home.HomeFragment;
import com.infosys.connected.h_carev2.ui.notifications.NotificationsFragment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPref ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customtoolbar);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_gluecose, R.id.navigation_notifications)
                .build();
       NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        final Intent i=new Intent(this, notificationsservice.class);
        sharedPref= this.getSharedPreferences("logins", Context.MODE_PRIVATE);
        if(sharedPref.getString("notification",null).equals("not called")){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startService(i);
            }
        },5000);}}
    public void switchToFragment1() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.nav_host_fragment, new NotificationsFragment()).commit();
    }
}
