package com.exam_2.pojo;

import org.springframework.stereotype.Component;

import java.util.List;
@Component("questions")
public class Questions {
    private List<QuestionC> choices;
    private List<QuestionJ> judges;
    private List<QuestionJ> answers;

    public Questions() {
    }

    public Questions(List<QuestionC> choices, List<QuestionJ> judges, List<QuestionJ> answers) {
        this.choices = choices;
        this.judges = judges;
        this.answers = answers;
    }

    public List<QuestionC> getChoices() {
        return choices;
    }

    public void setChoices(List<QuestionC> choices) {
        this.choices = choices;
    }

    public List<QuestionJ> getJudges() {
        return judges;
    }

    public void setJudges(List<QuestionJ> judges) {
        this.judges = judges;
    }

    public List<QuestionJ> getAnswers() {
        return answers;
    }

    public void setAnswers(List<QuestionJ> answers) {
        this.answers = answers;
    }
}
