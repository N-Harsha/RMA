package com.rma.adapters;
import com.rma.items.*;
import com.rma.myapplication.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TenantListAdapter extends ArrayAdapter<Tenant> {
    public TenantListAdapter(Context context, ArrayList<Tenant> tenants){
        super(context, R.layout.list_item,tenants);
    }

    @SuppressLint("SimpleDateFormat") SimpleDateFormat f=new SimpleDateFormat("dd/MM/yy");

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Tenant tenant =getItem(position);

        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        TextView userName=convertView.findViewById(R.id.PersonName);
        TextView dno=convertView.findViewById(R.id.doorId);
        TextView phno=convertView.findViewById(R.id.phno);
        TextView dueAmount=convertView.findViewById(R.id.dueAmount);
        TextView recentPayment=convertView.findViewById(R.id.recentPayment);

        userName.setText(tenant.getName());
        dno.setText(tenant.getDno());
        phno.setText(tenant.getPh());
        dueAmount.setText(tenant.getDue_Amount());
//        recentPayment.setText(tenant.getDue_date().toString());
//        recentPayment.setText(temp);

        return convertView;
    }
}
