package com.exam_2.pojo;

import org.springframework.stereotype.Component;

@Component("questionJ")
public class QuestionJ {
    private Integer id;
    private String stem;
    private String answer;
    private String rightAnswer;
    private Integer courseID;
    private Integer type;
    private int score;
    private double avg;

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    @Override
    public String toString() {
        return "QuestionJ{" +
                "id=" + id +
                ", stem='" + stem + '\'' +
                ", answer='" + answer + '\'' +
                ", rightAnswer='" + rightAnswer + '\'' +
                ", courseID=" + courseID +
                ", type=" + type +
                '}';
    }
}
