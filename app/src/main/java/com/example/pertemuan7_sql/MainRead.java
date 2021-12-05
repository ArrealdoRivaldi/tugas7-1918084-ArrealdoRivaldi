package com.example.pertemuan7_sql;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainRead extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView mListView;
    private CustomListAdapter adapter_off;
    private MyDatabase db;
    private List<Buah> ListBuah = new ArrayList<Buah>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_read);
        db = new MyDatabase(this);
        adapter_off = new CustomListAdapter(this, ListBuah);
        mListView = (ListView) findViewById(R.id.list_buah);
        mListView.setAdapter(adapter_off);
        mListView.setOnItemClickListener(this);
        mListView.setClickable(true);
        ListBuah.clear();
        List<Buah> buah = db.ReadBuah();
        for (Buah bh : buah) {
            Buah daftar = new Buah();
            daftar.set_id(bh.get_id());
            daftar.set_nama(bh.get_nama());
            daftar.set_harga(bh.get_harga());
            ListBuah.add(daftar);
            if ((ListBuah.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        Object o = mListView.getItemAtPosition(i);
        Buah detailBh = (Buah)o;
        String Sid = detailBh.get_id();
        String Snama = detailBh.get_nama();
        String Sharga = detailBh.get_harga();
        Intent goUpdel = new Intent(MainRead.this, MainUpdel.class);
        goUpdel.putExtra("Iid", Sid);
        goUpdel.putExtra("Inama", Snama);
        goUpdel.putExtra("Iharga", Sharga);
        startActivity(goUpdel);
    }
    @Override
    protected void onResume() {
        super.onResume();
        ListBuah.clear();
        mListView.setAdapter(adapter_off);
        List<Buah> buah = db.ReadBuah();
        for (Buah bh : buah) {
            Buah daftar = new Buah();
            daftar.set_id(bh.get_id());
            daftar.set_nama(bh.get_nama());
            daftar.set_harga(bh.get_harga());
            ListBuah.add(daftar);
            if ((ListBuah.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }
}
