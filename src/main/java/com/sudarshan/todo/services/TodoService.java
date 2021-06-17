package com.sudarshan.todo.services;

import com.sudarshan.todo.dto.StatusEnum;
import com.sudarshan.todo.exception.NoTodoFoundException;
import com.sudarshan.todo.model.Todo;

import java.util.List;

public interface TodoService {

    Todo addNewTodo(String userId, Todo todo);

    List<Todo> getAllTodoByUserId(String userId);

    Todo updateTodoWithUserIDAndTodoId(String userId, Long todoId, StatusEnum status) throws NoTodoFoundException;

    void deleteTodoByTodoId(Long todoId) throws NoTodoFoundException;
}
