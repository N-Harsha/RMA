package com.rma.myapplication;

import static com.rma.myapplication.MainActivity.TENANT_KEY;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.rma.items.Tenant;

import de.hdodenhof.circleimageview.CircleImageView;
public class ProfessionalInfo extends AppCompatActivity {
    TextView ph,oph,email,office_address,name;
    CircleImageView img;
    Tenant tenant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional_info);
        this.setTitle("Professional Details");
        ph=findViewById(R.id.Tenant_Info_phno);
        oph=findViewById(R.id.Tenant_Info_altphno);
        office_address=findViewById(R.id.PersonalResidents);
        name=findViewById(R.id.Details_Tenant_name);
        img=findViewById(R.id.Details_Tenant_image);
        tenant=new Tenant();
        if(getIntent()!=null&&getIntent().getExtras()!=null&&getIntent().hasExtra(TENANT_KEY)){
            tenant=(Tenant) getIntent().getSerializableExtra(TENANT_KEY);
        }
    }
}