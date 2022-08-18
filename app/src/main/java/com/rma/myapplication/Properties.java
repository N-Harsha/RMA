package com.rma.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.rma.adapters.PropertyAdapter;
import com.rma.items.Property;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Properties extends AppCompatActivity {

    final static public String PROPERTY_KEY="PROPERTY";
    final static public int PERMISSION_REQUEST_CODE=7;

    GridView gridView;
    ArrayList<Property> pl;
    Context context;
    FloatingActionButton fab;
    AlertDialog.Builder dialogBuilder;
    PropertyAdapter pa;
    AlertDialog dialog;
    TextInputEditText name;
    Button saveButton,cancelButton;
    int pos=0;

    ActivityResultLauncher<Intent> activityLauncher=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Property property=null;
                    if(result.getResultCode()==MainActivity.MainActivity_output){
                        Intent temp=result.getData();
                        if(temp!=null){
                            pl.set(pos,(Property) temp.getSerializableExtra(PROPERTY_KEY));
                        }
                    }
                    save(pl,"data.ser");
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties2);

        checkFolder();


        gridView =findViewById(R.id.properties_grid_view);
        fab=findViewById(R.id.property_add_button);

        context=this;

        pl=load("data.ser");

        pa=new PropertyAdapter(context,R.layout.property_item,pl);
        gridView.setAdapter(pa);

//        Log.d(TAG, "onCreate: "+context.getFilesDir());


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos=position;
                Intent intent=new Intent(context,MainActivity.class);
                intent.putExtra(PROPERTY_KEY,pl.get(position));
                activityLauncher.launch(intent);
            }
        });

        fab.setOnClickListener(v -> createNewDialog());

    }

    private void checkFolder() {
        if(ContextCompat.checkSelfPermission(Properties.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            createFolder();
        }
        else{
            askPermission();
        }
    }

    private void createFolder() {
        File file=new File(Environment.getDataDirectory(),"RMA");
//        File file=new File(Environment.getExternalStorageDirectory(),"RMA");
        if(!file.exists()) {
            file.mkdir();
            Toast.makeText(Properties.this, "Successful....", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(Properties.this, "File already exists...", Toast.LENGTH_SHORT).show();
    }

    private void askPermission() {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==PERMISSION_REQUEST_CODE){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                createFolder();
            }
            else{
                Toast.makeText(Properties.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
//            Log.d(TAG, "save:  ");
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