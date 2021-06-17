package com.sudarshan.todo.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "todo_table")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "todo_id")
    private Long todoId;
    @Column(name = "date_of_creation")
    private LocalDate dateOfCreation;
    @Column(name = "plan_start_date")
    private LocalDate planStartDate;
    @Column(name = "plan_end_date")
    private LocalDate planEndDate;
    @Column(name = "short_desc", length = 40, nullable = false)
    private String shortDesc;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private Integer status;
    @Column(name = "user_id")
    private String userId;

    public Todo() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "dateOfCreation=" + dateOfCreation +
                ", planStartDate=" + planStartDate +
                ", planEndDate=" + planEndDate +
                ", shortDesc='" + shortDesc + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
