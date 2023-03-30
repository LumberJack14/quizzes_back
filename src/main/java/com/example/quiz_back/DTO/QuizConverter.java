package com.example.quiz_back.DTO;

import com.example.quiz_back.entity.Answer;
import com.example.quiz_back.entity.Question;
import com.example.quiz_back.entity.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizConverter {
    public static QuizDTO toDTO(Quiz quiz) {
        QuizDTO quizDTO = new QuizDTO();

        quizDTO.setId(quiz.getId());
        quizDTO.setQuizName(quiz.getQuizName());
        quizDTO.setQuizDesc(quiz.getQuizDesc());
        quizDTO.setCreatorId(quiz.getCreatorId());
        quizDTO.setAmount(quiz.getQuestions().size());

        List<QuestionDTO> questionDTOs = new ArrayList<>();
        for (Question question : quiz.getQuestions()) {
            QuestionDTO questionDTO = new QuestionDTO();

            questionDTO.setText(question.getText());
            questionDTO.setId(question.getId());

            List<AnswerDTO> answerDTOs = new ArrayList<>();
            for (Answer answer : question.getAnswers()) {
                AnswerDTO answerDTO = new AnswerDTO();

                answerDTO.setId(answer.getId());
                answerDTO.setText(answer.getText());
                answerDTO.setCorrect(answer.isCorrect());
                answerDTOs.add(answerDTO);
            }
                questionDTO.setAnswers(answerDTOs);
                questionDTOs.add(questionDTO);
        }

        quizDTO.setQuestions(questionDTOs);

        return quizDTO;
    }
}
