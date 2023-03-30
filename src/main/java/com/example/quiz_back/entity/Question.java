package com.example.quiz_back.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String text;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    private List<Answer> answers;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;


    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Question() {
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }


    public Quiz getQuiz() {
        return quiz;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
