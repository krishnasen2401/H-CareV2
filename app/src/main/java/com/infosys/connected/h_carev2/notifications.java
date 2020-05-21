
package com.infosys.connected.h_carev2;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.infosys.connected.h_carev2.Adapter.notificationsadapter;
import com.infosys.connected.h_carev2.DataFiles.notificationstable;
import com.infosys.connected.h_carev2.Database.sqlhelper;

import java.util.LinkedList;
import java.util.List;

public class notifications extends AppCompatActivity {
    notificationsadapter adapter;
    List<notificationstable> notificationsList;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customtoolbar);
        notificationsList=new LinkedList<>();
        mRecyclerView=findViewById(R.id.rvNotifications);
        DividerItemDecoration itemDecor = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        mRecyclerView.addItemDecoration(itemDecor);
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        loadNotificationsList();
    }
    public void loadNotificationsList(){
        sqlhelper helper=new sqlhelper(this);
        notificationsList=helper.notificationT();
        adapter=new notificationsadapter(notificationsList,getApplicationContext(),mRecyclerView);
        mRecyclerView.setAdapter(adapter);
    }
}
