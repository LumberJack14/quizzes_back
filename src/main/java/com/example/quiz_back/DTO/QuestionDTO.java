package com.example.quiz_back.DTO;

import com.example.quiz_back.entity.Answer;

import java.util.List;

public class QuestionDTO {
    private Integer id;
    private String text;
    private List<AnswerDTO> answers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }
}
