package com.example.dell.judge.schedule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.dell.judge.database.DatabaseHandler;

/**
 * Created by dell on 9/13/2017.
 */
public class ScheduleManager {
    private DatabaseHandler dbHandler;

    private Context context;

    private SQLiteDatabase database;

    public ScheduleManager(Context context) {
        this.context = context;
    }

    public ScheduleManager open() throws SQLException {
        dbHandler = new DatabaseHandler(context);
        database = dbHandler.getWritableDatabase();
        return this;
    }
    public void close() {
        dbHandler.close();
    }

    public void insertSchedule(String time,String monday,String tuesday, String wednesday, String thursday,String friday) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHandler.KEY_TIME, time);
        contentValue.put(DatabaseHandler.KEY_MONDAY,monday);
        contentValue.put(DatabaseHandler.KEY_TUESDAY, tuesday);
        contentValue.put(DatabaseHandler.KEY_WEDNESDAY,wednesday);
        contentValue.put(DatabaseHandler.KEY_THURSDAY,thursday);
        contentValue.put(DatabaseHandler.KEY_FRIDAY,friday);
        database.insert(DatabaseHandler.TABLE_NAME, null, contentValue);
    }
    public Cursor fetchSchedule() {
        String[] columns = new String[] { DatabaseHandler.KEY_ID, DatabaseHandler.KEY_TIME,DatabaseHandler.KEY_MONDAY,DatabaseHandler.KEY_TUESDAY, DatabaseHandler.KEY_WEDNESDAY,DatabaseHandler.KEY_THURSDAY,DatabaseHandler.KEY_FRIDAY };
        Cursor cursor = database.query(DatabaseHandler.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public void deleteSchedule(){
        database.execSQL("delete from schedule where 1");
    }
}
