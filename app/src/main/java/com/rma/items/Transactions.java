package com.rma.items;

import java.io.Serializable;
import java.util.Calendar;


public class Transactions implements Serializable {

    String mot;//mot--Mode of Transfer
    Calendar paid_on;
    int amount;

    public String getMot() {
        return mot;
    }

    public void setMot(String mot) {
        this.mot = mot;
    }

    public Calendar getPaid_on() {
        return paid_on;
    }

    public void setPaid_on(Calendar paid_on) {
        this.paid_on = paid_on;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


}
