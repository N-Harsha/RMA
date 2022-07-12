package com.rma.myapplication;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import static com.rma.myapplication.MainActivity.FLAG_KEY;
import static com.rma.myapplication.MainActivity.TENANT_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;

import com.google.android.material.textfield.TextInputEditText;
import com.rma.adapters.VehicleListAdapter;
import com.rma.items.AutoMobiles;
import com.rma.items.Tenant;

public class VehicleInput extends AppCompatActivity implements VehicleListAdapter.CustomButtonListener {

    public static final int VEHICLE_OUTPUT = 56;

    AutoMobiles vehicle;
    Tenant tenant;
    TextInputEditText model;
    RadioButton bike,car;
    TextInputEditText regno;
    VehicleListAdapter vla;
    ListView lv;
    Button next,add;

    @Override
    public void onBackPressed() {
        Intent intent=new Intent();
        intent.putExtra(TENANT_KEY,tenant);
        setResult(VEHICLE_OUTPUT,intent);
        super.onBackPressed();
    }

    ActivityResultLauncher<Intent> activityLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode()== AmenitiesInput.COMMODITIES_OUTPUT);
            {
                Intent temp=result.getData();
                if(temp!=null) {
                    tenant = (Tenant) temp.getSerializableExtra(TENANT_KEY);

                    if(temp.getBooleanExtra(FLAG_KEY,false))
                    {

                        Intent intent=new Intent();
                        intent.putExtra(TENANT_KEY,tenant);
                        intent.putExtra(FLAG_KEY,true);
                        setResult(VEHICLE_OUTPUT,intent);
                        finish();
                    }
                    else
                    {
                        recreate();
                    }
                }

            }
        }
    });


    public void addVehicle(){
        vehicle=new AutoMobiles();

        model= (TextInputEditText) findViewById(R.id.ModelIN);
        car=(RadioButton)findViewById(R.id.car_id);
        bike=(RadioButton)findViewById(R.id.bike_id);
        regno= (TextInputEditText) findViewById(R.id.reg_numIN);

        vehicle.setModel(model.getText().toString());
        if(car.isChecked()){
            vehicle.setType("Car");
        }
        else{
            vehicle.setType("Bike");
        }
        vehicle.setLpn(regno.getText().toString());

        tenant.getLv().add(vehicle);

        model.setText("");
        car.setChecked(false);
        bike.setChecked(false);
        regno.setText("");


        lv.setAdapter(vla);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setTitle("Enter Vehicle Details");
        setContentView(R.layout.activity_vehicle_input);

        tenant=new Tenant();
        if(getIntent()!=null&&getIntent().getExtras()!=null&&getIntent().hasExtra(TENANT_KEY)){
            tenant=(Tenant) getIntent().getSerializableExtra(TENANT_KEY);
        }

        add=findViewById(R.id.add_vehicle);
        next=(Button)findViewById(R.id.next2);

        vla=new VehicleListAdapter(this,tenant.getLv());
        vla.setCustomButtonListener(VehicleInput.this);
        lv=findViewById(R.id.vehicle);
        lv.setAdapter(vla);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addVehicle();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCommodities();
            }
        });

    }

    private void addCommodities() {
        Intent intent=new Intent(this, AmenitiesInput.class);
        intent.putExtra(TENANT_KEY,tenant);
        activityLauncher.launch(intent);
    }


    @Override
    public void OnButtonClickListener(int pos) {
        tenant.getLv().remove(pos);
        lv.setAdapter(vla);
    }
}