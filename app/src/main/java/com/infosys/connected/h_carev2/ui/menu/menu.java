package com.infosys.connected.h_carev2.ui.menu;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.infosys.connected.h_carev2.Appointments;
import com.infosys.connected.h_carev2.Glucose_monitor;
import com.infosys.connected.h_carev2.Health_Records;
import com.infosys.connected.h_carev2.R;
import com.infosys.connected.h_carev2.Subcriptions;
import com.infosys.connected.h_carev2.X_ray_Diagonis;
import com.infosys.connected.h_carev2.about_app;
import com.infosys.connected.h_carev2.cardiac_health_analysis;
import com.infosys.connected.h_carev2.profile;
import com.infosys.connected.h_carev2.ui.notifications.NotificationsViewModel;
import com.infosys.connected.h_carev2.videonotifications;

public class menu extends Fragment {

    private NotificationsViewModel menuViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        menuViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.menu_fragment, container, false);
        Button button =  root.findViewById(R.id.aboutbt);
        Button bht=root.findViewById(R.id.healthrecordbt);
        Button sbt=root.findViewById(R.id.subscriptionsbt);
        Button abt=root.findViewById(R.id.abt);
        Button gbt=root.findViewById(R.id.gbt);
        Button cbt=root.findViewById(R.id.cardiacbt);
        Button vbt=root.findViewById(R.id.vbt);
        Button pbt=root.findViewById(R.id.profilebt);
        Button xraybt=root.findViewById(R.id.xraybt);
        xraybt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(getContext(), X_ray_Diagonis.class);
                startActivity(i);
            }
        });
        pbt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(getContext(), profile.class);
                startActivity(i);
            }
        });
        vbt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(getContext(), videonotifications.class);
                i.putExtra("activity","called from menu");
                startActivity(i);
            }
        });
        cbt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(getContext(), cardiac_health_analysis.class);
                startActivity(i);
            }
        });
        gbt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(getContext(), Glucose_monitor.class);
                startActivity(i);
            }
        });
        abt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(getContext(), Appointments.class);
                startActivity(i);
            }
        });
        sbt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(getContext(), Subcriptions.class);
                startActivity(i);
            }
        });
        bht.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(getContext(), Health_Records.class);
                startActivity(i);
            }
        });
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(getContext(),about_app.class);
                startActivity(i);
            }
        });
        return root;
    }

}
