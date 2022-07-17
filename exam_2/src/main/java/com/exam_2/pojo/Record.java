package com.exam_2.pojo;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
@Component("record")
public class Record {
    private Integer id;
    private Integer examId;
    private String pna ;
    private String userId;
    private String account;
    private String name;
    private String major;
    private Date startTime;
    private Date endTime;
    private String start;
    private String end;
    private Double count;//分数
    private String state;


    public Record() {
    }

    public String getPna() {
        return pna;
    }

    public void setPna(String pna) {
        this.pna = pna;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

//    public String getStart() {
//        return start;
//    }
//
//    public void setStart(String start) {
//        this.start = start;
//    }
//
//    public String getEnd() {
//        return end;
//    }
//
//    public void setEnd(String end) {
//        this.end = end;
//    }
//    public void date(){
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        this.setStart(df.format(this.getStartTime()));
//        this.setEnd(df.format(this.getEndTime()));
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
