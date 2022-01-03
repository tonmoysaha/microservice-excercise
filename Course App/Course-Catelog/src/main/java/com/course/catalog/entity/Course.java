package com.course.catalog.entity;

public class Course {

    private Long id;
    private String courseName;
    private String authorName;


    public Course() {
    }

    public Course(String courseName, String authorName) {
        this.courseName = courseName;
        this.authorName = authorName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}

