package com.exam_2.dao;



import com.exam_2.pojo.Paper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaperDao {
    Paper selectByPaperId(int id);
    List<Paper> selectALL();
    int update(Paper paper);
    int updatePC(Paper paper);
    int updateState(@Param("state") int state,@Param("paperId")int paperId);
    int deletePCByPaper(int paperId);
    int deletePaper(int paperId);
    List<Paper> selectAllByUserID(int id);
    List<Paper> selectAllByT(int id);
    List<Paper> selectByCourseId(int id);
    int selectPaperCourse(int id);
    List<Paper> checkByUAndC(@Param("userId") int userId,@Param("courseId")int courseId);

    int insertPaper( Paper paper);
    int insertPCRel( Paper paper);

    int publish(int id);
    int undoPublish(int id);
}
