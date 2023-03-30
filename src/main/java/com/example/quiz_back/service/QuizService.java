package com.example.quiz_back.service;

import com.example.quiz_back.DTO.AnswerDTO;
import com.example.quiz_back.DTO.QuestionDTO;
import com.example.quiz_back.DTO.QuizConverter;
import com.example.quiz_back.DTO.QuizDTO;
import com.example.quiz_back.entity.Answer;
import com.example.quiz_back.entity.Question;
import com.example.quiz_back.entity.Quiz;
import com.example.quiz_back.exception.QuizAlreadyExistsException;
import com.example.quiz_back.exception.QuizNotFoundException;
import com.example.quiz_back.repository.QuizPagingRepo;
import com.example.quiz_back.repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuizPagingRepo quizPagingRepo;

    public List<QuizDTO> getQuizzes(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Quiz> quizzes = quizPagingRepo.findAll(pageable);
        List<QuizDTO> quizDTOs = quizzes.getContent().stream()
                .map(quiz -> {
                    QuizDTO quizDTO = QuizConverter.toDTO(quiz);
                    return quizDTO;
                }).toList();


        return quizDTOs;
    }

    public Quiz createQuiz(QuizDTO quizDTO) throws QuizAlreadyExistsException {
        Quiz quiz = new Quiz();
        quiz.setQuizName(quizDTO.getQuizName());

        //check if there is a quiz with the same name
        if (quizRepo.findByQuizName(quiz.getQuizName()) != null) {
            throw new QuizAlreadyExistsException("quiz with such name already exists");
        }

        quiz.setQuizDesc(quizDTO.getQuizDesc());
        quiz.setCreatorId(quizDTO.getCreatorId());

        List<Question> questions = new ArrayList<>();
        for (QuestionDTO questionDTO: quizDTO.getQuestions()) {
            Question question = new Question();
            question.setQuiz(quiz);
            question.setText(questionDTO.getText());

            List<Answer> answers = new ArrayList<>();
            for (AnswerDTO answerDTO : questionDTO.getAnswers()){
                Answer answer = new Answer();
                answer.setQuestion(question);
                answer.setText(answerDTO.getText());
                answer.setCorrect(answerDTO.isCorrect());

                answers.add(answer);

            }
            question.setAnswers(answers);
            questions.add(question);

        }

        quiz.setQuestions(questions);

        return quizRepo.save(quiz);
    }

    public Quiz getOne(Integer id) throws QuizNotFoundException {
        Quiz quiz = quizRepo.findById(id).get();
        /*if (quizRepo.findById(id).get() == null) {
            throw new QuizNotFoundException("couldn't find a quiz with such id");
        }*/
        return quiz;
    }

    public Integer deleteOne(Integer id) throws QuizNotFoundException {
        Quiz quiz = quizRepo.findById(id).get();
        if (quizRepo.findById(id).get() == null) {
            throw new QuizNotFoundException("couldn't find a quiz with such id");
        }
        quizRepo.deleteById(id);
        return id;
    }
}
