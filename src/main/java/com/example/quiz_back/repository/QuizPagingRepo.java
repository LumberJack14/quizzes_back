package com.example.quiz_back.repository;

import com.example.quiz_back.entity.Quiz;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface QuizPagingRepo extends PagingAndSortingRepository<Quiz, Integer> {
}
