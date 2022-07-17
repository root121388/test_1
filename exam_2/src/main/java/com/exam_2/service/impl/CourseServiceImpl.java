package com.exam_2.service.impl;

import com.exam_2.dao.CourseDao;
import com.exam_2.dao.UserDao;
import com.exam_2.pojo.Course;
import com.exam_2.pojo.User;
import com.exam_2.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("courseService")
public class CourseServiceImpl implements CourseService {
    private CourseDao courseDao;
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {

        this.userDao = userDao;
    }

    @Autowired
    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public List<Course> selectCourseByUser(int userId) {

        List<Course> courses = courseDao.selectCourseByUserID(userId);


        return courses;
    }

    @Override
    public Course selectCourseByPaper(int paperId) {


        Course course = courseDao.selectCourseByPaperId(paperId);

        return course;
    }

    @Override
    public int addCourse(Course course, int id) {
        courseDao.addCourse(course);
        courseDao.addCourseUser(course,id);
        return 1;

    }

    @Override
    public int updateCourse(Course course) {
        courseDao.updateCourse(course);
        return 0;
    }

    @Override
    public int addStudent(List<User> students,int courseId) {

        for (int i = 0; i < students.size(); i++) {
            courseDao.addStudent(students.get(i).getId(),courseId);
        }
        return 1;
    }

    @Override
    public int deleteStuFromUC(int userId, int courseId) {
        return userDao.deleteStuFromUC(userId,courseId);
    }

    @Override
    public int deleteCourse(int courseId) {
        //删除相关试卷
        //试卷课程关系表
        //试卷用户关系表
        //试卷表
        courseDao.deletePC(courseId);
        courseDao.deleteUC(courseId);
        courseDao.deleteC(courseId);
        return 1;
    }


}
