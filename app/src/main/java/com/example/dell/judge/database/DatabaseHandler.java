package com.example.dell.judge.database;

/**
 * Created by dell on 9/13/2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "vktamta";
    public static final String TABLE_NAME = "schedule";
    public static final String KEY_ID= "id";
    public static final String KEY_TIME= "time";
    public static final String KEY_MONDAY = "Monday";
    public static final String KEY_TUESDAY= "Tuesday";
    public static final String KEY_WEDNESDAY="Wednesday";
    public static final String KEY_THURSDAY="Thursday";
    public static final String KEY_FRIDAY="Friday";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE ="CREATE TABLE if not exists "+TABLE_NAME+"("+KEY_ID+" integer primary key autoincrement, "+KEY_TIME+" TIME, "+KEY_MONDAY+" text, "+KEY_TUESDAY+" text, "+KEY_WEDNESDAY+" text, "+KEY_THURSDAY+" text,"+KEY_FRIDAY+" text);";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}