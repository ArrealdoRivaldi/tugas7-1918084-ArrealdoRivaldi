package com.example.pertemuan7_sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "db_komoditi";
    private static final String tb_buah = "tb_buah";
    private static final String tb_buah_id = "id";
    private static final String tb_buah_nama = "nama";
    private static final String tb_buah_harga = "harga";
    private static final String CREATE_TABLE_BUAH = "CREATE TABLE " +
    tb_buah +"("
            + tb_buah_id + " INTEGER PRIMARY KEY ,"
            + tb_buah_nama + " TEXT ,"
            + tb_buah_harga + " TEXT " + ")";
    public MyDatabase (Context context){
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BUAH);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
    }
    public void CreateBuah(Buah data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_buah_id, data.get_id());
        values.put(tb_buah_nama, data.get_nama());
        values.put(tb_buah_harga, data.get_harga());
        db.insert(tb_buah, null, values);
        db.close();
    }
    public List<Buah> ReadBuah() {
        List<Buah> listbh = new ArrayList<Buah>();
        String selectQuery = "SELECT * FROM " + tb_buah;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Buah data = new Buah();
                data.set_id(cursor.getString(0));
                data.set_nama(cursor.getString(1));
                data.set_harga(cursor.getString(2));
                listbh.add(data);
            } while (cursor.moveToNext());
        }
        db.close();
        return listbh;
    }
    public int UpdateBuah (Buah data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_buah_nama, data.get_nama());
        values.put(tb_buah_harga, data.get_harga());
        return db.update(tb_buah, values, tb_buah_id +
                        " = ?",
                new String[]{String.valueOf((data.get_id()))});
    }
    public void DeleteBuah(Buah data){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tb_buah,tb_buah_id+ " = ?",
                new String[]{String.valueOf(data.get_id())});
        db.close();
    }
}
