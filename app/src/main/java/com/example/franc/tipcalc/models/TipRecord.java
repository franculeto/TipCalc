package com.example.franc.tipcalc.models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by franc on 11/2/2017.
 */

public class TipRecord {

    private double bill;
    private int tipPercentage;
    private Date timeStamp;

    public int getTipPercentage() {
        return tipPercentage;
    }

    public void setTipPercentage(int tipPercentage) {
        this.tipPercentage = tipPercentage;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public double getTip()
    {
        return bill*(tipPercentage/100d);
    }

    public String getDateFormatedd()
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd,yyyy,HH:mm");
        return simpleDateFormat.format(timeStamp);

    }
}
