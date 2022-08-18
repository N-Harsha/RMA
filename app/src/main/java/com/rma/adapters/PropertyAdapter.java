package com.rma.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.rma.items.Property;
import com.rma.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class PropertyAdapter extends ArrayAdapter<Property> {
    int resource;
//    String[] colors={"#a8e6cf","#dcedc1","#ffd3b6","#ffaaa5","#ff8b94"};
    String[] colors={"#ff3f3f","#FC652C","#e0e300","#01dddd","#00bfaf"};
    public PropertyAdapter(@NonNull Context context, int resource, @NonNull List<Property> objects) {
        super(context, resource, objects);
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Property temp=getItem(position);

        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(resource,parent,false);
        }
        TextView name=convertView.findViewById(R.id.property_name);
        convertView.setBackgroundColor(Color.parseColor(colors[position%colors.length]));
//        CardView cardView=convertView.findViewById(R.id.property_item);
//        cardView.setBackgroundColor(Color.parseColor(colors[position%colors.length]));
//
        name.setText(temp.getPropertyName());


        return convertView;
    }
}
