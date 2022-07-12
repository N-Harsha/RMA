package com.rma.myapplication;


import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rma.adapters.TenantListAdapter;
import com.rma.items.Tenant;
import com.rma.myapplication.databinding.ActivityMainBinding;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Tenant> al;
    TenantListAdapter la;
    ActivityMainBinding binding;
    ListView lv;
    int pos;


    final static public String TENANT_KEY="TENANT";
    final static public String FLAG_KEY="flag";

    ActivityResultLauncher<Intent> activityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Tenant tenant=null;
                    if(result.getResultCode()==TenantInput.TENANT_OUTPUT)
                    {
                        Intent temp=result.getData();
                        if(temp!=null) {
                            tenant = (Tenant) temp.getSerializableExtra(TENANT_KEY);
                            if(temp.getBooleanExtra(FLAG_KEY,false)&&tenant!=null)
                            {
                                al.add(tenant);
                            }
                        }

                    }
                    else if(result.getResultCode()==TenantInfo.TenantInfo_Key){
                        Intent temp=result.getData();
                        if(temp!=null){
                            al.set(pos,(Tenant) temp.getSerializableExtra(TENANT_KEY));
                        }
                    }
                    save(al,"hello.ser");
                    lv.setAdapter(la);
                }
            }
    );

    public ArrayList<Tenant> load(String fileName){
        ArrayList<Tenant> temp;
        try{
            FileInputStream in=getApplicationContext().openFileInput(fileName);
            ObjectInputStream ois=new ObjectInputStream(in);
            temp= (ArrayList<Tenant>) ois.readObject();
            in.close();
            ois.close();
            return temp;
        } catch (Exception e) {
            temp=new ArrayList<>();
            return temp;
        }
    }

    public void save(Serializable data,String fileName){
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        al = load("hello.ser");
//        al.clear();
//        save(al,"hello.ser");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat f=new SimpleDateFormat("dd/MM/yy");


        FloatingActionButton fab=findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(v -> addTenant());




        la=new TenantListAdapter(this,al);
        lv=findViewById(R.id.simpleListView);
        binding.simpleListView.setAdapter(la);
        binding.simpleListView.setClickable(true);
        binding.simpleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                openTenantDetails(position);

            }
        });


    }

    private void openTenantDetails(int position) {
        Intent intent=new Intent(this,TenantInfo.class);
        intent.putExtra(TENANT_KEY,al.get(position));
        pos=position;
        activityLauncher.launch(intent);
    }

    public void addTenant(){
        Intent intent=new Intent(this, TenantInput.class);
        activityLauncher.launch(intent);


    }

}