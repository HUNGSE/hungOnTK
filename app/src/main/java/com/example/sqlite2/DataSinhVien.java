package com.example.sqlite2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataSinhVien extends SQLiteOpenHelper {

    public DataSinhVien(@Nullable Context context, @Nullable String name,
                        @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE SinhVien (" +"id INTEGER PRIMARY KEY AUTOINCREMENT,"+"tenSV TEXT NOT NULL)";
        db.execSQL(sql);
    }
    public void addSinhVien(SinhVien sinhVien){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("tenSV",sinhVien.getTenSV());
        db.insert("SinhVien",null,values);
    }
    public List<SinhVien> getALl(){
        List<SinhVien>sinhViensList= new ArrayList<>();
        String sql="select *from SinhVien";
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                SinhVien sinhVien=new SinhVien();
                sinhVien.setId(cursor.getInt(0));
                sinhVien.setTenSV(cursor.getString(1));
                sinhViensList.add(sinhVien);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return sinhViensList;
    }
    public int removeSinhVien(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        return db.delete("SinhVien","Id=?",new String[]{String.valueOf(id)});
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
