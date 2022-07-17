package com.exam_2.controller;

import com.exam_2.pojo.Course;
import com.exam_2.pojo.User;
import com.exam_2.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping("/selectByUser")
    @ResponseBody
    public List<Course> selectByUserId(int userId){
        List<Course> courses = courseService.selectCourseByUser(userId);
        return courses;
    }

    @RequestMapping("/addCourse")
    @ResponseBody
    public int addCourse(@RequestBody Course courseA, int userId){
        courseService.addCourse(courseA,userId);
        return 1;
    }
    @RequestMapping("/update")
    @ResponseBody
    public int update(@RequestBody Course courseE){
        courseService.updateCourse(courseE);
        return 1;
    }
    @RequestMapping("/addStu")
    @ResponseBody
    public int addStu(@RequestBody List<User> users, int courseId){
        courseService.addStudent(users,courseId);
        return 1;
    }

    @RequestMapping("/deleteStu")
    @ResponseBody
    public int deleteStu( int userId,int courseId){

        courseService.deleteStuFromUC(userId,courseId);
        return 1;
    }
    @RequestMapping("/deleteCourse")
    @ResponseBody
    public int deleteCourse(int courseId){

        courseService.deleteCourse(courseId);
        return 1;
    }


}
