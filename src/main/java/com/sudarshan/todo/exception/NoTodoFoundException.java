package com.sudarshan.todo.exception;

public class NoTodoFoundException extends RuntimeException {

    public NoTodoFoundException(String message) {
        super(message);
    }

    public NoTodoFoundException(Long todoId) {
        super("No TODO found with id "+ todoId);
    }
}
