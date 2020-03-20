package com.example.androidrealtimedatabase;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Student implements Serializable {

    private String studentID;
    private String name;
    private String gender;

    public Student() {

    }

    public Student(String studentID, String name, String gender) {
        this.studentID = studentID;
        this.name = name;
        this.gender = gender;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID='" + studentID + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
