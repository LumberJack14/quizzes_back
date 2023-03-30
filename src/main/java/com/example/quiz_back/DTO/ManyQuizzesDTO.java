package com.example.quiz_back.DTO;

import java.util.List;

public class ManyQuizzesDTO {
    private List<QuizDTO> quizzes;
    private Integer amount;

    public List<QuizDTO> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<QuizDTO> quizzes) {
        this.quizzes = quizzes;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
