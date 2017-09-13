package com.example.dell.judge.schedule;

/**
 * Created by dell on 9/6/2017.
 */
public class ScheduleVariable {
    static String clickDay;
    public  void setVar(String var){
        clickDay=var;
    }
    public static String getVar(){
        return clickDay;
    }
}
