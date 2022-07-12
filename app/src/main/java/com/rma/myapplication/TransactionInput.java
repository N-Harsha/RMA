package com.rma.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import com.rma.items.Transactions;

import java.io.Serializable;
import java.util.Calendar;

public class TransactionInput extends AppCompatActivity {

    TextInputEditText paid_on,amount;
    AutoCompleteTextView mot;
    Button add;
    ArrayAdapter<String> adapterItems;
    Transactions res;

    final static public String TRANSACTION_KEY="TRANSACTION";
    final static public int Transaction_output=121;

    @Override
    public void onBackPressed() {
        Intent intent=new Intent();
        intent.putExtra(TRANSACTION_KEY, (Serializable) null);
        setResult(Transaction_output);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_input);

        String[] modes={"Cash","UPI","Net banking"};

        res=new Transactions();

        res.setMot("Cash");
        res.setPaid_on(Calendar.getInstance());


        paid_on=findViewById(R.id.paid_on_tr_input);
        amount=findViewById(R.id.amount_tr_input);
        mot=findViewById(R.id.auto_complete_txt);
        add=findViewById(R.id.transaction_add);


        adapterItems = new ArrayAdapter<String>(this,R.layout.dropdownlistelement,modes);
        mot.setAdapter(adapterItems);

        mot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item= parent.getItemAtPosition(position).toString();
                res.setMot(item);
            }
        });

        Common common=new Common();

        paid_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                res.setPaid_on(common.dateDialog(TransactionInput.this,paid_on));
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String temp;
                temp=amount.getText().toString();
                if(temp.equals(""))
                    res.setAmount(0);
                else
                    res.setAmount(Integer.parseInt(temp));

                Intent intent = new Intent();
                intent.putExtra(TRANSACTION_KEY,res);
                setResult(Transaction_output,intent);
                finish();

            }
        });

    }
}