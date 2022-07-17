package com.exam_2.service.impl;

import com.exam_2.dao.RecordDao;
import com.exam_2.pojo.QuestionC;
import com.exam_2.pojo.QuestionJ;
import com.exam_2.pojo.Questions;
import com.exam_2.pojo.Record;
import com.exam_2.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("recordService")
public class RecordServiceImpl implements RecordService {

    private RecordDao recordDao;

    @Autowired
    public void setRecordDao(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    @Override
    public List<Record> examList(String userId) {

        List<Record> records = recordDao.selectALlByUser(userId);
//        for (Record r : records) {
//            r.date();
//        }
//        System.out.println(records.get(1).getStart());

        return records;
    }

    @Override
    public List<String> selectAllMajorByUserId(String userId) {

        System.out.println("service=" + userId);
        List<String> strings = recordDao.selectAllMajorByUserId(userId);
        System.out.println("map==" + recordDao.selectAllMajorByUserId(userId));
        return strings;
    }

    @Override
    public int addRecord(Record record) {
        if(recordDao.isScoreRecordExist(record)>0){
            return 1;

        }else {
            return recordDao.addScoreRecord(record);
        }

    }

    @Override
    public int submitPaper(Questions questions, int userId, int paperId) {
        //分别获取生成试卷的选择判断简答题目
        List<QuestionC> choices = questions.getChoices();
        List<QuestionJ> judges = questions.getJudges();
        List<QuestionJ> answers = questions.getAnswers();
        //生成试卷记录表的名称
        String choiceRecord = "record_c_" + paperId;
        String judgeRecord = "record_j_" + paperId;
        String answerRecord = "record_a_" + paperId;
        //要添加的两列列名
        String answerColumn = "answer_"+userId;
        String scoreColumn = "score_"+userId;


        if(choices.size()>0){
            recordDao.appendColumn(answerColumn,choiceRecord);
            recordDao.appendColumn(scoreColumn,choiceRecord);
        }
        recordDao.appendColumn(answerColumn,judgeRecord);
        recordDao.appendColumn(scoreColumn,judgeRecord);

        recordDao.appendColumn(answerColumn,answerRecord);
        recordDao.appendColumn(scoreColumn,answerRecord);

        //将试卷答案录入到数据库中
        if (choices.size() > 0) {
            for(int i = 0;i<choices.size();i++){
                recordDao.addStuScore(answerColumn,choiceRecord,choices.get(i).getAnswer(),choices.get(i).getId());
            }
        }
        if (judges.size() > 0) {
            for (int i = 0; i < judges.size(); i++) {
                recordDao.addStuScore(answerColumn,judgeRecord,judges.get(i).getAnswer(),judges.get(i).getId());

            }
        }
        if (answers.size() > 0) {
            for (int i = 0; i < answers.size(); i++) {
               recordDao.addStuScore(answerColumn,answerRecord,answers.get(i).getAnswer(),answers.get(i).getId());
            }
        }
        //修改记录表中记录的状态
        recordDao.updateRecord(paperId,userId);

        return 0;
    }

    @Override
    public List<Record> checkGradePaperList(int userId) {
        return recordDao.selectGradedPaperByUser(userId);
    }

    @Override
    public List<Record> checkGradePaperListByP(int paperId) {
        return recordDao.selectGradedPaperByP(paperId);
    }

}
