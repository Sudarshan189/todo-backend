package com.sudarshan.todo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Set;

public class Error {
    @JsonProperty("error_id")
    public Integer id;
    @JsonProperty("error_message")
    public String message;
    @JsonProperty("error_date")
    public LocalDate date;
    @JsonProperty("error_time")
    public LocalTime time;
    @JsonProperty("error_fields")
    public Set<String> stringSet;


    public Error() {
    }

    public Error(String errorMessage) {
        this.id = (Integer) errorMessage.hashCode();
        this.message = errorMessage;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        stringSet = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Set<String> getStringSet() {

        return stringSet;
    }

    public void setStringSet(Set<String> stringSet) {
        this.stringSet = stringSet;
    }
}
