package com.example.dell.judge.attendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.dell.judge.database.DatabaseHandler;

/**
 * Created by Raman on 14-09-2017.
 */

public class AttendenceManager {
    private DatabaseHandler dbHandler;

    private Context context;

    private SQLiteDatabase database;

    public AttendenceManager(Context context) {
        this.context = context;
    }

    public AttendenceManager open() throws SQLException {
        dbHandler = new DatabaseHandler(context);
        database = dbHandler.getWritableDatabase();
        return this;
    }
    public void close() {
        dbHandler.close();
    }

    public void insertStudents(String students) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHandler.KEY_STUDENT, students);
        database.insert(DatabaseHandler.STUDENT_TABLE_NAME, null, contentValue);
    }
    public Cursor fetchStudents() {
        String[] columns = new String[] { DatabaseHandler.KEY_ID, DatabaseHandler.KEY_STUDENT};
        Cursor cursor = database.query(DatabaseHandler.STUDENT_TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public void deleteStudents(){
        database.execSQL("delete from mobile_computing_students where 1");
    }
}
