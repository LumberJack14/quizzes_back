package com.example.quiz_back.controller;


import com.example.quiz_back.DTO.ManyQuizzesDTO;
import com.example.quiz_back.DTO.QuizConverter;
import com.example.quiz_back.DTO.QuizDTO;
import com.example.quiz_back.entity.Quiz;
import com.example.quiz_back.exception.QuizAlreadyExistsException;
import com.example.quiz_back.exception.QuizNotFoundException;
import com.example.quiz_back.repository.QuizPagingRepo;
import com.example.quiz_back.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quizzes")
@CrossOrigin(origins = "http://localhost:3000")
public class QuizController {

    @Autowired
    private QuizService quizService;


    //1. create dtos for quizes etc, 2.
    @PostMapping
    public ResponseEntity createQuiz(@RequestBody QuizDTO quizDTO) {
        try {
            quizService.createQuiz(quizDTO);
            return ResponseEntity.ok().body("Quiz was successfully saved!");
        } catch (QuizAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error creating the quiz");
        }
    }

    @GetMapping("/top")
    public ResponseEntity<?> getQuizzes(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        try {
            ManyQuizzesDTO manyQuizzesDTO = new ManyQuizzesDTO();
            List<QuizDTO> quizzes = quizService.getQuizzes(pageNumber, pageSize);
            manyQuizzesDTO.setQuizzes(quizzes);
            manyQuizzesDTO.setAmount(quizzes.size());
            return ResponseEntity.ok().body(manyQuizzesDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error getting all quizzes");
        }
    }

    @GetMapping
    public ResponseEntity<?> getOneQuiz(@RequestParam Integer id) {
        try {
            Quiz quiz = quizService.getOne(id);
            QuizDTO quizDTO = QuizConverter.toDTO(quiz);
            return ResponseEntity.ok().body(quizDTO);
        } catch (QuizNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Server error");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOneQuiz(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok().body(quizService.deleteOne(id));
        } catch (QuizNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Server error");
        }
    }
}
