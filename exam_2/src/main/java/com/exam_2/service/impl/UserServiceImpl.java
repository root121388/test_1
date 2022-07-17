package com.exam_2.service.impl;

import com.exam_2.dao.CourseDao;
import com.exam_2.dao.RecordDao;
import com.exam_2.dao.UserDao;
import com.exam_2.pojo.Page;
import com.exam_2.pojo.User;
import com.exam_2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private CourseDao courseDao;
    private RecordDao recordDao;


    @Autowired
    public void setRecordDao(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    @Autowired
    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public Page pageCondition(User user, int page, int size, int role1) {
        //为模糊查询的条件加上%;
        if(user.getAccount() !=null && user.getAccount().length()>0){
            user.setAccount("%"+user.getAccount()+"%");
        }
        if(user.getName()!=null && user.getName().length()>0){
            user.setName("%"+user.getName()+"%");
        }
        if(user.getStuClass()!=null && user.getStuClass().length()>0){
            user.setStuClass("%"+user.getStuClass()+"%");
        }
        Page page1 = new Page();

        int start = (page-1)*size;
        int total = userDao.selectTotalCondition(user, role1);
        List<User> users = userDao.selectConditions(user, start, size, role1);
        page1.setTotalCount(total);
        page1.setRows(users);

        return page1;



    }

    @Override
    public Boolean addUser(User user) {

        System.out.println("service1");
        int i = userDao.addUserLogin(user);
        int i1 = userDao.addUserInfo(user);
        System.out.println("service2");
        if (i==1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public User login(User user) {
        User user1 = userDao.login(user);
        return user1;

    }

    @Override
    public Page page(int currentPage, int size,int role) {

        int start = (currentPage-1)*size;
        Page page = new Page();
        int total = userDao.selectTotal(role);
        List<User> users =userDao.selectByPage(start, size, role);
        page.setRows(users);
        page.setTotalCount(total);

        return page;
    }

    @Override
    public int deleteUser(User user) {
        //record 表中学生记录
        recordDao.deleteByUserId(user.getId());
        //学生课程表中删除记录
        courseDao.deleteRecordByUser(user.getId());
        //学生登录表
        userDao.deleteUserLogin(user);
        //学生身份信息表

        int i = userDao.deleteUserInfo(user);

        return i;
    }

    @Override
    public int deleteUsers(List<User> users) {
        //将ID数组提取出来
        String[] ids = new String[users.size()];
        for (int i = 0; i < users.size(); i++) {
            ids[i] = users.get(i).getAccount();
            recordDao.deleteByUserId(users.get(i).getId());
            courseDao.deleteRecordByUser(users.get(i).getId());
            userDao.deleteUserLogin(users.get(i));
            userDao.deleteUserInfo(users.get(i));
        }

        return 1;

    }


    @Override
    public Boolean updateUser(User user) {

        userDao.updateUserInfo(user);
        int i = userDao.updateUserLogin(user);

        if (i==1){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public List<User> selectByPFromRecord(int id) {
        return userDao.selectByPFromRecord(id);
    }

    @Override
    public List<User> selectStuByCourse(int id) {
        return userDao.selectStuByCourse(id);
    }

    @Override
    public List<User> selectNotInCourse(int id) {
        List<User> users = userDao.selectStuByCourse(id);
        System.out.println(users.size());
        if (users.size()>0){
            int[] ids = new int[users.size()];
            for (int i = 0; i < users.size(); i++) {
                ids[i]=users.get(i).getId();
            }
            return userDao.selectNotInCourse(ids);
        }else{
            return userDao.selectAllStu();
        }


    }

    @Override
    public User selectInfo(int id) {
        return userDao.selectInfo(id);
    }
}
