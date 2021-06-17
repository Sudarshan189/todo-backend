package com.sudarshan.todo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class TodoDto {
    @JsonProperty("todo_id")
    private Long todoId;
    @JsonProperty("date_of_creation")
    private LocalDate dateOfCreation;
    @JsonProperty("plan_start_date")
    private LocalDate planStartDate;
    @JsonProperty("plan_end_date")
    private LocalDate planEndDate;
    @JsonProperty("short_desc")
    @NotNull(message = "short_desc - Please add Short Description")
    @NotEmpty(message = "short_desc - Please add Short Description")
    @Size(max = 40, message = "short_desc - Short Description length should be less that 40 characters")
    private String shortDesc;
    @JsonProperty("description")
    @Length(max = 255, message = "description - Description length should be less that 250 characters")
    private String description;
    @JsonProperty("status")
    private StatusEnum status;

    public TodoDto() {
    }

    public Long getTodoId() {
        return todoId;
    }

    public void setTodoId(Long todoId) {
        this.todoId = todoId;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public LocalDate getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(LocalDate planStartDate) {
        this.planStartDate = planStartDate;
    }

    public LocalDate getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(LocalDate planEndDate) {
        this.planEndDate = planEndDate;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "TodoDto{" +
                "todoId='" + todoId + '\'' +
                ", dateOfCreation=" + dateOfCreation +
                ", planStartDate=" + planStartDate +
                ", planEndDate=" + planEndDate +
                ", shortDesc='" + shortDesc + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
