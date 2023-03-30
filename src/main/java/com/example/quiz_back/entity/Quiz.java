package com.example.quiz_back.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer creatorId;
    private String quizName;
    private String quizDesc;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quiz")
    private List<Question> questions;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Quiz() {

    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public void setQuizDesc(String quizDesc) {
        this.quizDesc = quizDesc;
    }

    public String getQuizName() {
        return quizName;
    }

    public String getQuizDesc() {
        return quizDesc;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }


    public Integer getId() {
        return id;
    }

    public Integer getCreatorId() {
        return creatorId;
    }


}
