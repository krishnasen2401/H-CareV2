package com.infosys.connected.h_carev2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class profile extends AppCompatActivity {
    SharedPreferences sharedPref ;
    SharedPreferences.Editor editor;
    public void signout(View v){
        editor = sharedPref.edit();
        editor.putString("Loginid", "null");
        editor.commit();
        Intent i=new Intent(this,login.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customtoolbar);
        TextView tv=findViewById(R.id.tvloginid);
        sharedPref= this.getSharedPreferences("logins", Context.MODE_PRIVATE);
        tv.setText(sharedPref.getString("Loginid",""));
    }
}
