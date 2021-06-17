package com.sudarshan.todo.controller;

import com.sudarshan.todo.dto.StatusEnum;
import com.sudarshan.todo.dto.TodoDto;
import com.sudarshan.todo.dto.TodosDto;
import com.sudarshan.todo.model.Todo;
import com.sudarshan.todo.services.TodoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/v1/todo")
public class TodoController {

    public static final Logger LOGGER = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TodoService todoService;

    @Value("${todo.variable.status}")
    private String defaultStatus;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<TodoDto> addTodo(@RequestHeader("user_id") @Valid String userId,
                                           @RequestBody @Valid TodoDto todoDto) {
        LOGGER.info("Adding TODO -> {} for User {}", todoDto.toString(), userId);
        Todo todo = modelMapper.map(todoDto, Todo.class);
        Todo updatedTodo = todoService.addNewTodo(userId, todo);
        TodoDto responseTodoDto = modelMapper.map(updatedTodo, TodoDto.class);
        return new ResponseEntity<>(responseTodoDto, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/all/{user_id}", method = RequestMethod.GET)
    public ResponseEntity<TodosDto> getAllTodoWithUser(@PathVariable("user_id") String userId) {
        LOGGER.info("Get All TODO for user -> {}", userId);
        List<Todo> allTodos = todoService.getAllTodoByUserId(userId);
        TodosDto todosDto = new TodosDto();
        todosDto.setUserId(userId);
        todosDto.setTodoDtoList(allTodos.stream().map(todo -> modelMapper.map(todo, TodoDto.class)).collect(Collectors.toList()));
        return new ResponseEntity<>(todosDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/update/{todo_id}", method = RequestMethod.PUT)
    public ResponseEntity<TodoDto> updateTodoStatus(@PathVariable("todo_id") @Validated Long todoId,
                                                    @RequestHeader("user_id") @Validated String userId,
                                                    @RequestBody Map<String, String> statusMap) {
        LOGGER.info("Update TODO for id {} and with status {}", todoId, statusMap);
        Todo todo = todoService.updateTodoWithUserIDAndTodoId(userId, todoId, StatusEnum.valueOf(statusMap.get(defaultStatus)));
        TodoDto todoDto = modelMapper.map(todo, TodoDto.class);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{todo_id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteTodo(@PathVariable("todo_id") @Validated Long todoId,
                           @RequestHeader("user_id") @Validated String userId) {
        LOGGER.info("Delete TODO for id {}", todoId);
        todoService.deleteTodoByTodoId(todoId);
    }


}
