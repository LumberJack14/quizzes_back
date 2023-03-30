package com.example.quiz_back.repository;

import com.example.quiz_back.entity.Quiz;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuizRepo extends CrudRepository<Quiz, Integer> {

    Quiz findByQuizName(String quizName);

    //List<Quiz>
}
