package com.rma.myapplication;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class Common {
    Calendar resDate;

    public Calendar dateDialog(Context context, TextInputEditText et){

        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);

        resDate=new GregorianCalendar(year,month,day);

        DatePickerDialog datePickerDialog= new DatePickerDialog(
                context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month=month+1;
                setResDate(year,month,day);
                et.setText(dateFormatter(resDate));
            }
        },year,month,day);
        datePickerDialog.show();
        return resDate;
    }

    public String dateFormatter(Calendar calendar){

        return calendar.get(Calendar.DAY_OF_MONTH) +"/" + (calendar.get(Calendar.MONTH))+"/"+(calendar.get(Calendar.YEAR)%100);


    }

    public void setResDate(int  year,int month,int day) {
        this.resDate.set(year,month,day);
    }
}
