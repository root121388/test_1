package com.exam_2.service;



import com.exam_2.pojo.Course;
import com.exam_2.pojo.User;

import java.util.List;

public interface CourseService {
    public List<Course> selectCourseByUser(int userId);
    public Course selectCourseByPaper(int paperId);

    public int addCourse(Course course,int id);
    public int updateCourse(Course course);

    public int addStudent(List<User> students,int courseId);
    int deleteStuFromUC(int userId,int courseId);
    int deleteCourse(int courseId);

}
