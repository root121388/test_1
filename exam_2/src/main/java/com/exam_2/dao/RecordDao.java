package com.exam_2.dao;

import com.exam_2.pojo.Record;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface RecordDao {
    public List<Record> selectALlByUser(String userId);
    public Date selectDate();
    public List<String> selectAllMajorByUserId(String userId);

    List<Record> selectGradedPaperByUser(int userId);
    List<Record> selectGradedPaperByP(int paperId);
    int addScoreRecord(Record record);
    int isScoreRecordExist(Record record);

    int updateRecord(@Param("paperId") int paperId,@Param("userId") int userId);
    int updateCount(@Param("paperId") int paperId,@Param("userId") int userId,@Param("count") int count);
    Integer selectRecord(@Param("paperId") int paperId,@Param("userId") int userId);


    //判断记录表是否存在
    int isRecordExist(@Param("name")String tableName);
    int dropTable(@Param("name")String tableName);
    int deleteByPaperId(@Param("paperId") int paperId);
    int deleteByUserId(@Param("userId") int userId);


    int truncateRecord(@Param("name")String tableName);
    int createRecordTable(@Param("name") String tableName);
    int addRecord(@Param("question") int questionId,@Param("name") String tableName);
//    int appendChoiceColumn()
    int appendColumn(@Param("columnName") String columnName,@Param("table") String tableName);
    int addStuScore(@Param("columnName") String columnName,@Param("table") String tableName,@Param("answer") String answer,@Param("id")int id);
    int setScore(@Param("columnName") String columnName,@Param("table") String tableName,@Param("score") int score,@Param("id") int id);

    //将完成试卷的学生id取出来
    List<Integer> selectStu(int id);
    List<String> selectAnswer(@Param("columnName") String columnName,@Param("table") String tableName);
    List<Integer> selectScore(@Param("columnName") String columnName,@Param("table") String tableName);
    int selectS(@Param("columnName") String columnName,@Param("table") String tableName,@Param("id") int id);

    //按照试卷ID提取所有学生的成绩
    List<Integer> selectAllTotalByC(int id);
}
