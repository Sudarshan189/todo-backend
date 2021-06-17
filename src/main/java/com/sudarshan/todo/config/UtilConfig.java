package com.sudarshan.todo.config;

import com.sudarshan.todo.dto.StatusEnum;
import com.sudarshan.todo.dto.TodoDto;
import com.sudarshan.todo.model.Todo;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilConfig {

    @Bean
    public ModelMapper todoModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        Converter<Integer, StatusEnum> stringEnumConverter = src -> src.getSource() == null? null : StatusEnum.getEnumById(src.getSource());
        TypeMap<Todo, TodoDto> todoTypeMap = modelMapper.createTypeMap(Todo.class, TodoDto.class);
        todoTypeMap.addMappings(mapper -> {
            mapper.using(stringEnumConverter).map(Todo::getStatus, TodoDto::setStatus);
        });

        Converter<StatusEnum, Integer> statusEnumConverter = src -> src.getSource() == null? null : src.getSource().getId();
        TypeMap<TodoDto, Todo> todoDtoTypeMap = modelMapper.createTypeMap(TodoDto.class, Todo.class);
        todoDtoTypeMap.addMappings(mapper -> {
           mapper.using(statusEnumConverter).map(TodoDto::getStatus, Todo::setStatus);
        });
        return modelMapper;
    }
}
