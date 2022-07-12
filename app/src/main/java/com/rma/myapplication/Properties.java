package com.rma.myapplication;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.rma.adapters.PropertyAdapter;
import com.rma.items.Property;
import com.rma.items.Tenant;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Properties extends AppCompatActivity {

    GridView gridView;
    ArrayList<Property> pl;
    Context context;
    FloatingActionButton fab;
    AlertDialog.Builder dialogBuilder;
    PropertyAdapter pa;
    AlertDialog dialog;
    TextInputEditText name;
    Button saveButton,cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties2);

        gridView =findViewById(R.id.properties_grid_view);
        fab=findViewById(R.id.property_add_button);

        context=this;

        pl=load("data.ser");

        pa=new PropertyAdapter(context,R.layout.property_item,pl);
        gridView.setAdapter(pa);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        fab.setOnClickListener(v -> createNewDialog());

    }

    public ArrayList<Property> load(String fileName){
        ArrayList<Property> temp;
        try{
            FileInputStream in=getApplicationContext().openFileInput(fileName);
            ObjectInputStream ois=new ObjectInputStream(in);
            temp= (ArrayList<Property>) ois.readObject();
            in.close();
            ois.close();
        } catch (Exception e) {
            temp=new ArrayList<>();
        }
        return temp;
    }

    public void save(Serializable data, String fileName){
        try{
            FileOutputStream out=getApplicationContext().openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream oos=new ObjectOutputStream(out);
            oos.writeObject(data);
            out.close();
            oos.close();
        } catch (IOException e) {
            Log.d(TAG, "save:  ");
            e.printStackTrace();
        }
    }

    void addProperty(String name){
        Property newProperty=new Property(name);
        pl.add(newProperty);
        gridView.setAdapter(pa);
    }

    public void createNewDialog(){
        dialogBuilder=new AlertDialog.Builder(this);
        final View propertyPopupView = getLayoutInflater().inflate(R.layout.popup,null);
        name=(TextInputEditText) propertyPopupView.findViewById(R.id.popup_property_name);

        saveButton=(Button) propertyPopupView.findViewById(R.id.save_property_button);
        cancelButton=(Button) propertyPopupView.findViewById(R.id.cancel_property_button);

        dialogBuilder.setView(propertyPopupView);
        dialog= dialogBuilder.create();
        dialog.show();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add a property
                addProperty(name.getText().toString());
                save(pl,"data.ser");
                dialog.dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

}