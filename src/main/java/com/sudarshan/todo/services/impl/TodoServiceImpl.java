package com.sudarshan.todo.services.impl;

import com.sudarshan.todo.dto.StatusEnum;
import com.sudarshan.todo.exception.NoTodoFoundException;
import com.sudarshan.todo.model.Todo;
import com.sudarshan.todo.repository.TodoRepository;
import com.sudarshan.todo.services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    public static final Logger LOGGER = LoggerFactory.getLogger(TodoServiceImpl.class);

    @Value("${todo.default.expiry}")
    private Integer defaultExpiry;

    @Value("${todo.default.status}")
    private Integer defaultStatus;

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Todo addNewTodo(String userId, Todo todo) {
        todo.setUserId(userId);
        todo.setDateOfCreation(LocalDate.now());
        todo.setPlanStartDate(todo.getPlanStartDate()==null ? todo.getDateOfCreation() : todo.getPlanStartDate());
        todo.setPlanEndDate(todo.getPlanEndDate() == null ? todo.getPlanStartDate().plusDays(defaultExpiry) : todo.getPlanEndDate());
        todo.setStatus(todo.getStatus()==null? defaultStatus : todo.getStatus());
        LOGGER.info("Adding TODO {}", todo);
        todo = todoRepository.save(todo);
        return todo;
    }

    @Override
    public List<Todo> getAllTodoByUserId(String userId) {
        LOGGER.info("Getting all todos for ->  {}", userId);
        List<Todo> allTodos = todoRepository.getAllTodoByUserId(userId);
        LOGGER.info("Found todos -> size found {}", allTodos.size());
        return allTodos;
    }

    @Override
    public Todo updateTodoWithUserIDAndTodoId(String userId, Long todoId, StatusEnum status) throws NoTodoFoundException {
        Optional<Todo> todoOptional = todoRepository.findById(todoId);
        if (todoOptional.isPresent()) {
            LOGGER.info("Found TODO for id {}", todoId);
            Todo todo = todoOptional.get();
            todo.setStatus(status.getId());
            todo = todoRepository.save(todo);
            return todo;
        }
        LOGGER.info("No TODO found for id {}", todoId);
        throw new NoTodoFoundException(todoId);
    }

    @Override
    public void deleteTodoByTodoId(Long todoId) throws NoTodoFoundException {
        boolean isTodoPresent = todoRepository.existsById(todoId);
        if (isTodoPresent) {
            LOGGER.info("Found TODO for id {}. Proceeding to delete", todoId);
            todoRepository.deleteById(todoId);
            return;
        }
        throw new NoTodoFoundException(todoId);
    }
}
