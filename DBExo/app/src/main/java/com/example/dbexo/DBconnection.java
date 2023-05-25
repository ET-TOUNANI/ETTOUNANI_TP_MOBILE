package com.example.dbexo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBconnection extends SQLiteOpenHelper {

    public DBconnection(Context context) {
        super(context, "Personnes.db", null, 1);
    }

    public void insertNewAdmin(String name){
        SQLiteDatabase wDB = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("Name", name);
        wDB.insert("Admin",null,contentValue);

    }
    public void deleteRow(Integer id){
        SQLiteDatabase wDB = this.getWritableDatabase();
        String[] s = new String[]{String.valueOf(id)};
        wDB.delete("Admin","ID=?",s);

    }
    public void updateRow(String name, Integer id){
        SQLiteDatabase wDB = this.getWritableDatabase();
        wDB.execSQL("update Admin set Name='"+name+"' where ID="+String.valueOf(id));

    }
    public ArrayList getAllRecord(){
        ArrayList maliste = new ArrayList();
        SQLiteDatabase rDB = this.getReadableDatabase();
        Cursor res = rDB.rawQuery("Select * FROM Admin",null);
        res.moveToFirst();
        while(res.isAfterLast()== false){
            maliste.add(res.getString(res.getColumnIndex("ID"))+ ": " +  res.getString(res.getColumnIndex("Name")));
            res.moveToNext();
        }
        return maliste;

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Admin (ID INTEGER PRIMARY KEY, Name TEXT);");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
       // db.execSQL("DROP TABLE IF EXISTS Admin");
        //onCreate(db);
    }
}


