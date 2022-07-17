package com.exam_2.service;



import com.exam_2.pojo.Paper;

import java.util.List;

public interface PaperService {

    int update(Paper paper);
    int updateState(int state,int paperId);
    int deletePaper(int paperId);
    //查
    Paper selectPaper(int id);
    List<Paper> selectAll();
    List<Paper> selectAllByUserId(int id);
    List<Paper> selectByStu(int id);
    List<Paper> selectAllByT(int id);
    List<Paper> checkByUAndC(int userId,int courseId);
    List<Paper> selectByCourseId(int id);
    List<Paper> selectByC(int id);
    int addPaper(Paper paper);
    int publish(int id);
    int undoPublish(int id);
}
