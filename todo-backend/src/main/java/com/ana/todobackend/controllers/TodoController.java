package com.ana.todobackend.controllers;

import com.ana.todobackend.Entities.Todo;
import com.ana.todobackend.Models.TodoDTO;
import com.ana.todobackend.services.NotFoundException;
import com.ana.todobackend.services.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;


@RestController
@RequestMapping("api/v1/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List<TodoDTO> getAll(){
        return todoService.getAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/create")
    public Long createTodo(@RequestBody TodoDTO todoDTO){
        return todoService.save(todoDTO);
    }

    @GetMapping("/search")
    public List<TodoDTO> search(@RequestParam(value = "query") String query){
        return todoService.search(query);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/search/todo")
    public TodoDTO findById(@RequestParam(value = "id") Long todoId){
        return todoService.findById(todoId);
    }

    @GetMapping("/overdue")
    public List<TodoDTO> search(){
        return todoService.getOverdueTodos();
    }

    @DeleteMapping("/deleteByTitle")
    public void deleteByTitle(@RequestParam(value = "query") String title){
        todoService.deleteByTitle(title);
    }



    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/delete")
    public void deleteById(@RequestParam(value = "id") Long id){
        todoService.deleteById(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/update")
    public ResponseEntity<?> updateTodo(@RequestBody TodoDTO todoDTO){
        try {
            return new ResponseEntity(todoService.updateTodo(todoDTO), HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
