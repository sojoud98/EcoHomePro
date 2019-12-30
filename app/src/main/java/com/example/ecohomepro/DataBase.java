package com.example.ecohomepro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "EcoHome.db";

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table admins " +
                "(id integer primary key AUTOINCREMENT," +
                "name text," +
                " phone text," +
                "password text )");
        db.execSQL("create table request " +
                "(id integer primary key AUTOINCREMENT," +
                "mobile text," +
                "service text," +
                "note text," +
                "info text,"+
                "address text," +
                "time text," +
                "served boolean DEFAULT 0," +
                "date text)");
        db.execSQL("create table customers " +
                "(id integer primary key AUTOINCREMENT," +
                "name text," +
                " phone text," +
                "password text," +
                "gender text)");
        db.execSQL("create table suggestions " +
                "(id integer primary key AUTOINCREMENT," +
                "suggestion text," +
                " sender text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
