package com.example.dell.judge.schedule;

import java.util.ArrayList;

/**
 * Created by dell on 9/6/2017.
 */
public class Schedule_Menu_Details {
    public static ArrayList<Schedule_Menu_Model> getList(){
        ArrayList<Schedule_Menu_Model> al = new ArrayList<>();
        al.add(new Schedule_Menu_Model("Monday"));
        al.add(new Schedule_Menu_Model("Tuesday"));
        al.add(new Schedule_Menu_Model("Wednesday"));
        al.add(new Schedule_Menu_Model("Thursday"));
        al.add(new Schedule_Menu_Model("Friday"));
        return al;
    }
}
