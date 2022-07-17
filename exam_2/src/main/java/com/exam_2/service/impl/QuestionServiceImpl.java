package com.exam_2.service.impl;

import com.exam_2.dao.*;
import com.exam_2.pojo.*;
import com.exam_2.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

    private CourseDao courseDao;
    private QuestionDao questionDao;
    private RecordDao recordDao;
    private PaperDao paperDao;
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setPaperDao(PaperDao paperDao) {
        this.paperDao = paperDao;
    }

    @Autowired
    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Autowired
    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Autowired
    public void setRecordDao(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    @Override
    public List<QuestionC> selectAllQCByCourseID(int userId) {


        List<Course> courses = courseDao.selectCourseByUserID(userId);
        int[] ids = new int[courses.size()];
        for (int i = 0; i < courses.size(); i++) {
            ids[i] = courses.get(i).getCourseId();
        }
        List<QuestionC> questionCS = questionDao.selectAllQCByCourse(ids);


        return questionCS;
    }

    @Override
    public List<QuestionC> selectChoiceByCourse(int courseId) {

        List<QuestionC> questionCS = questionDao.selectChoiceByCourse(courseId);

        return questionCS;
    }

    @Override
    public List<QuestionJ> selectAllQJByCourseID(int userId) {

        List<Course> courses = courseDao.selectCourseByUserID(userId);
        int[] ids = new int[courses.size()];
        for (int i = 0; i < courses.size(); i++) {
            ids[i] = courses.get(i).getCourseId();
        }
        List<QuestionJ> questionJS = questionDao.selectAllQJByCourse(ids);

        return questionJS;
    }

    @Override
    public List<QuestionJ> selectJudgeByCourse(int courseId) {

        List<QuestionJ> questionJS = questionDao.selectJudgeByCourse(courseId);

        return questionJS;
    }

    @Override
    public List<QuestionJ> selectAllQAByCourseID(int userId) {

        List<Course> courses = courseDao.selectCourseByUserID(userId);
        int[] ids = new int[courses.size()];
        for (int i = 0; i < courses.size(); i++) {
            ids[i] = courses.get(i).getCourseId();
        }
        List<QuestionJ> questionAS = questionDao.selectAllQAByCourse(ids);

        return questionAS;
    }

    @Override
    public List<QuestionJ> selectAnswerByCourse(int courseId) {

        List<QuestionJ> questionAS = questionDao.selectAnswerByCourse(courseId);

        return questionAS;
    }

    @Override
    public void setPaperQuestion(Questions questions, int paperId) {

        //分别获取生成试卷的选择判断简答题目
        List<QuestionC> choices = questions.getChoices();
        List<QuestionJ> judges = questions.getJudges();
        List<QuestionJ> answers = questions.getAnswers();
        //生成试卷记录表的名称
        String choiceRecord = "record_c_" + paperId;
        String judgeRecord = "record_j_" + paperId;
        String answerRecord = "record_a_" + paperId;

        //若记录表中有数据 删除
        if (recordDao.isRecordExist(choiceRecord) > 0) {
            recordDao.truncateRecord(choiceRecord);
        }
        if (recordDao.isRecordExist(judgeRecord) > 0) {
            recordDao.truncateRecord(judgeRecord);
        }
        if (recordDao.isRecordExist(answerRecord) > 0) {
            recordDao.truncateRecord(judgeRecord);
        }
        System.out.println(choiceRecord);
        //题目试卷关系表的清除
        questionDao.deletePaperQ(paperId);
        //向记录表中添加题目ID  正确答案？
        //向题目试卷关系表添加rel
        if (choices.size() > 0) {
            for (int i = 0; i < choices.size(); i++) {
                recordDao.addRecord(choices.get(i).getId(), choiceRecord);
                questionDao.setPaperChoice(choices.get(i).getId(), paperId);
            }
        }
        if (judges.size() > 0) {
            for (int i = 0; i < judges.size(); i++) {
                recordDao.addRecord(judges.get(i).getId(), judgeRecord);
                questionDao.setPaperJudge(judges.get(i).getId(), paperId);
            }
        }
        if (answers.size() > 0) {
            for (int i = 0; i < answers.size(); i++) {
                recordDao.addRecord(answers.get(i).getId(), answerRecord);
                questionDao.setPaperAnswer(answers.get(i).getId(), paperId);
            }
        }

    }

    @Override
    public Questions selectQuestionByPaper(int paperId) {

        List<QuestionC> questionCS = questionDao.selectChoiceByPaper(paperId);
        List<QuestionJ> questionJS = questionDao.selectJudgeByPaper(paperId);
        List<QuestionJ> questionAS = questionDao.selectAnswerByPaper(paperId);
        Questions questions = new Questions(questionCS, questionJS, questionAS);

        return questions;
    }

    @Override
    public Questions gradeAnalyse(int paperId) {
        Questions questions = selectQuestionByPaper(paperId);
        //生成试卷记录表的名称
        String choiceRecord = "record_c_" + paperId;
        String judgeRecord = "record_j_" + paperId;
        String answerRecord = "record_a_" + paperId;
        //分母
        int courseId = paperDao.selectPaperCourse(paperId);
        List<User> users = userDao.selectStuByCourse(courseId);
        double total = users.size();
        System.out.println(total);

        //完成试卷的学生Id
        List<Integer> ids = recordDao.selectStu(paperId);

        List<QuestionJ> answers = questions.getAnswers();
        List<QuestionC> choices = questions.getChoices();
        List<QuestionJ> judges = questions.getJudges();
        DecimalFormat df = new DecimalFormat("#.00");

        if (choices.size() > 0) {
            for (int i = 0; i < choices.size(); i++) {
                double count = 0;
                for (int j = 0; j < ids.size(); j++) {
                    String scoreColumn = "score_" + ids.get(j);
                    //客观题求得正确率
                    if (recordDao.selectS(scoreColumn, choiceRecord, choices.get(i).getId()) > 0) {
                        count++;
                    }
                    //count += recordDao.selectS(scoreColumn, choiceRecord, choices.get(i).getId());
                }
//                System.out.println("1");
                double avg = 0;
                if (count != 0) {
                    avg = (int)((count / total)*100);
                }
                questions.getChoices().get(i).setAvg(avg);
            }
        }
        if (judges.size() > 0) {
            for (int i = 0; i < judges.size(); i++) {
                double count = 0;
                for (int j = 0; j < ids.size(); j++) {

                    String scoreColumn = "score_" + ids.get(j);
                    System.out.println(recordDao.selectS(scoreColumn, judgeRecord, judges.get(i).getId()));
                    //客观题求得正确率
                    if(recordDao.selectS(scoreColumn, judgeRecord, judges.get(i).getId())>0){
                        count++;
                    }
                    //count += recordDao.selectS(scoreColumn, choiceRecord, choices.get(i).getId());
                }
                System.out.println("count=" + count);
                double avg = 0;
                if (count != 0) {
                    avg = avg = (int)((count / total)*100);
                }
                questions.getJudges().get(i).setAvg(avg);
            }
        }
        if (answers.size() > 0) {
            for (int i = 0; i < answers.size(); i++) {
                double count = 0;
                for (int j = 0; j < ids.size(); j++) {
                    String scoreColumn = "score_" + ids.get(j);
                    //主观题求平均分
                    count += recordDao.selectS(scoreColumn, answerRecord, answers.get(i).getId());
                }
                double avg = 0;
                if (count != 0) {
                    avg= Double.parseDouble(df.format(count / total));
                }
                questions.getAnswers().get(i).setAvg(avg);
            }
        }

        return questions;
    }

    @Override
    public int addChoice(QuestionC q) {

        return questionDao.addChoice(q);
    }

    @Override
    public int addJudge(QuestionJ q) {

        return questionDao.addJudge(q);
    }

    @Override
    public int addAnswer(QuestionJ q) {
        return questionDao.addAnswer(q);
    }

    @Override
    public void autoGrade(int userId, int paperId) {

        //生成试卷记录表的名称
        String choiceRecord = "record_c_" + paperId;
        String judgeRecord = "record_j_" + paperId;


        //要添加的两列列名
        String answerColumn = "answer_" + userId;
        String scoreColumn = "score_" + userId;

        //获取该试卷的正确答案
        List<QuestionC> questionCS = questionDao.selectChoiceByPaper(paperId);
        List<QuestionJ> questionJS = questionDao.selectJudgeByPaper(paperId);

        //获取学生的答案
        List<String> choiceAnswers = recordDao.selectAnswer(answerColumn, choiceRecord);
        List<String> judgeAnswers = recordDao.selectAnswer(answerColumn, judgeRecord);


        //学生的各道题目的分数
        int[] choiceScores = new int[choiceAnswers.size()];
        int[] judgeScores = new int[judgeAnswers.size()];

        //选择题批阅
        if (questionCS.size() > 0) {
            for (int i = 0; i < questionCS.size(); i++) {
                System.out.println(questionCS.get(i).getRightAnswer() + "  " + choiceAnswers.get(i));
                if (questionCS.get(i).getRightAnswer().equals(choiceAnswers.get(i))) {
                    choiceScores[i] = 2;
                } else {
                    choiceScores[i] = 0;
                }
            }
        }
        //填空题批阅
        if (questionJS.size() > 0) {
            for (int i = 0; i < questionJS.size(); i++) {
                if (questionJS.get(i).getRightAnswer().equals(judgeAnswers.get(i))) {
                    judgeScores[i] = 2;
                } else {
                    judgeScores[i] = 0;
                }
            }
        }


        //录入答案
        for (int i = 0; i < questionCS.size(); i++) {
            recordDao.setScore(scoreColumn, choiceRecord, choiceScores[i], questionCS.get(i).getId());
            System.out.println(questionCS.get(i).getRightAnswer() + "  " + choiceAnswers.get(i) + " " + choiceScores[i]);
        }


        for (int i = 0; i < questionJS.size(); i++) {
            recordDao.setScore(scoreColumn, judgeRecord, judgeScores[i], questionJS.get(i).getId());
            System.out.println(questionJS.get(i).getRightAnswer() + "  " + judgeAnswers.get(i) + " " + judgeScores[i]);
        }
    }

    @Override
    public int gradePaper(Questions questions, int userId, int paperId) {
        //分别获取生成试卷的选择判断简答题目
        List<QuestionC> choices = questions.getChoices();
        List<QuestionJ> judges = questions.getJudges();
        List<QuestionJ> answers = questions.getAnswers();

        //生成试卷记录表的名称
        String answerRecord = "record_a_" + paperId;
        //要添加的两列列名
        String answerColumn = "answer_" + userId;
        String scoreColumn = "score_" + userId;
        //录入学生简答题的成绩
        for (int i = 0; i < answers.size(); i++) {
            recordDao.setScore(scoreColumn, answerRecord, answers.get(i).getScore(), answers.get(i).getId());
        }
        //计算学生总分
        int grade = 0;
        for (int i = 0; i < choices.size(); i++) {
            grade += choices.get(i).getScore();
        }
        for (int i = 0; i < judges.size(); i++) {
            grade += judges.get(i).getScore();
        }
        for (int i = 0; i < answers.size(); i++) {
            grade += answers.get(i).getScore();
        }

        //录入学生总分 顺便将记录表的状态设置为3
        recordDao.updateCount(paperId, userId, grade);


        return 0;
    }

    @Override
    public Questions checkStuAnswer(int userId, int paperId) {
        //获取该试卷的所有题目
        List<QuestionC> questionCS = questionDao.selectChoiceByPaper(paperId);
        List<QuestionJ> questionJS = questionDao.selectJudgeByPaper(paperId);
        List<QuestionJ> questionAS = questionDao.selectAnswerByPaper(paperId);
        //生成试卷记录表的名称
        String choiceRecord = "record_c_" + paperId;
        String judgeRecord = "record_j_" + paperId;
        String answerRecord = "record_a_" + paperId;
        //要添加的两列列名
        String answerColumn = "answer_" + userId;
        String scoreColumn = "score_" + userId;
        //获取学生的答案
        List<String> choiceAnswers = recordDao.selectAnswer(answerColumn, choiceRecord);
        List<String> judgeAnswers = recordDao.selectAnswer(answerColumn, judgeRecord);
        List<String> answerAnswers = recordDao.selectAnswer(answerColumn, answerRecord);
        //获取学生试题的成绩
        List<Integer> choiceScore = recordDao.selectScore(scoreColumn, choiceRecord);
        List<Integer> judgeScore = recordDao.selectScore(scoreColumn, judgeRecord);
        List<Integer> answerScore = recordDao.selectScore(scoreColumn,answerRecord);
        for (int i = 0; i < choiceScore.size(); i++) {
            System.out.println(choiceScore.get(i));
        }
        //将学生的答案 成绩添加到试卷中去
        if (questionCS.size() > 0) {
            for (int i = 0; i < questionCS.size(); i++) {
                questionCS.get(i).setAnswer(choiceAnswers.get(i));
                questionCS.get(i).setScore(choiceScore.get(i));
            }
        }
        if (questionJS.size() > 0) {
            for (int i = 0; i < questionJS.size(); i++) {
                questionJS.get(i).setAnswer(judgeAnswers.get(i));
                questionJS.get(i).setScore(judgeScore.get(i));
            }
        }
        if (questionAS.size() > 0) {
            for (int i = 0; i < questionAS.size(); i++) {
                questionAS.get(i).setAnswer(answerAnswers.get(i));
                questionAS.get(i).setScore(answerScore.get(i));
            }
        }
        //返回该学生答题情况
        Questions questions = new Questions(questionCS, questionJS, questionAS);
        return questions;
    }

    @Override
    public int deleteQC(int id) {
        questionDao.deleteQC(id);
        questionDao.deleteQCRel(id);
        return 0;
    }

    @Override
    public int deleteQJ(int id) {
        questionDao.deleteQJ(id);
        questionDao.deleteQJRel(id);
        return 0;
    }

    @Override
    public int deleteQA(int id) {
        questionDao.deleteQA(id);
        questionDao.deleteQARel(id);
        return 0;
    }
}
