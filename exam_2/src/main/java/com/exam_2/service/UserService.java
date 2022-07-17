package com.exam_2.service;

import com.exam_2.pojo.Page;
import com.exam_2.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    Page pageCondition(User user, int page, int size, int role1);
    Boolean addUser(User user);
    User login(User user);
    Page page(int page, int size,int role);
    int deleteUser(User user);
    int deleteUsers(List<User> users);

    Boolean updateUser(User user);

    //查询完成该试卷的学生
    List<User> selectByPFromRecord(int id);
    List<User> selectStuByCourse(int id);
    public List<User> selectNotInCourse(int id);
    User selectInfo(int id);

}
