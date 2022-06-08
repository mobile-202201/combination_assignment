package com.example.json1;

import java.util.ArrayList;

public class DataList {
    String name;
    String rate;
    String def;
    ArrayList<emergence> emdata= new ArrayList<emergence>();

    public DataList(String name,String rate, String def, ArrayList emdata){
        this.name = name;
        this.rate = rate;
        this.def = def;
        this.emdata =emdata;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public ArrayList<emergence> getEmdata() {
        return emdata;
    }

    public void setEmdata(ArrayList<emergence> emdata) {
        this.emdata = emdata;
    }
}
