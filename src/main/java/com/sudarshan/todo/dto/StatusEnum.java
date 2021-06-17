package com.sudarshan.todo.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusEnum {
    INCOMPLETE(1,"INCOMPLETE"), COMPLETED(2,"COMPLETED"), INVALID(0, "NOT_SET");

    private Integer id;
    private String status;

    StatusEnum(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }

    public Integer getId() {
        return this.id;
    }

    public static StatusEnum getEnumById(Integer id) {
       for (StatusEnum statusEnum: values()) {
           if (statusEnum.getId()==id) {
               return statusEnum;
           }
       }
       return INVALID;
    }
}
