package com.exam_2.dao;


import com.exam_2.pojo.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseDao {
    List<Course> selectCourseByUserID(@Param("id")int id);
    Course selectCourseByPaperId(@Param("id")int id);
    int deleteRecordByUser(int userId);

    int deletePC(int courseId);
    int deleteUC(int courseId);
    int deleteC(int courseId);
    int addCourse(Course course);
    int addCourseUser(@Param("course")Course course,@Param("id")int id);
    int updateCourse(Course course);

    int addStudent(@Param("stuId") int id,@Param("courseId") int courseId);
}
