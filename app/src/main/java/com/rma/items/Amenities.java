package com.rma.items;

import java.io.Serializable;

public class Amenities implements Serializable {
    int NoOfCommodities;

    public int getNoOfCommodities() {
        return NoOfCommodities;
    }

    public String getType() {
        return Type;
    }

    String Type;

    public void setNoOfCommodities(int noOfCommodities) {
        NoOfCommodities = noOfCommodities;
    }

    public void setType(String type) {
        Type = type;
    }

}
