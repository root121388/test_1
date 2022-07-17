package com.exam_2.controller;

import com.exam_2.pojo.Questions;
import com.exam_2.pojo.Record;
import com.exam_2.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/record")
public class RecordController {

    private RecordService recordService;

    @Autowired
    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }

    @RequestMapping("/addRecord")
    @ResponseBody
    public int joinExam(@RequestBody Record record){
        return recordService.addRecord(record);
    }

    @RequestMapping("/submitPaper")
    @ResponseBody
    public int submitPaper(@RequestBody Questions questions, int userId, int paperId){
        return recordService.submitPaper(questions,userId,paperId);
    }
    @RequestMapping("/checkGradePaperList")
    @ResponseBody
    public List<Record> checkGradePaperList(int userId){
        return recordService.checkGradePaperList(userId);
    }
    @RequestMapping("/checkGradePaperListByP")
    @ResponseBody
    public List<Record> checkGradePaperListByCourse(int paperId){
        return recordService.checkGradePaperListByP(paperId);
    }
}
