package com.rma.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import static com.rma.myapplication.MainActivity.FLAG_KEY;
import static com.rma.myapplication.MainActivity.TENANT_KEY;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.textfield.TextInputEditText;
import com.rma.adapters.CommodityAdapter;
import com.rma.items.Amenities;
import com.rma.items.Tenant;

public class AmenitiesInput extends AppCompatActivity implements CommodityAdapter.CustomButtonListener1 {

    Tenant tenant;
    Button add,submit,inc,dec;
    TextInputEditText name,quantity;
    ListView lv;
    CommodityAdapter cla;
    Amenities com;

    final public static int COMMODITIES_OUTPUT=69;

    @Override
    public void onBackPressed() {
        Intent intent=new Intent();
        intent.putExtra(TENANT_KEY,tenant);
        setResult(COMMODITIES_OUTPUT,intent);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Amenities Provided");
        setContentView(R.layout.activity_commodities_input);

        name=(TextInputEditText) findViewById(R.id.commodityIN);
        quantity=(TextInputEditText) findViewById(R.id.quantityIN);
        com=new Amenities();

        if(getIntent()!=null&&getIntent().getExtras()!=null&&getIntent().hasExtra(TENANT_KEY)){
            tenant=(Tenant) getIntent().getSerializableExtra(TENANT_KEY);
        }

        add=(Button) findViewById(R.id.add_commodity);
        submit=(Button) findViewById(R.id.submit_tenant);
        inc=(Button) findViewById(R.id.increment);
        dec=(Button) findViewById(R.id.decrement);

        cla =new CommodityAdapter(this,tenant.getLc());
        cla.setCustomListener(this);
        lv = (ListView)findViewById(R.id.commoditiesList);
        lv.setAdapter(cla);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCommodity();
            }
        });

        inc.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                com.setNoOfCommodities(com.getNoOfCommodities()+1);
                quantity.setText(Integer.toString(com.getNoOfCommodities()));
            }
        });


        dec.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(com.getNoOfCommodities()>0) {
                    com.setNoOfCommodities(com.getNoOfCommodities() - 1);
                    quantity.setText(Integer.toString(com.getNoOfCommodities()));
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra(TENANT_KEY,tenant);
                intent.putExtra(FLAG_KEY,true);
                setResult(COMMODITIES_OUTPUT,intent);
                finish();
            }
        });

    }

    private void addCommodity() {
        com.setType(name.getText().toString());
        tenant.getLc().add(com);
        name.setText("");
        quantity.setText("0");
        lv.setAdapter(cla);
        com=new Amenities();
    }

    @Override
    public void OnButtonClickListener(int pos) {
        tenant.getLc().remove(pos);
        lv.setAdapter(cla);
    }
}