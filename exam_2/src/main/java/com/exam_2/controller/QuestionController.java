package com.exam_2.controller;

import com.exam_2.pojo.QuestionC;
import com.exam_2.pojo.QuestionJ;
import com.exam_2.pojo.Questions;
import com.exam_2.service.CourseService;
import com.exam_2.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {
    private QuestionService questionService;
    private CourseService courseService;

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping("/selectCByUser")
    @ResponseBody
    public List<QuestionC> selectCByUser(int userId) {
        List<QuestionC> questionCS = questionService.selectAllQCByCourseID(userId);
        return questionCS;
    }

    @RequestMapping("/selectJByUser")
    @ResponseBody
    public List<QuestionJ> selectJByUser(int userId) {
        List<QuestionJ> questionJS = questionService.selectAllQJByCourseID(userId);
        return questionJS;
    }

    @RequestMapping("/selectAByUser")
    @ResponseBody
    public List<QuestionJ> selectAByUser(int userId) {
        List<QuestionJ> questionAS = questionService.selectAllQAByCourseID(userId);
        return questionAS;
    }


    @RequestMapping("/selectCByCourse")
    @ResponseBody
    public List<QuestionC> selectCByCourse(int courseId) {
        List<QuestionC> questionCS = questionService.selectChoiceByCourse(courseId);

        return questionCS;
    }

    @RequestMapping("/selectAllByPaper")
    @ResponseBody
    public Questions selectAllByPaper(int paperId) {
        Integer courseId = courseService.selectCourseByPaper(paperId).getCourseId();
        List<QuestionC> questionCS = questionService.selectChoiceByCourse(courseId);
        List<QuestionJ> questionJS = questionService.selectJudgeByCourse(courseId);
        List<QuestionJ> questionAS = questionService.selectAnswerByCourse(courseId);

        Questions questions = new Questions();
        questions.setChoices(questionCS);
        questions.setJudges(questionJS);
        questions.setAnswers(questionAS);
        return questions;
    }

    @RequestMapping("/setPaper")
    @ResponseBody
    public void setPaper(@RequestBody Questions paper, int paperId){
        questionService.setPaperQuestion(paper,paperId);

    }
    @RequestMapping("/selectByPaper")
    @ResponseBody
    public Questions selectByPaper(int paperId){
        Questions questions = questionService.selectQuestionByPaper(paperId);
        return questions;
    }
    @RequestMapping("/gradeAnalyse")
    @ResponseBody
    public Questions gradeAnalyse(int paperId){

        return questionService.gradeAnalyse(paperId);
    }

    @RequestMapping("/addChoice")
    @ResponseBody
    public int addChoice(@RequestBody QuestionC questionC){
        System.out.println(questionC);
        return questionService.addChoice(questionC);
    }
    @RequestMapping("/addJudge")
    @ResponseBody
    public int addJudge(@RequestBody QuestionJ questionJ){
        System.out.println(questionJ);
        return questionService.addJudge(questionJ);
    }

    @RequestMapping("/addAnswer")
    @ResponseBody
    public int addAnswer(@RequestBody QuestionJ questionJ){
        System.out.println(questionJ);
        return questionService.addAnswer(questionJ);
    }

    @RequestMapping("/autoGrade")
    @ResponseBody
    public int autoGrade(int userId, int paperId){

         questionService.autoGrade(userId,paperId);
         return 1;
    }
    @RequestMapping("/checkStuAnswer")
    @ResponseBody
    public Questions checkStuAnswer(int userId, int paperId){

        return questionService.checkStuAnswer(userId,paperId);
    }
    @RequestMapping("/gradePaper")
    @ResponseBody
    public int gradePaper(@RequestBody Questions questions, int userId, int paperId){
        return questionService.gradePaper(questions,userId,paperId);

    }
    @RequestMapping("/deleteQC")
    @ResponseBody
    public int deleteQC(int id){
        questionService.deleteQC(id);

    return 1;
    }
    @RequestMapping("/deleteQJ")
    @ResponseBody
    public int deleteQJ(int id){

        questionService.deleteQJ(id);
        return 1;
    }
    @RequestMapping("/deleteQA")
    @ResponseBody
    public int deleteQA(int id){

        questionService.deleteQA(id);
        return 1;
    }



}
