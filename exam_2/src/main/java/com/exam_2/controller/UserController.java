package com.exam_2.controller;



import com.exam_2.pojo.Page;
import com.exam_2.pojo.User;
import com.exam_2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    @ResponseBody
    public User login(@RequestBody User user) {


        System.out.println(user);
        User user1 = userService.login(user);

        System.out.println("login");
        System.out.println(user1);
        return user1;

    }

    @RequestMapping("/addUser")
    @ResponseBody
    public User addUser(@RequestBody User userAdd){
        System.out.println(userAdd);
        Boolean aBoolean = userService.addUser(userAdd);
        return userAdd;
    }
    @RequestMapping("/login1")
    @ResponseBody
    public User login1(@RequestBody List<User> users) {


        System.out.println(users.get(0));
        return users.get(1);

    }

    @RequestMapping("/page")
    @ResponseBody
    public Page page(int currentPage,int size,int role){

        Page page = userService.page(currentPage, size, role);

        return page;

    }
    @RequestMapping("/condition")
    @ResponseBody
    public Page condition(@RequestBody User userSelect,int currentPage,int size,int role){
        Page page = userService.pageCondition(userSelect, currentPage, size, role);
        return page;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public int delete(@RequestBody  User user){
        int i = userService.deleteUser(user);
        return i;
    }

    @RequestMapping("/deleteMulti")
    @ResponseBody
    public int deleteMulti(@RequestBody  List<User> users){
        int i = userService.deleteUsers(users);
        return i;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Boolean edit(@RequestBody  User userEdit){
        Boolean aBoolean = userService.updateUser(userEdit);
        return aBoolean;
    }

    @RequestMapping("/selectStuByCourse")
    @ResponseBody
    public List<User> selectStuByC(int courseId){
       return userService.selectStuByCourse(courseId);
    }

    @RequestMapping("/selectNotInCourse")
    @ResponseBody
    public List<User> selectNotInCourse(int courseId){
        return userService.selectNotInCourse(courseId);
    }

    @RequestMapping("/selectInfo")
    @ResponseBody
    public User selectInfo(int id){
        return userService.selectInfo(id);
    }


    @RequestMapping("/selectByPFromRecord")
    @ResponseBody
    public List<User> selectByPFromRecord(int paperId){
        return userService.selectByPFromRecord(paperId);
    }

}
