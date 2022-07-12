package com.rma.items;

import java.io.Serializable;
import java.util.ArrayList;

public class Property implements Serializable {
    ArrayList<Tenant> tl;
    String propertyName;

    public Property(String name){
        tl=new ArrayList<>();
        this.propertyName=name;
    }

    public ArrayList<Tenant> getTl() {
        return tl;
    }

    public void setTl(ArrayList<Tenant> tl) {
        this.tl = tl;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
}
