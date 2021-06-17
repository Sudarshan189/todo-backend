package com.sudarshan.todo.config;

import com.sudarshan.todo.dto.StatusEnum;
import com.sudarshan.todo.dto.TodoDto;
import com.sudarshan.todo.model.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootTest
public class UtilConfigTest {

    @Autowired
    public ModelMapper modelMapper;

    @Test
    public void testMapperTodoDto2Todo() {
        TodoDto todoDto = new TodoDto();
        todoDto.setTodoId(10L);
        todoDto.setDescription("This is Description");
        todoDto.setShortDesc("Short Description");
        todoDto.setTodoId(10L);
        todoDto.setDateOfCreation(LocalDate.now());
        todoDto.setPlanStartDate(todoDto.getDateOfCreation());
        todoDto.setPlanEndDate(todoDto.getPlanStartDate().plusDays(3));
        todoDto.setStatus(StatusEnum.valueOf("INCOMPLETE"));

        Todo todo = modelMapper.map(todoDto, Todo.class);
        Assertions.assertEquals(todoDto.getTodoId(), todo.getTodoId());
        Assertions.assertEquals(todoDto.getStatus().getId(), todo.getStatus());
    }

    @Test
    public void testMapperTodo2TodoDto() {
        Todo todo = new Todo();
        todo.setTodoId(10L);
        todo.setDescription("This is Description");
        todo.setShortDesc("Short Description");
        todo.setTodoId(10L);
        todo.setDateOfCreation(LocalDate.now());
        todo.setPlanStartDate(todo.getDateOfCreation());
        todo.setPlanEndDate(todo.getPlanStartDate().plusDays(3));
        todo.setStatus(1);

        TodoDto todoDto = modelMapper.map(todo, TodoDto.class);
        Assertions.assertEquals(todo.getTodoId(), todoDto.getTodoId());
        Assertions.assertEquals(todo.getStatus(), todoDto.getStatus().getId());
    }
}
