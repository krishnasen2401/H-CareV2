package com.infosys.connected.h_carev2.DataFiles;

public class doctors {
    String location,hospital,name1,Speciality,Doctor;
public doctors(){}
public doctors(String location,String hospital,String name1,String Speciality,String Doctor){
    this.location=location;
    this.hospital=hospital;
    this.name1=name1;
    this.Speciality=Speciality;
    this.Doctor=Doctor;
}

    public String getDoctor() {
        return Doctor;
    }

    public String getHospital() {
        return hospital;
    }

    public String getLocation() {
        return location;
    }

    public String getName1() {
        return name1;
    }

    public String getSpeciality() {
        return Speciality;
    }

    public void setDoctor(String doctor) {
        Doctor = doctor;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public void setSpeciality(String speciality) {
        Speciality = speciality;
    }
}
