package com.sudarshan.todo.controller;

import com.sudarshan.todo.dto.Error;
import com.sudarshan.todo.exception.NoTodoFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NoTodoFoundException.class)
    public ResponseEntity<Error> handleNoTodoFoundException(NoTodoFoundException noTodoFoundException) {
        Error error = new Error(noTodoFoundException.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleMethodArgumentNotValidException(MethodArgumentNotValidException notValidException) {
        Set<String> stringSet = new HashSet<>();
        notValidException.getBindingResult().getFieldErrors()
                .forEach(error -> {
                    String fieldName = error.getField();
                    String errorDesc = error.getDefaultMessage();
                    stringSet.add(errorDesc);
                });
        Error error = new Error("Inputs are not proper");
        error.setStringSet(stringSet);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
