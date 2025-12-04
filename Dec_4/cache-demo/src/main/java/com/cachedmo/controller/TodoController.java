package com.cachedmo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cachedmo.entity.Todo;
import com.cachedmo.service.TodoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
  
    @GetMapping("/{id}")
    public Mono<Todo> getTodo(@PathVariable Long id) {
        return todoService.getTodoById(id);
    }
    @PostMapping
    public Mono<Todo> createTodo(@RequestBody Todo todo) {
        return todoService.save(todo);
    }
    @GetMapping("/getAll")
    public Flux<Todo> getAll(){
    	return todoService.getAll();
    }
    
    
}
