package com.example.quiz_back.exception;

public class QuizAlreadyExistsException extends Exception {
    public QuizAlreadyExistsException(String message) {
        super(message);
    }
}
