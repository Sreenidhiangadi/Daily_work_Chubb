package com.cachedmo.service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cachedmo.entity.Todo;
import com.cachedmo.repository.TodoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TodoService {

	 private final TodoRepository repo;

	    public TodoService(TodoRepository repo) {
	        this.repo = repo;
	    }

    @Cacheable("todos")
    public Mono<Todo> getTodoById(Long id) {
        return repo.findById(id)
                   .delayElement(Duration.ofSeconds(2));
    }

    @Cacheable("allTodos")
    public Flux<Todo> getAll() {
        return repo.findAll()
                   .delayElements(Duration.ofMillis(500)) 
                   .cache(); 
    }
    @CacheEvict(value = {"todos", "allTodos"}, allEntries = true)
    public Mono<Todo> save(Todo todo) {
        return repo.save(todo);
    }
    private void simulateSlowCall() {
        try {
            Thread.sleep(3000); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
