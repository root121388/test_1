package com.exam_2.service;



import com.exam_2.pojo.Questions;
import com.exam_2.pojo.Record;

import java.util.List;

public interface RecordService {
    public List<Record> examList(String userId);
    public List<String> selectAllMajorByUserId(String userId);

    int addRecord(Record record);

    int submitPaper(Questions questions, int userId, int paperId);
    List<Record> checkGradePaperList(int userId);
    List<Record> checkGradePaperListByP(int paperId);

}
