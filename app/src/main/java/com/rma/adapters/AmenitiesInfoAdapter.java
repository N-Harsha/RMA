package com.rma.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.rma.items.Amenities;
import com.rma.items.AutoMobiles;
import com.rma.myapplication.R;

import java.util.List;

public class AmenitiesInfoAdapter extends ArrayAdapter<Amenities> {


    public AmenitiesInfoAdapter(@NonNull Context context, @NonNull List<Amenities> objects) {
        super(context, R.layout.amenities_item, objects);
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Amenities obj=getItem(position);
        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.amenities_item,parent,false);
        }
        TextView name=convertView.findViewById(R.id.Amenity_name);
        TextView quant=convertView.findViewById(R.id.Amenity_quantity);

        name.setText(obj.getType());
        quant.setText(Integer.toString(obj.getNoOfCommodities()));
        return convertView;
    }
}
