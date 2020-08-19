package com.example.bamtech;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String Create_Events_Table ="CREATE TABLE "+DBStructure.Event_Table_Name+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            +DBStructure.Event+" TEXT, " +DBStructure.Time+" TEXT, "+DBStructure.Date+" TEXT, "+DBStructure.Month+" TEXT, "
            +DBStructure.Year+" TEXT) ";
    private  static  final String Drop_Events_Table ="DROP TABLE IF EXISTS "+DBStructure.Event_Table_Name;

    public DBOpenHelper(@Nullable Context context) {
        super(context, DBStructure.DB_Name, null , DBStructure.DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_Events_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL(Drop_Events_Table);
        onCreate(db);
    }

    public void  SaveEvent (String event, String time, String date, String month, String year, SQLiteDatabase database) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBStructure.Event, event);
        contentValues.put(DBStructure.Time, time);
        contentValues.put(DBStructure.Date, date);
        contentValues.put(DBStructure.Month, month);
        contentValues.put(DBStructure.Year, year);
        database.insert(DBStructure.Event_Table_Name, null, contentValues );
    }

    public Cursor ReadEvents (String date, SQLiteDatabase database){
        String [] Projections = {DBStructure.Event, DBStructure.Time, DBStructure.Date, DBStructure.Month, DBStructure.Year};
        String Selection = DBStructure.Date + "=?";
        String [] SelectionArgs = {date};
        return database.query(DBStructure.Event_Table_Name, Projections, Selection, SelectionArgs,null, null, null);
    }

    public Cursor ReadEventsperMonth (String month, String year, SQLiteDatabase database){
        String [] Projections = {DBStructure.Event, DBStructure.Time, DBStructure.Date, DBStructure.Month, DBStructure.Year};
        String Selection = DBStructure.Month + "=? and "+DBStructure.Year+"=?";
        String [] SelectionArgs = {month,year};
        return database.query(DBStructure.Event_Table_Name, Projections, Selection, SelectionArgs,null, null, null);
    }

}
