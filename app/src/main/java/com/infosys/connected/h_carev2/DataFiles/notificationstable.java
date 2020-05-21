package com.infosys.connected.h_carev2.DataFiles;

public class notificationstable {
    String Title1;
    String text1;
    String timestamp;
    public notificationstable(){}
public notificationstable(String Title1, String text1, String timestamp){
this.Title1=Title1;
this.text1=text1;
this.timestamp=timestamp;
}

    public String getText1() {
        return text1;
    }

    public String getTitle1() {
        return Title1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public void setTitle1(String title1) {
        Title1 = title1;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;

    }
}
