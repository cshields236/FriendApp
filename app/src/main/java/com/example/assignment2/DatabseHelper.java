package com.example.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "FriendsDB";
    public static final String TABLE_NAME = "friend_table";


    public DatabseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT,EMAIL TEXT,PHONENUM INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String username, String email, int phoneNum) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME", username);
        contentValues.put("EMAIL", email);
        contentValues.put("PHONENUM", phoneNum);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }


    public List<Friend> searchByUsername(String username) {
        List<Friend> friends = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME + " where username" + " like ?", new String[]{"%" + username + "%"});
            if (cursor.moveToFirst()) {
                friends = new ArrayList<Friend>();
                do {
                    Friend f = new Friend();
                    f.setUsername(cursor.getString(1));
                    f.setEmail(cursor.getString(2));
                    f.setPhoneNum(cursor.getInt(3));

                    friends.add(f);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            friends = null;
        }
        return friends;
    }

    public List<Friend> searchByNumber(String number) {
        List<Friend> friends = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME + " where phoneNum" + " like ?", new String[]{"%" + number + "%"});
            if (cursor.moveToFirst()) {
                friends = new ArrayList<Friend>();
                do {
                    Friend f = new Friend();
                    f.setUsername(cursor.getString(1));
                    f.setEmail(cursor.getString(2));
                    f.setPhoneNum(cursor.getInt(3));

                    friends.add(f);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            friends = null;
        }
        return friends;
    }



    public boolean updateData(String username, String email, String phoneNum) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME", username);
        contentValues.put("EMAIL", email);
        contentValues.put("PHONENUM", phoneNum);
        db.update(TABLE_NAME, contentValues, "USERNAME = ?", new String[]{username});
        return true;
    }
}

