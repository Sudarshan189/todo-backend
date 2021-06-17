package com.sudarshan.todo.repository;

import com.sudarshan.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query(value = "select * from todo_table where user_id = ?1", nativeQuery = true)
    List<Todo> getAllTodoByUserId(String userid);
}
