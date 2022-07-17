package com.exam_2.dao;

import com.exam_2.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    // 查询用户
    User login(User user);


    //分页查询
    List<User> selectByPage(@Param("start") int start, @Param("size")int size, @Param("role")int role);
    //user 总数
    int selectTotal(@Param("role")int role);

    List<User> selectConditions(@Param("user") User user,@Param("start")int start,@Param("size")int size,@Param("role1")int role1);
    int selectTotalCondition(@Param("user")User user,@Param("role1")int role1);
    List<User> selectStuByCourse(@Param("id") int courseId);

    List<User> selectNotInCourse(@Param("ids") int[] ids);
    List<User> selectAllStu();
    User selectInfo(@Param("id") int id);
    List<User> selectByPFromRecord(int paperId);


    int deleteUserLogin(User user);
    int deleteUserInfo(User user);
    int deleteUsers(@Param("ids")String[] ids);
    int deleteStuFromUC(@Param("userId")int userId,@Param("courseId") int courseId);

    int updateUserLogin(User user);
    int updateUserInfo(User user);

    int addUserLogin(User user);
    int addUserInfo(User user);

}
