package com.example.quiz_back.repository;

import com.example.quiz_back.entity.Quiz;
import org.springframework.data.repository.CrudRepository;

public interface QuizRepo extends CrudRepository<Quiz, Integer> {

    Quiz findByQuizName(String quizName);
}
