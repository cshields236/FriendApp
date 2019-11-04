package com.example.assignment2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Friends.db";
    public static final String TABLE_NAME = "friend_table";


    public DatabseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      String q = String.format("create table %s (username TEXT PRIMARY KEY, email TEXT, phoneNum INTEGER)", TABLE_NAME);
       db.execSQL(q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(String.format("Drop TABLE IF EXISTS %s", TABLE_NAME));
            onCreate(db);
    }
}
