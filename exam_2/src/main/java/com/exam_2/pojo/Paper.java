package com.exam_2.pojo;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component("paper")
public class Paper {
    private Integer id;
    private String paperName;
    private Double time;
    private Date startTime;
    private String start;
    private Integer courseId;
    private String courseName;
    private Integer state;
    private int loop;
    private Double avg;

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

    public int getLoop() {
        return loop;
    }

    public void setLoop(int loop) {
        this.loop = loop;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "id=" + id +
                ", paperName='" + paperName + '\'' +
                ", time=" + time +
                ", startTime=" + startTime +
                ", start='" + start + '\'' +
                ", courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", state=" + state +
                '}';
    }
}
