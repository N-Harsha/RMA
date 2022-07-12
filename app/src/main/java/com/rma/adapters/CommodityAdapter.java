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
import com.rma.items.Amenities;
import com.rma.myapplication.R;

import java.util.List;

public class CommodityAdapter extends ArrayAdapter<Amenities> {

    TextView commodity_name,quantity;
    FloatingActionButton remove;
    CustomButtonListener1 listener;

    public interface CustomButtonListener1{
        public void OnButtonClickListener(int pos);
    }

    public void setCustomListener(CustomButtonListener1 listener){
        this.listener=listener;
    }


    public CommodityAdapter(@NonNull Context context, @NonNull List<Amenities> objects) {
        super(context,R.layout.commodity_item,objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Amenities commodity=getItem(position);
        if(convertView==null)
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.commodity_item,parent,false);
        commodity_name=convertView.findViewById(R.id.commodity);
        quantity =convertView.findViewById(R.id.quantity);
        remove=convertView.findViewById(R.id.remove1);

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnButtonClickListener(position);
            }
        });
        commodity_name.setText(commodity.getType());
        String temp=Integer.toString(commodity.getNoOfCommodities());
        quantity.setText(temp);

        return convertView;
    }
}
