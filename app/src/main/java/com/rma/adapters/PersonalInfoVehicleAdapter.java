package com.rma.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.rma.items.AutoMobiles;
import com.rma.items.Tenant;
import com.rma.myapplication.R;

import java.util.List;

public class PersonalInfoVehicleAdapter extends ArrayAdapter<AutoMobiles> {


    public PersonalInfoVehicleAdapter(@NonNull Context context, @NonNull List<AutoMobiles> objects) {
        super(context, R.layout.personalinfovehicles, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        AutoMobiles presentObj=getItem(position);
        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.personalinfovehicles,parent,false);
        }
        TextView model=convertView.findViewById(R.id.personalInfoModel);
        TextView type=convertView.findViewById(R.id.personalInfoType);
        TextView regno=convertView.findViewById(R.id.personalInfoRegNo);
        model.setText(presentObj.getModel());
        type.setText(presentObj.getType());
        regno.setText(presentObj.getLpn());
        return convertView;
    }
}
