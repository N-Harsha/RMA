package com.rma.items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Tenant implements Serializable{
    int T_id;

    List<Transactions> lt;
    //todo transactions page to be done
    List<AutoMobiles> lv;
    List<Amenities> lc;
    String name,guardian_name,ph,aph;//ph phone number, aph alternate phone number
    //todo guardian name in Tenant Input Page
    String job_id;//job id for tenant
    String Aadhaar_no;
    String Email;
    String Occupation;
    String Perm_address;
    String Dno;//door number
    Calendar due_date; // what is the next date of rent payment
    Calendar doj;//date of joining

    int occupants;//no.of people staying

    int rent_Amount; // amount of rent which they pay
    int adv_mon; // the no. of months for which they have paid advance
    int due_Amount=0; //the amount which is due

    public Tenant(){
        lt=new ArrayList<>();
        lv=new ArrayList<>();
        lc=new ArrayList<>();
    }



    public String getOccupation() {
        return Occupation;
    }
    public void setOccupation(String occupation) {
        Occupation = occupation;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
    public int getT_id() {
        return T_id;
    }

    public void setT_id(int t_id) {
        T_id = t_id;
    }

    public String getDno() {
        return Dno;
    }

    public void setDno(String dno) {
        Dno = dno;
    }

    public String getDue_Amount() {
        return Integer.toString(due_Amount);
    }

    public void setDue_Amount(String due_Amount) {
        this.due_Amount = Integer.parseInt(due_Amount);
    }

    public List<Transactions> getLt() {
        return lt;
    }

    public void setLt(List<Transactions> lt) {
        this.lt = lt;
    }

    public List<AutoMobiles> getLv() {
        return lv;
    }

    public void setLv(List<AutoMobiles> lv) {
        this.lv = lv;
    }

    public List<Amenities> getLc() {
        return lc;
    }

    public void setLc(List<Amenities> lc) {
        this.lc = lc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGuardian_name() {
        return guardian_name;
    }

    public void setGuardian_name(String guardian_name) {
        this.guardian_name = guardian_name;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getAph() {
        return aph;
    }

    public void setAph(String aph) {
        this.aph = aph;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public String getAadhaar_no() {
        return Aadhaar_no;
    }

    public void setAadhaar_no(String aadhaar_no) {
        this.Aadhaar_no = aadhaar_no;
    }

    public String getPerm_address() {
        return Perm_address;
    }

    public void setPerm_address(String perm_address) {
        Perm_address = perm_address;
    }

    public Calendar getDue_date() {
        return due_date;
    }

    public void setDue_date(Calendar due_date) {
        this.due_date = due_date;
    }

    public Calendar getDoj() {
        return doj;
    }

    public void setDoj(Calendar doj) {
        this.doj = doj;
    }

    public int getOccupants() {
        return occupants;
    }

    public void setOccupants(int occupants) {
        this.occupants = occupants;
    }

    public int getRent_Amount() {
        return rent_Amount;
    }

    public void setRent_Amount(int amount) {
        this.rent_Amount = amount;
    }

    public int getAdv_mon() {
        return adv_mon;
    }

    public void setAdv_mon(int adv_mon) {
        this.adv_mon = adv_mon;
    }

}

