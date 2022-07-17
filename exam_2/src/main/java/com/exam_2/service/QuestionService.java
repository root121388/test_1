package com.exam_2.service;



import com.exam_2.pojo.QuestionC;
import com.exam_2.pojo.QuestionJ;
import com.exam_2.pojo.Questions;

import java.util.List;

public interface QuestionService {
    public List<QuestionC> selectAllQCByCourseID(int userId);
    public List<QuestionC> selectChoiceByCourse(int courseId);

    public List<QuestionJ> selectAllQJByCourseID(int userId);
    public List<QuestionJ> selectJudgeByCourse(int courseId);

    public List<QuestionJ> selectAllQAByCourseID(int userId);
    public List<QuestionJ> selectAnswerByCourse(int courseId);

    public void setPaperQuestion(Questions questions, int paperId);

    public Questions selectQuestionByPaper(int paperId);
    public Questions gradeAnalyse(int paperId);

    int addChoice(QuestionC q);
    int addJudge(QuestionJ q);
    int addAnswer(QuestionJ q);

    //对选择题和判断题自动判断
    void autoGrade(int userId,int paperId);
    int gradePaper(Questions questions, int userId, int paperId);
    //学生答题记录
    Questions checkStuAnswer(int userId,int paperId);

    int deleteQC(int  id);
    int deleteQJ(int  id);
    int deleteQA(int  id);


}
