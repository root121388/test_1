package com.exam_2.controller;


import com.exam_2.dao.PaperDao;
import com.exam_2.pojo.Paper;
import com.exam_2.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/paper")
public class PaperController {
    private PaperService paperService;

    @Autowired
    public void setPaperService(PaperService paperService) {
        this.paperService = paperService;
    }

    @RequestMapping("/select")
    @ResponseBody
    public Paper selectPaper(int paperId){


        return paperService.selectPaper(paperId);
    }

    @RequestMapping("/check")
    @ResponseBody
    public List<Paper> checkByUser(int userId){
        System.out.println("paper check");

        List<Paper> papers = paperService.selectAllByUserId(userId);
        return papers;
    }
    @RequestMapping("/check2")
    @ResponseBody
    public List<Paper> checkByUser2(int userId){
        System.out.println("paper check");

        List<Paper> papers = paperService.selectAllByUserId(userId);
        return papers;
    }
    @RequestMapping("/checkStu")
    @ResponseBody
    public List<Paper> checkByStu(int userId){

        List<Paper> papers = paperService.selectByStu(userId);
        return papers;
    }

    @RequestMapping("/checkByT")
    @ResponseBody
    public List<Paper> checkByT(int userId){


        List<Paper> papers = paperService.selectAllByT(userId);
        return papers;
    }

    @RequestMapping("/selectByCourse")
    @ResponseBody
    public List<Paper> selectByCourse(int courseId){

        List<Paper> papers = paperService.selectByCourseId(courseId);

        return papers;
    }
    //计算总分
    @RequestMapping("/selectByC")
    @ResponseBody
    public List<Paper> selectByC(int courseId){

        List<Paper> papers = paperService.selectByC(courseId);

        return papers;
    }
    @RequestMapping("/addPaper")
    @ResponseBody
    public int addPaper(@RequestBody Paper paper){

        System.out.println(paper);
        paperService.addPaper(paper);

        return 1;
    }
    @RequestMapping("/checkByUAndC")
    @ResponseBody
    public List<Paper> checkByUAndC(int userId,int courseId){


        return paperService.checkByUAndC(userId,courseId);
    }
    @RequestMapping("/update")
    @ResponseBody
    public int update(@RequestBody Paper paper){

        System.out.println("paperUpdate");
        System.out.println(paper);
        return paperService.update(paper);
    }
    @RequestMapping("/updateState")
    @ResponseBody
    public int updateState(int state,int paperId){

       return paperService.updateState(state,paperId);
    }
    @RequestMapping("/delete")
    @ResponseBody
    public int delete(int paperId){

        return paperService.deletePaper(paperId);
    }
    @RequestMapping("/publish")
    @ResponseBody
    public int publish(int id){

        return paperService.publish(id);
    }
    @RequestMapping("/undoPublish")
    @ResponseBody
    public int undoPublish(int id){

        return paperService.undoPublish(id);
    }


}
