package com.rma.myapplication;

import static com.rma.myapplication.MainActivity.TENANT_KEY;
import static com.rma.myapplication.RentalDetails.rentalDetails_Key;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rma.items.Tenant;

import de.hdodenhof.circleimageview.CircleImageView;

public class TenantInfo extends AppCompatActivity {

    CardView personal,professional,rental,attachments,maintenance,amenities;
    TextView name;
    Tenant tenant;
    CircleImageView image;

    final static public int TenantInfo_Key=434;

    ActivityResultLauncher<Intent> activityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result!=null){
                if(result.getResultCode()==rentalDetails_Key){
                    Intent resIntent=result.getData();
                    if(resIntent!=null)
                    tenant=(Tenant)resIntent.getSerializableExtra(TENANT_KEY);
//                    Log.d("Hello", tenant.getLt().toString());
                }
            }
        }
    });

    @Override
    public void onBackPressed() {
        Intent intent =new Intent();
        intent.putExtra(TENANT_KEY,tenant);
        setResult(TenantInfo_Key,intent);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Tenant Information");
        setContentView(R.layout.activity_tenant_info);

        personal=findViewById(R.id.personalDetails);
        professional=findViewById(R.id.professionalDetails);
        rental= findViewById(R.id.rentalDetails);
        attachments= findViewById(R.id.attachmentDetails);
        amenities=findViewById(R.id.amenitiesDetails);
        maintenance= findViewById(R.id.maintenanceDetails);
        name=findViewById(R.id.Details_Tenant_name);
        image=findViewById(R.id.Details_Tenant_image);

        tenant=new Tenant();
        if(getIntent()!=null&&getIntent().getExtras()!=null&&getIntent().hasExtra(TENANT_KEY)){
            tenant=(Tenant) getIntent().getSerializableExtra(TENANT_KEY);
        }

        Context present=this;
        name.setText(tenant.getName());

        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(present,PersonalInfo.class) ;
                printSomething("Personal");//not imp
                intent.putExtra(TENANT_KEY,tenant);
                startActivity(intent);
            }
        });

        professional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printSomething("Professional");
                Intent intent=new Intent(present,ProfessionalInfo.class);
                intent.putExtra(TENANT_KEY,tenant);
                startActivity(intent);
            }


        });


        amenities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printSomething("Amenities");
                Intent intent =new Intent(present,AmenitiesInfo.class);
                intent.putExtra(TENANT_KEY,tenant);
                startActivity(intent);
            }
        });

        rental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("Hello", tenant.getLt().toString());
                printSomething("Rental");
                Intent intent =new Intent(present,RentalDetails.class);
                intent.putExtra(TENANT_KEY,tenant);
//                startActivity(intent);
                activityLauncher.launch(intent);
            }

        });

        attachments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printSomething("Attachments");
            }
        });

        maintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printSomething("Maintenance");
            }
        });

    }

    private void printSomething(String str) {
        str="you have clicked "+str+" details";
        Toast t=Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT);
        t.show();
    }
}