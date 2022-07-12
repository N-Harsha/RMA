package com.rma.myapplication;

import static com.rma.myapplication.MainActivity.TENANT_KEY;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.rma.adapters.AmenitiesInfoAdapter;
import com.rma.items.Tenant;

public class AmenitiesInfo extends AppCompatActivity {

    ListView lv;
    AmenitiesInfoAdapter arrayAdapter;
    Tenant t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amenities_info);
        this.setTitle("Amenities");
        Tenant t=new Tenant();
        if(getIntent()!=null&&getIntent().getExtras()!=null&&getIntent().hasExtra(TENANT_KEY))
            t=(Tenant)getIntent().getSerializableExtra(TENANT_KEY);

        lv=findViewById(R.id.Amenity_list);
//        Toast.makeText(this, t.getLc().toString(), Toast.LENGTH_SHORT).show();
        arrayAdapter = new AmenitiesInfoAdapter(this,t.getLc());
        lv.setAdapter(arrayAdapter);
    }
}