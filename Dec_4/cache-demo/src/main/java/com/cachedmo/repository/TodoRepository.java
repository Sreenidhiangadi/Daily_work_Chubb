package com.cachedmo.repository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.cachedmo.entity.Todo;

public interface TodoRepository extends ReactiveMongoRepository<Todo, Long> {
}
