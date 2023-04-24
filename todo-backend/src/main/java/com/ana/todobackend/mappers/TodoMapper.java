package com.ana.todobackend.mappers;


import com.ana.todobackend.Entities.Todo;
import com.ana.todobackend.Models.TodoDTO;
import org.mapstruct.Mapper;

@Mapper
public interface TodoMapper {
    Todo Todo_DTOtoTodo (TodoDTO todoDTO);
    TodoDTO TodoToTodo_DTO(Todo todo);
}
