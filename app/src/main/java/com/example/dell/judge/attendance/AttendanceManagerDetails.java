package com.example.dell.judge.attendance;

import android.database.Cursor;
import com.example.dell.judge.MyApplication;
import java.util.ArrayList;

/**
 * Created by dell on 9/14/2017.
 */
public class AttendanceManagerDetails {
    public static ArrayList<AttendanceManagerModel> getList(){
        ArrayList<AttendanceManagerModel> al = new ArrayList<>();
        AttendenceManager ad= new AttendenceManager(MyApplication.getContext());
        ad.open();
        Cursor cr=ad.fetchStudents();
        if(cr.moveToFirst())
        {
            do{
                al.add(new AttendanceManagerModel(cr.getString(1)));
            }while(cr.moveToNext());
        }
        return al;
    }
}
