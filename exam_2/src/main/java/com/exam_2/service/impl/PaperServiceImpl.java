package com.exam_2.service.impl;

import com.exam_2.dao.PaperDao;
import com.exam_2.dao.QuestionDao;
import com.exam_2.dao.RecordDao;
import com.exam_2.dao.UserDao;
import com.exam_2.pojo.Paper;
import com.exam_2.pojo.User;
import com.exam_2.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service("paperService")
public class PaperServiceImpl implements PaperService {

    private PaperDao paperDao;
    private RecordDao recordDao;
    private QuestionDao questionDao;
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    @Autowired
    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Autowired
    public void setPaperDao(PaperDao paperDao) {
        this.paperDao = paperDao;
    }

    @Autowired
    public void setRecordDao(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    @Override
    //也要修改课程试卷关系表
    public int update(Paper paper) {
        paperDao.updatePC(paper);
        return paperDao.update(paper);
    }

    @Override
    public int updateState(int state, int paperId) {
        paperDao.updateState(state, paperId);
        return 0;
    }

    @Override
    public int deletePaper(int paperId) {
        //判断学生答案表是否存在 存在的话删除
        String choiceRecord = "record_c_" + paperId;
        String judgeRecord = "record_j_" + paperId;
        String answerRecord = "record_a_" + paperId;
        if (recordDao.isRecordExist(choiceRecord) > 0) {
            recordDao.dropTable(choiceRecord);
        }
        if (recordDao.isRecordExist(judgeRecord) > 0) {
            recordDao.dropTable(judgeRecord);
        }
        if (recordDao.isRecordExist(answerRecord) > 0) {
            recordDao.dropTable(answerRecord);
        }
        //record 表中删除记录
        recordDao.deleteByPaperId(paperId);
        //试卷题目表删除
        questionDao.deletePaperQ(paperId);
        //试卷课程表删除记录
        paperDao.deletePCByPaper(paperId);
        //最后从试卷表中删除记录
        paperDao.deletePaper(paperId);
        return 0;
    }

    @Override
    public Paper selectPaper(int id) {
        return paperDao.selectByPaperId(id);
    }

    @Override
    public List<Paper> selectAll() {
        return null;
    }

    @Override
    public List<Paper> selectAllByUserId(int id) {
        List<Paper> papers = paperDao.selectAllByUserID(id);
        DecimalFormat df = new DecimalFormat("#.00");
        if (papers.size() > 0) {
            for (int i = 0; i < papers.size(); i++) {
                double count=0;
                double avg=0;
                //分母
                int courseId = paperDao.selectPaperCourse(papers.get(i).getId());
                List<User> users = userDao.selectStuByCourse(courseId);
                double total = users.size();
                List<Integer> scores = recordDao.selectAllTotalByC(papers.get(i).getId());
                if(scores.size()>0){
                    for (int j = 0; j < scores.size(); j++) {
                        count+=scores.get(j);
                    }
                }
                if(count>0){
                    avg = Double.parseDouble(df.format(count / total));
                }
                papers.get(i).setAvg(avg);

            }
        }
//        for (int i = 0; i < papers.size(); i++) {
//            if (recordDao.selectRecord(papers.get(i).getId(), id) != null && recordDao.selectRecord(papers.get(i).getId(), id) > 1) {
//                System.out.println(recordDao.selectRecord(papers.get(i).getId(), id));
//                papers.remove(i);
//                i--;
//            }
//        }

        return papers;
    }

    @Override
    public List<Paper> selectByStu(int id) {
        List<Paper> papers = paperDao.selectAllByUserID(id);
        for (int i = 0; i < papers.size(); i++) {
            if (recordDao.selectRecord(papers.get(i).getId(), id) != null && recordDao.selectRecord(papers.get(i).getId(), id) > 1) {
                System.out.println(recordDao.selectRecord(papers.get(i).getId(), id));
                papers.remove(i);
                i--;
            }
        }

        return papers;
    }

    @Override
    public List<Paper> selectAllByT(int id) {
        return paperDao.selectAllByT(id);
    }

    @Override
    public List<Paper> checkByUAndC(int userId, int courseId) {
        List<Paper> papers = paperDao.checkByUAndC(userId, courseId);
        for (int i = 0; i < papers.size(); i++) {
            if (recordDao.selectRecord(papers.get(i).getId(), userId) != null && recordDao.selectRecord(papers.get(i).getId(), userId) > 1) {
                System.out.println(recordDao.selectRecord(papers.get(i).getId(), userId));
                papers.remove(i);
                i--;
            }
        }
        return papers;
    }

    @Override
    public List<Paper> selectByCourseId(int id) {
        List<Paper> papers = paperDao.selectByCourseId(id);
        return papers;
    }

    @Override
    public List<Paper> selectByC(int id) {
        List<Paper> papers = paperDao.selectByCourseId(id);
        DecimalFormat df = new DecimalFormat("#.00");
        if (papers.size() > 0) {
            for (int i = 0; i < papers.size(); i++) {
                double count=0;
                double avg=0;
                //分母
                int courseId = paperDao.selectPaperCourse(papers.get(i).getId());
                List<User> users = userDao.selectStuByCourse(courseId);
                double total = users.size();
                List<Integer> scores = recordDao.selectAllTotalByC(papers.get(i).getId());
               if(scores.size()>0){
                   for (int j = 0; j < scores.size(); j++) {
                       count+=scores.get(j);
                   }
               }
               if(count>0){
                  avg = Double.parseDouble(df.format(count / total));
               }
               papers.get(i).setAvg(avg);

            }
        }
        return papers;
    }

    @Override
    public int addPaper(Paper paper) {
        paperDao.insertPaper(paper);
        paperDao.insertPCRel(paper);

        //生成试卷记录表的名称
        String choiceRecord = "record_c_" + paper.getId();
        String judgeRecord = "record_j_" + paper.getId();
        String answerRecord = "record_a_" + paper.getId();

        //创建试卷试题记录表
        recordDao.createRecordTable(choiceRecord);
        recordDao.createRecordTable(judgeRecord);
        recordDao.createRecordTable(answerRecord);

        return 1;
    }

    @Override
    public int publish(int id) {
        return paperDao.publish(id);
    }

    @Override
    public int undoPublish(int id) {
        return paperDao.undoPublish(id);
    }

}
