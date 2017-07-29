package com.example.shenjun.lab04;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shenjun on 6/24/2017.
 */

//https://www.youtube.com/watch?v=p8TaTgr4uKM(Android SQLite Database Tutorial 2 # Introduction + Creating Database and Tables (Part 2))
public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Database.db";
    public static final String TABLE_NAME = "Table";
    public static final String COL1 = "ID";
    public static final String COL2 = "FirstName";
    public static final String COL3 = "LastName";
    public static final String COL4 = "Marks";
    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table" + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,FirstName TEXT, LastName TEXT, Marks INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    //
    public boolean addData(String firstname, String lastname, String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,firstname);
        contentValues.put(COL3,lastname);
        contentValues.put(COL4,marks);
        long result = db.insert(TABLE_NAME, null,contentValues );
        if (result == -1)
            return false;
        else
            return true;
    }

    //
    public Cursor displayData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from" + TABLE_NAME,null);
        return result;
    }

    //
    public Integer deleteDate(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?", new String[]{id});
    }

    public boolean updateData(String id, String firstname, String lastname, String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,id);
        contentValues.put(COL2,firstname);
        contentValues.put(COL3,lastname);
        contentValues.put(COL4,marks);
        db.update(TABLE_NAME,contentValues,"ID = ?",new String[]{ id });
        return true;
    }
}
