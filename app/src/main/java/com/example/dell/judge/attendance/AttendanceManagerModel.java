package com.example.dell.judge.attendance;

/**
 * Created by dell on 9/14/2017.
 */
public class AttendanceManagerModel {
    private String studentName;

    public AttendanceManagerModel(String studentName) {
        this.studentName = studentName;
    }
    public String getstudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
