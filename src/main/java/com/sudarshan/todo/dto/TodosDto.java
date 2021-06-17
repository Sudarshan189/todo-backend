package com.sudarshan.todo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TodosDto {
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("todos")
    private List<TodoDto> todoDtoList;


    public TodosDto() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<TodoDto> getTodoDtoList() {
        return todoDtoList;
    }

    public void setTodoDtoList(List<TodoDto> todoDtoList) {
        this.todoDtoList = todoDtoList;
    }
}
