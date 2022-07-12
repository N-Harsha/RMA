package com.rma.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rma.items.AutoMobiles;
import com.rma.myapplication.R;

import java.util.List;

public class VehicleListAdapter extends ArrayAdapter<AutoMobiles> {
    CustomButtonListener customListener;
    public interface CustomButtonListener{
        public void OnButtonClickListener(int pos);
    }
    public void setCustomButtonListener(CustomButtonListener listener){
        this.customListener=listener;
    }

    public VehicleListAdapter(@NonNull Context context,  @NonNull List<AutoMobiles> objects) {
        super(context, R.layout.vehicle_item, objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        AutoMobiles vehicle =getItem(position);
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.vehicle_item,parent,false);
        }
        TextView model=convertView.findViewById(R.id.Model);
        TextView type=convertView.findViewById(R.id.type);
        TextView regno=convertView.findViewById(R.id.reg_num);

        FloatingActionButton remove=convertView.findViewById(R.id.remove);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customListener.OnButtonClickListener(position);
            }
        });

        model.setText("Model : "+vehicle.getModel());
        type.setText("("+vehicle.getType()+")");
        regno.setText("Registration No : "+vehicle.getLpn());
        return convertView;
    }
}
