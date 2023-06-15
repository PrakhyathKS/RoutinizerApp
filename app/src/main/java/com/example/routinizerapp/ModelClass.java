package com.example.routinizerapp;

import android.widget.TextView;

public class ModelClass {
    private String Reminder,remicounter;

    ModelClass(String Reminder,String remicoutner){
        this.Reminder=Reminder;
        this.remicounter=remicoutner;


    }

    public void setReminder(String reminder) {
        Reminder = reminder;
    }

    public void setRemicounter(String remicounter) {
        this.remicounter = remicounter;
    }

    public String getReminder() {
        return Reminder;
    }

    public String getRemicounter() {
        return remicounter;
    }
}
