package com.infosys.connected.h_carev2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.infosys.connected.h_carev2.DataFiles.notificationstable;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class sqlhelper extends SQLiteOpenHelper {
    Context context;
    public static final String DATABASE_NAME = "H-care";
    public static final String TABLE_Name_Notification = "Notifications";
    public static final String Notification_COLUMN_title="title";
    public static final String Notification_COLUMN_text="message";
    public static final String Notification_COLUMN_Time_Stamp="time";
    public static final String TABLE_Name_Hospitals="Hospitals";
    public static final String Hopistals_COLUMN_LOCATION="Location";
    public sqlhelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
        this.context=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE IF NOT EXISTS " + TABLE_Name_Notification + " ("+Notification_COLUMN_title+" Varchar(45),"+Notification_COLUMN_text+" Varchar(45),"+Notification_COLUMN_Time_Stamp+" Varchar(45));");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_Name_Hospitals + " (Location varchar(30),Hospital_Name Varchar(30),Speciality Varchar(30),Doctor Varchar(30));");
        db.execSQL("INSERT INTO "+TABLE_Name_Hospitals+" VALUES('West Palm Beach','JFK Medical Center North Campus','Pulmology','Max Gonzales');");
        db.execSQL("INSERT INTO "+TABLE_Name_Hospitals+" VALUES('West Palm Beach','JFK Medical Center North Campus','Cardiology','Hattie Marsh');");
        db.execSQL("INSERT INTO "+TABLE_Name_Hospitals+" VALUES('West Palm Beach','JFK Medical Center North Campus','Endocrinology','Renee Snyder');");
        db.execSQL("INSERT INTO "+TABLE_Name_Hospitals+" VALUES('West Palm Beach','Good Samaritan Medical Center','Pulmology','Sophie Mitchell');");
        db.execSQL("INSERT INTO "+TABLE_Name_Hospitals+" VALUES('West Palm Beach','Good Samaritan Medical Center','Cardiology','Randy Swanson');");
        db.execSQL("INSERT INTO "+TABLE_Name_Hospitals+" VALUES('West Palm Beach','Good Samaritan Medical Center','Endocrinology','Ray Underwood');");
        db.execSQL("INSERT INTO "+TABLE_Name_Hospitals+" VALUES('West Palm Beach','St. Mary Medical Center','Pulmology','Julia Townsend');");
        db.execSQL("INSERT INTO "+TABLE_Name_Hospitals+" VALUES('West Palm Beach','St. Mary Medical Center','Cardiology','Ida Perry');");
        db.execSQL("INSERT INTO "+TABLE_Name_Hospitals+" VALUES('West Palm Beach','St. Mary Medical Center','Endocrinology','Lucille Porter');");
        db.execSQL("INSERT INTO "+TABLE_Name_Hospitals+" VALUES('St. Petersburg','Bayfront Health St. Petersburg','Pulmology','Wm Bush');");
        db.execSQL("INSERT INTO "+TABLE_Name_Hospitals+" VALUES('St. Petersburg','Bayfront Health St. Petersburg','Cardiology','Luz Ryan');");
        db.execSQL("INSERT INTO "+TABLE_Name_Hospitals+" VALUES('St. Petersburg','Bayfront Health St. Petersburg','Endocrinology','Ollie Taylor');");
        db.execSQL("INSERT INTO "+TABLE_Name_Hospitals+" VALUES('St. Petersburg','Edward White Hospital','Pulmology','Henry Stevens');");
        db.execSQL("INSERT INTO "+TABLE_Name_Hospitals+" VALUES('St. Petersburg','Edward White Hospital','Cardiology','Janice Aguilar');");
        db.execSQL("INSERT INTO "+TABLE_Name_Hospitals+" VALUES('St. Petersburg','Edward White Hospital','Endocrinology','Mable Woods');");
        db.execSQL("INSERT INTO "+TABLE_Name_Hospitals+" VALUES('St. Petersburg','Northside Hospital','Pulmology','Lana Bass');");
        db.execSQL("INSERT INTO "+TABLE_Name_Hospitals+" VALUES('St. Petersburg','Northside Hospital','Cardiology','Stuart Rivera');");
        db.execSQL("INSERT INTO "+TABLE_Name_Hospitals+" VALUES('St. Petersburg','Northside Hospital','Endocrinology','Leona Bryant');");
        db.execSQL("INSERT INTO "+TABLE_Name_Hospitals+" VALUES('Orlando','Dr. P. Phillips Hospital','Pulmology','Eric Maxwell');");
        db.execSQL("INSERT INTO "+TABLE_Name_Hospitals+" VALUES('Orlando','Dr. P. Phillips Hospital','Cardiology','Amos Greene');");
        db.execSQL("INSERT INTO "+TABLE_Name_Hospitals+" VALUES('Orlando','Dr. P. Phillips Hospital','Endocrinology','Samuel Ruiz');");
        db.execSQL("INSERT INTO "+TABLE_Name_Hospitals+" VALUES('Orlando','Florida Hospital East Orlando','Pulmology','Josefina Thomas');");
        db.execSQL("INSERT INTO "+TABLE_Name_Hospitals+" VALUES('Orlando','Florida Hospital East Orlando','Cardiology','Ricky Wade');");
        db.execSQL("INSERT INTO "+TABLE_Name_Hospitals+" VALUES('Orlando','Florida Hospital East Orlando','Endocrinology','Donnie Phelps');");
        db.execSQL("INSERT INTO "+TABLE_Name_Hospitals+" VALUES('Orlando','Orlando Regional Medical Center','Pulmology','Jonathon Cunningham');");
        db.execSQL("INSERT INTO "+TABLE_Name_Hospitals+" VALUES('Orlando','Orlando Regional Medical Center','Cardiology','Vivian Garner');");
        db.execSQL("INSERT INTO "+TABLE_Name_Hospitals+" VALUES('Orlando','Orlando Regional Medical Center','Endocrinology','Dianne Horton');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_Name_Hospitals);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_Name_Notification);
        this.onCreate(db);
    }

    public void notificationseeder(String Title,String Timestamp,String text){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cn=new ContentValues();
        cn.put(Notification_COLUMN_title,Title);
        cn.put(Notification_COLUMN_Time_Stamp,Timestamp);
        cn.put(Notification_COLUMN_text,text);
        db.insert(TABLE_Name_Notification,null, cn);
        cn.clear();
        db.close();
    }


    public List<notificationstable> notificationT() {
        List<notificationstable> notificationTLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM Notifications", null);
        notificationstable notificationstable1;

        if (cursor.moveToFirst()) {
            do {
                notificationstable1 = new notificationstable();
                notificationstable1.setTitle1(cursor.getString(cursor.getColumnIndex(Notification_COLUMN_title)));
                notificationstable1.setTimestamp(cursor.getString(cursor.getColumnIndex(Notification_COLUMN_Time_Stamp)));
                notificationstable1.setText1(cursor.getString(cursor.getColumnIndex(Notification_COLUMN_text)));
                notificationTLinkedList.add(notificationstable1);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notificationTLinkedList;
    }
public void initialcreations(){
    SQLiteDatabase db=getWritableDatabase();
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_Name_Notification);
    db.execSQL("DROP TABLE IF EXISTS "+TABLE_Name_Hospitals);
    this.onCreate(db);
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
    Calendar cal = Calendar.getInstance();
    Date oneHourBack = cal.getTime();
    String currentDateandTime1 = sdf.format(oneHourBack);
    ContentValues cn=new ContentValues();
    cn.put(Notification_COLUMN_title,"Welcome");
    cn.put(Notification_COLUMN_Time_Stamp,currentDateandTime1);
    cn.put(Notification_COLUMN_text,"Thank you for using H-Care");
    db.insert(TABLE_Name_Notification,null, cn);
    cn.clear();
    db.close();
}

    public List<String> citylist() {
        List<String> cityTLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT Distinct location FROM "+TABLE_Name_Hospitals, null);
        String city=null;
        if (cursor.moveToFirst()) {
            do {
                city=cursor.getString(cursor.getColumnIndex("Location"));
                cityTLinkedList.add(city);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return cityTLinkedList;
    }
    public List<String> hospitallist(String location) {
        List<String> hospitalTLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT Distinct Hospital_Name FROM "+TABLE_Name_Hospitals +" where location ='"+location+"';", null);
        String hospital=null;
        if (cursor.moveToFirst()) {
            do {
                hospital=cursor.getString(cursor.getColumnIndex("Hospital_Name"));
                hospitalTLinkedList.add(hospital);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return hospitalTLinkedList;
    }

    public List<String> Specialitylist(String location,String hospital) {
        List<String> SpecialityTLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT Distinct Speciality FROM "+TABLE_Name_Hospitals +" where location ='"+location+"' and Hospital_Name='"+hospital+"';", null);
        String Speciality=null;
        if (cursor.moveToFirst()) {
            do {
                Speciality=cursor.getString(cursor.getColumnIndex("Speciality"));
                SpecialityTLinkedList.add(Speciality);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return SpecialityTLinkedList;
    }

    public List<String> doctorlist(String location,String hospital,String speciality) {
        List<String> doctorTLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT Distinct Doctor FROM "+TABLE_Name_Hospitals +" where location ='"+location+"' and Hospital_Name='"+hospital+"' and Speciality='"+speciality+"';", null);
        String doctor=null;
        if (cursor.moveToFirst()) {
            do {
                doctor=cursor.getString(cursor.getColumnIndex("Doctor"));
                doctorTLinkedList.add(doctor);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return doctorTLinkedList;
    }
}
