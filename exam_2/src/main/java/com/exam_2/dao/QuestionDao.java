package com.exam_2.dao;


import com.exam_2.pojo.QuestionC;
import com.exam_2.pojo.QuestionJ;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionDao {
    //查询该老师所有课程的选择题
    List<QuestionC> selectAllQCByCourse(@Param("ids") int[] ids);
    List<QuestionC> selectChoiceByCourse(@Param("id") int id);

    List<QuestionJ> selectAllQJByCourse(@Param("ids") int[] ids);
    List<QuestionJ> selectJudgeByCourse(@Param("id") int id);

    List<QuestionJ> selectAllQAByCourse(@Param("ids") int[] ids);
    List<QuestionJ> selectAnswerByCourse(@Param("id") int id);

    int addChoice(QuestionC q);
    int addJudge(QuestionJ q);
    int addAnswer(QuestionJ q);


    int setPaperChoice(@Param("id") int id,@Param("examId") int examId);
    int setPaperJudge(@Param("id") int id,@Param("examId") int examId);
    int setPaperAnswer(@Param("id") int id,@Param("examId") int examId);

    List<QuestionC> selectChoiceByPaper(@Param("id") int id);
    List<QuestionJ> selectJudgeByPaper(@Param("id") int id);
    List<QuestionJ> selectAnswerByPaper(@Param("id") int id);

    void deletePaperQ(@Param("id") int id);

    int deleteQC(int id);
    int deleteQCRel(int id);
    int deleteQJ(int id);
    int deleteQJRel(int id);
    int deleteQA(int id);
    int deleteQARel(int id);






}
