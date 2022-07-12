package com.rma.items;

import java.io.Serializable;

public class AutoMobiles implements Serializable {

    String lpn,type,model;

    //lpn - licence plate registration number
    //type - car or bike
    //model - car model name
    public AutoMobiles(){

    }

    public String getLpn() {
        return lpn;
    }

    public void setLpn(String lpn) {
        this.lpn = lpn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public AutoMobiles(String lpn, String type, String model) {
        this.lpn = lpn;
        this.type = type;
        this.model = model;
    }
}
