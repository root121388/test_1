package com.exam_2.pojo;

import org.springframework.stereotype.Component;

@Component("course")
public class Course {
    private Integer courseId;
    private String courseName;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
