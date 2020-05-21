package com.infosys.connected.h_carev2.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.infosys.connected.h_carev2.Adapter.notificationsadapter;
import com.infosys.connected.h_carev2.DataFiles.notificationstable;
import com.infosys.connected.h_carev2.Database.sqlhelper;
import com.infosys.connected.h_carev2.R;

import java.util.LinkedList;
import java.util.List;

public class NotificationsFragment extends Fragment {
    notificationsadapter adapter;
    List<notificationstable> notificationsList;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        notificationsList=new LinkedList<>();
        mRecyclerView=root.findViewById(R.id.rvNotifications);
        DividerItemDecoration itemDecor = new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL);
        mRecyclerView.addItemDecoration(itemDecor);
        mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        loadNotificationsList();

        return root;
    }
    public void loadNotificationsList(){
        sqlhelper helper=new sqlhelper(getContext());
        notificationsList=helper.notificationT();
        adapter=new notificationsadapter(notificationsList,getContext(),mRecyclerView);
        mRecyclerView.setAdapter(adapter);
    }
}