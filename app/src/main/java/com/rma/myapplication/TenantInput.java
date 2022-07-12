package com.rma.myapplication;

import static com.rma.myapplication.MainActivity.FLAG_KEY;
import static com.rma.myapplication.MainActivity.TENANT_KEY;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.TelephonyNetworkSpecifier;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.rma.items.Tenant;

import java.util.ArrayList;
import java.util.Calendar;

public class TenantInput extends AppCompatActivity {


    public static final int TENANT_OUTPUT = 96;

    TextInputEditText name,ph,alt_ph,dno,aadhaar_no,doj,rentAmount,RentAdv,occupation,job_id,permAddress,email,occupants,dueDate;
    Calendar calender_doj,calendar_dueDate;
    Tenant nt;

    ActivityResultLauncher<Intent> activityLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode()==VehicleInput.VEHICLE_OUTPUT)
            {
                Intent temp=result.getData();
                if(temp!=null) {
                    nt = (Tenant) temp.getSerializableExtra(TENANT_KEY);
                    if(temp.getBooleanExtra(FLAG_KEY,false))
                    {
                        Intent intent=new Intent();
                        intent.putExtra(TENANT_KEY,nt);
                        intent.putExtra(FLAG_KEY,true);
                        setResult(TENANT_OUTPUT,intent);
                        finish();
                    }
                }

            }
        }
    });


    public void addVehicle(){
        Intent intent=new Intent(this,VehicleInput.class);
        intent.putExtra(TENANT_KEY,nt);
        activityLauncher.launch(intent);
    }

    public void updateTenant(){
        String temp;

        nt.setName(name.getText().toString());//done
        nt.setPh(ph.getText().toString());//done
        nt.setAph(alt_ph.getText().toString());//done
        nt.setDno(dno.getText().toString());//done

        temp=rentAmount.getText().toString();
        if(temp.equals(""))
            nt.setRent_Amount(0);
        else
            nt.setRent_Amount(Integer.parseInt(temp));

        temp=RentAdv.getText().toString();
        if(temp.equals(""))
            nt.setAdv_mon(0);
        else
            nt.setAdv_mon(Integer.parseInt(temp));

        nt.setPerm_address(permAddress.getText().toString());
        nt.setJob_id(job_id.getText().toString());
        nt.setAadhaar_no(aadhaar_no.getText().toString());
        nt.setOccupation(occupation.getText().toString());

        nt.setDoj(calender_doj);
        nt.setDue_date(calendar_dueDate);

        temp=occupants.getText().toString();
        if(temp.equals(""))
            nt.setOccupants(1);
        else
            nt.setOccupants(Integer.parseInt(temp));
        nt.setEmail(email.getText().toString());


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_input);
        this.setTitle("Enter Tenant Details");

        Context context=this;

        nt=new Tenant();
        name = (TextInputEditText) findViewById(R.id.Tenant_Name);
        ph = (TextInputEditText) findViewById(R.id.Tenant_Phno);
        alt_ph = (TextInputEditText) findViewById(R.id.alt_phno);
        dno = (TextInputEditText) findViewById(R.id.dno);
        aadhaar_no = (TextInputEditText) findViewById(R.id.adhno);
        doj = (TextInputEditText) findViewById(R.id.doj);
        rentAmount=findViewById(R.id.rent_amount);
        RentAdv=findViewById(R.id.rent_adv);
        job_id=findViewById(R.id.job_id);
        occupation=findViewById(R.id.occupation);
        permAddress=findViewById(R.id.perm_add);
        email=findViewById(R.id.email);
        occupants = findViewById(R.id.occupants);
        dueDate = findViewById(R.id.dueDate);

        Common common=new Common();

        doj.setInputType(InputType.TYPE_NULL);
        doj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calender_doj = common.dateDialog(TenantInput.this,doj);
            }
        });

        dueDate.setInputType(InputType.TYPE_NULL);
        dueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar_dueDate=common.dateDialog(TenantInput.this,dueDate);
            }
        });


//this is to adjust the double tap error programmatically

//        doj.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(hasFocus){
//                    common.dateDialog(TenantInput.this,doj);
//                }
//            }
//        });


        Button next=(Button)findViewById(R.id.next1);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTenant();
                addVehicle();
            }
        });


    }


}