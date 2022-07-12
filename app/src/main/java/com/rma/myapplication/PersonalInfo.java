package com.rma.myapplication;

import static com.rma.myapplication.MainActivity.TENANT_KEY;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.rma.adapters.PersonalInfoVehicleAdapter;
import com.rma.items.AutoMobiles;
import com.rma.items.Tenant;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersonalInfo extends AppCompatActivity {

    TextView ph,aph,email,residents,aadhaar,name;
    CircleImageView image;
    Tenant tenant;
    ListView vehicleListView;
    PersonalInfoVehicleAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Personal Information");
        setContentView(R.layout.activity_personal_info);
        ph=findViewById(R.id.Tenant_Info_phno);
        aph=findViewById(R.id.Tenant_Info_altphno);
        email=findViewById(R.id.PersonalEmail);
        residents=findViewById(R.id.PersonalResidents);
        aadhaar=findViewById(R.id.Personalaadhaar);
        name=findViewById(R.id.Details_Tenant_name);
        image=findViewById(R.id.Details_Tenant_image);
        vehicleListView=findViewById(R.id.personalInfoVehicles);

        tenant=new Tenant();
        if(getIntent()!=null&&getIntent().getExtras()!=null&&getIntent().hasExtra(TENANT_KEY)){
            tenant=(Tenant) getIntent().getSerializableExtra(TENANT_KEY);
        }

        ph.setText(tenant.getPh());
        aph.setText(tenant.getAph());
        email.setText(tenant.getEmail());
        name.setText(tenant.getName());
        aadhaar.setText(tenant.getAadhaar_no());
        arrayAdapter =new PersonalInfoVehicleAdapter(this,tenant.getLv());
        vehicleListView.setAdapter(arrayAdapter);

    }
}