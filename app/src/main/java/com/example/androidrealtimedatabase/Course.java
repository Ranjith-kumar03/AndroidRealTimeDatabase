package com.example.androidrealtimedatabase;

import java.io.Serializable;

public class Course implements Serializable {
    private String courseid;
    private String coursename;
    private int courserating;

    public Course() {

    }

    public Course(String courseid, String coursename, int courserating) {
        this.courseid = courseid;
        this.coursename = coursename;
        this.courserating = courserating;
    }

    public String getCourseid() {
        return courseid;
    }

    public String getCoursename() {
        return coursename;
    }

    public int getCourserating() {
        return courserating;
    }
}
