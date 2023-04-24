package com.ana.todobackend.services;

import com.ana.todobackend.Entities.Todo;
import com.ana.todobackend.Models.TodoDTO;
import com.ana.todobackend.mappers.TodoMapper;
import com.ana.todobackend.repositories.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    public List<TodoDTO> getAll(){
        return todoRepository.findAll().stream()
                .sorted(Comparator.comparing(Todo::getDueDate))
                .map(todoMapper::TodoToTodo_DTO)
                .collect(Collectors.toList());
    }

    public Long save(TodoDTO todoDTO){
       Todo todo = todoRepository.save(todoMapper.Todo_DTOtoTodo(todoDTO));
       return todo.getId();
    }

    public List<TodoDTO> search(String query){
        return todoRepository.
                findAllByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query).stream()
                .map(todoMapper::TodoToTodo_DTO)
                .collect(Collectors.toList());
    }

    public List<TodoDTO>getOverdueTodos(){
        return todoRepository.findAllByDoneAndDueDateBefore(false, ZonedDateTime.now()).stream()
                .map(todoMapper::TodoToTodo_DTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteByTitle(String title){
        todoRepository.deleteTodoByTitle(title);
    }

    public TodoDTO updateTodo(TodoDTO todoDTO) throws NotFoundException {
        Optional<Todo> optionalTodo = todoRepository.findById(todoDTO.getId());
        Todo todo = optionalTodo.orElseThrow(() -> new NotFoundException("Not-found"));
        todoRepository.save(todoMapper.Todo_DTOtoTodo(todoDTO));
        return todoDTO;

    }

    public void deleteById(Long id) {
        this.todoRepository.deleteById(id);
    }


    public TodoDTO findById(Long todoId) {
        return todoMapper.TodoToTodo_DTO(this.todoRepository.getReferenceById(todoId));
    }
}
