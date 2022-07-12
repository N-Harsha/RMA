package com.rma.myapplication;

import static android.content.ContentValues.TAG;
import static com.rma.myapplication.MainActivity.TENANT_KEY;
import static com.rma.myapplication.TransactionInput.TRANSACTION_KEY;
import static com.rma.myapplication.TransactionInput.Transaction_output;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rma.adapters.TransactionAdapter;
import com.rma.items.Tenant;
import com.rma.items.Transactions;

import java.util.ArrayList;

import java.util.Calendar;
import java.util.Collections;

import java.util.GregorianCalendar;
import java.util.List;


public class RentalDetails extends AppCompatActivity  implements TransactionAdapter.CustomButtonListener{

    TextView rent_amount,due_date,doj,recent_payment,advance;
    ListView transaction_list;
    Tenant tenant;
    List<Transactions> transactions,revTrans;
    TransactionAdapter transactionAdapter;
    final static public int rentalDetails_Key =321;
    Context present;
    FloatingActionButton addTransaction;


    @Override
    public void onBackPressed() {
        tenant.setLt(transactions);
        Intent intent=new Intent();
        intent.putExtra(TENANT_KEY,tenant);
        setResult(rentalDetails_Key,intent);
        super.onBackPressed();
    }

    ActivityResultLauncher<Intent> activityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode()==Transaction_output){
                Intent resIntent=result.getData();
                if(resIntent!=null){
                   Transactions trans = (Transactions) resIntent.getSerializableExtra(TRANSACTION_KEY);
                   if(trans!=null) {
                       transactions.add(trans);
                       revTrans.add(0, trans);
                       transaction_list.setAdapter(transactionAdapter);

                   }
                }
            }
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_details);
        this.setTitle("Rental Details");

        tenant=new Tenant();

        if(getIntent()!=null&&getIntent().getExtras()!=null&&getIntent().hasExtra(TENANT_KEY))
            tenant=(Tenant) getIntent().getSerializableExtra(TENANT_KEY);

        transactions=tenant.getLt();
        revTrans=new ArrayList<>(transactions);
        Collections.reverse(revTrans);



        rent_amount= findViewById(R.id.rent_amount_rental_details);
        due_date= findViewById(R.id.due_date_rental_details);
        doj= findViewById(R.id.doj_rental_details);
        recent_payment= findViewById(R.id.recentPayment_rental_details);
        advance= findViewById(R.id.rent_adv_rental_details);
        transaction_list = findViewById(R.id.transaction_list);
        addTransaction = findViewById(R.id.addTransaction);

        present=this;
        addTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(present,TransactionInput.class);
                activityLauncher.launch(intent);
            }
        });




        basicInfoSetText();

        transactionAdapter =new TransactionAdapter(this, revTrans);
        transactionAdapter.setListener(this);
        transaction_list.setAdapter(transactionAdapter);

    }

    void basicInfoSetText(){ // this block of code sets the text from tenant obj to basic info block

        Common common=new Common();
        String s;
        s=Integer.toString(tenant.getRent_Amount());
        rent_amount.setText(s);
        s=Integer.toString(tenant.getAdv_mon());
        advance.setText(s);

        due_date.setText(common.dateFormatter(tenant.getDue_date()));
        doj.setText(common.dateFormatter(tenant.getDoj()));



        if(transactions.size()==0){
            recent_payment.setText(common.dateFormatter(tenant.getDoj()));
        }
        else{
            recent_payment.setText(common.dateFormatter(transactions.get(transactions.size()-1).getPaid_on()));
        }

    }

    @Override
    public void onButtonClick(int pos) {
//        transactions.remove(pos);
        revTrans.remove(pos);
        transactions.remove(transactions.size()-1-pos);
        transaction_list.setAdapter(transactionAdapter);
    }
}