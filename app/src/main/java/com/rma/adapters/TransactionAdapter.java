package com.rma.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rma.items.Transactions;
import com.rma.myapplication.R;

import java.util.Calendar;
import java.util.List;


public class TransactionAdapter extends ArrayAdapter<Transactions> {

    CustomButtonListener customButtonListener;

    public interface CustomButtonListener{
        void onButtonClick(int pos);
    }
    public void setListener(CustomButtonListener customButtonListener){
        this.customButtonListener=customButtonListener;
    }


    public TransactionAdapter(@NonNull Context context, @NonNull List<Transactions> objects) {
        super(context, R.layout.transaction_item, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Transactions transaction=getItem(position);
        TextView amount,paid_on,mot;
        FloatingActionButton remove;

        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.transaction_item,parent,false);
        }

        remove=convertView.findViewById(R.id.remove_transaction);
        amount=convertView.findViewById(R.id.rent_amount_list_item);
        paid_on=convertView.findViewById(R.id.paid_on_list_item);
        mot=convertView.findViewById(R.id.mot_list_item);

        String s=Integer.toString(transaction.getAmount());
        amount.setText(s);

        paid_on.setText(dateFormatter(transaction.getPaid_on()));
        mot.setText(transaction.getMot());

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customButtonListener.onButtonClick(position);
            }
        });


        return convertView;
    }

    public String dateFormatter(Calendar calendar){

        return calendar.get(Calendar.DAY_OF_MONTH) +"/" + (calendar.get(Calendar.MONTH)+1)+"/"+(calendar.get(Calendar.YEAR)%100);


    }
}
