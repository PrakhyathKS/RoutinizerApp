package com.example.routinizerapp;

import android.widget.TextView;

public class ModelClass {


    private String radioGroup;
    private String layouttitle;
    private String layoutdesc;

    public String getLayoutmode() {
        return layoutmode;
    }

    public void setLayoutmode(String layoutmode) {
        this.layoutmode = layoutmode;
    }

    private String layoutmode;
    private String countertext1;
    private String timertext;

    public ModelClass() {
    }

    public ModelClass(String layoutmode,String radioGroup, String layouttitle, String layoutdesc, String countertext1, String timertext) {
        this.radioGroup = radioGroup;
        this.layouttitle = layouttitle;
        this.layoutdesc = layoutdesc;
        this.countertext1 = countertext1;
        this.timertext = timertext;
        this.layoutmode=layoutmode;
    }


    public String getCountertext1() {
        return countertext1;
    }

    public void setCountertext1(String countertext1) {
        this.countertext1 = countertext1;
    }

    public String getRadioGroup() {
        return radioGroup;
    }

    public void setRadioGroup(String radioGroup) {
        this.radioGroup = radioGroup;
    }

    public String getLayouttitle() {
        return layouttitle;
    }

    public void setLayouttitle(String layouttitle) {
        this.layouttitle = layouttitle;
    }

    public String getLayoutdesc() {
        return layoutdesc;
    }

    public void setLayoutdesc(String layoutdesc) {
        this.layoutdesc = layoutdesc;
    }

    public String getTimertext() {
        return timertext;
    }

    public void setTimertext(String timertext) {
        this.timertext = timertext;
    }




}
