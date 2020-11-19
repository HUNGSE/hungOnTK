package com.example.sqlite2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    DataSinhVien dataSinhVien;
    Button add_btn,remove_btn,cancel_btn;
    EditText tenSV_edit;
    ListView lvTenSV;
    ArrayList tenSVlist;
    ArrayList idSVlist;
    ArrayAdapter adapter;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         dataSinhVien= new
                 DataSinhVien(this,"SinhViemdb.sql",null,1);
        tenSVlist= new ArrayList();
        idSVlist=new ArrayList();
        add_btn=findViewById(R.id.add_btn);
        remove_btn=findViewById(R.id.remove_btn);   
        cancel_btn=findViewById(R.id.cancel_btn);
        tenSV_edit=findViewById(R.id.tenSV_edit);
        lvTenSV=findViewById(R.id.lv_tenSV);
        gettenSVList();
        adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,tenSVlist);
        lvTenSV.setAdapter(adapter);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSinhVien.addSinhVien(new SinhVien(tenSV_edit.getText().toString()));
                gettenSVList();
                adapter.notifyDataSetChanged();
                tenSV_edit.clearFocus();
            }
        });
        remove_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSinhVien.removeSinhVien((int)idSVlist.get(index));
                gettenSVList();
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Succesful", Toast.LENGTH_SHORT).show();
            }
        });
        lvTenSV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                index=i;
            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private ArrayList gettenSVList(){
            tenSVlist.clear();
            idSVlist.clear();
        for (Iterator iterator = dataSinhVien.getALl().iterator(); iterator.hasNext(); ) {
            SinhVien sinhVien =  (SinhVien) iterator.next();
            tenSVlist.add(sinhVien.getTenSV());
            idSVlist.add(sinhVien.getId());
        }
        return tenSVlist;
    }
}