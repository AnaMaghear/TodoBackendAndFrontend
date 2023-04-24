package com.ana.todobackend.repositories;

import com.ana.todobackend.Entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String titleQuery, String descriptionQuery);

    List<Todo> findAllByDoneAndDueDateBefore(boolean done, ZonedDateTime dueDate);
    void deleteTodoByTitle(String title);

}
