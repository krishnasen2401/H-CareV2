package com.infosys.connected.h_carev2.ui.appointments;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.infosys.connected.h_carev2.Database.sqlhelper;
import com.infosys.connected.h_carev2.R;
import com.infosys.connected.h_carev2.notifications;
import com.infosys.connected.h_carev2.videonotifications;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.content.Context.NOTIFICATION_SERVICE;

public class AppointmentsFragment extends Fragment {
    Spinner citysp,hospitalsp,specialitysp,doctorsp;
    String selectedhospital,selectedspeciality,selectdoctor;
    List<String> citieslist,hospitallist,specialitylist,doctorlist;
    ArrayAdapter<String> spinnerArrayAdapter,spinnerArrayAdapter1,spinnerArrayAdapter2,spinnerArrayAdapter3;
    private AppointmentsViewModel appointmentsViewModel;
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
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
            appointmentsViewModel =ViewModelProviders.of(this).get(AppointmentsViewModel.class);
            View root = inflater.inflate(R.layout.fragment_appointments, container, false);
        citysp=root.findViewById(R.id.citysp);
        hospitalsp=root.findViewById(R.id.hospitalsp);
        specialitysp=root.findViewById(R.id.specialitysp);
        doctorsp=root.findViewById(R.id.doctorsp);
        final sqlhelper sp=new sqlhelper(getContext());
        citieslist=  sp.citylist();
        spinnerArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, citieslist);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citysp.setAdapter(spinnerArrayAdapter);

        citysp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                final String selectedlocation = parentView.getItemAtPosition(position).toString();
                Log.d("selected",selectedlocation);
                hospitallist=sp.hospitallist(selectedlocation);
                spinnerArrayAdapter1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, hospitallist);
                spinnerArrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                hospitalsp.setAdapter(spinnerArrayAdapter1);
                hospitalsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        selectedhospital = parentView.getItemAtPosition(position).toString();
                        specialitylist=sp.Specialitylist(selectedlocation,selectedhospital);
                        spinnerArrayAdapter2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,specialitylist);
                        spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        specialitysp.setAdapter(spinnerArrayAdapter2);
                        specialitysp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                        {
                            @Override
                            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                selectedspeciality = parentView.getItemAtPosition(position).toString();
                                doctorlist=sp.doctorlist(selectedlocation,selectedhospital,selectedspeciality);
                                spinnerArrayAdapter3 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,doctorlist);
                                spinnerArrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                doctorsp.setAdapter(spinnerArrayAdapter3);
                                doctorsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                                {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                        selectdoctor=parentView.getItemAtPosition(position).toString();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parentView) {
                                        // your code here
                                    }
                                });
                                    }
                            @Override
                            public void onNothingSelected(AdapterView<?> parentView) {
                                // your code here
                            }
                        });
                            }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // your code here
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });


        Button appointsub=root.findViewById(R.id.appointmentbook);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        Calendar cal = Calendar.getInstance();
        Date oneHourBack = cal.getTime();
        final String currentDateandTime1 = sdf.format(oneHourBack);
        appointsub.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent resultIntent = new Intent(getContext(), notifications.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(getContext());
                stackBuilder.addParentStack(videonotifications.class);
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

                mNotificationManager = (NotificationManager)getActivity()  .getSystemService(NOTIFICATION_SERVICE);
                createNotificationChannel(mNotificationManager);
                Notification notification = new NotificationCompat.Builder(getContext(),DEFAULT_CHANNEL_ID)
                        .setContentTitle("Appointment Request")
                        .setContentText("The Appointment Request for "+selectedspeciality+" with "+selectdoctor+" has been sent to "+selectedhospital)
                        .setSmallIcon(R.drawable.ic_home_black_24dp)
                        .setAutoCancel(true)
                        .setContentIntent(resultPendingIntent)
                        .build();
                mNotificationManager.notify("H-care",1, notification);
                sp.notificationseeder("Appointment Request",currentDateandTime1,"The Appointment Request for "+selectedspeciality+" with "+selectdoctor+" has been sent to "+selectedhospital);
            }
        });
        return root;
    }


}