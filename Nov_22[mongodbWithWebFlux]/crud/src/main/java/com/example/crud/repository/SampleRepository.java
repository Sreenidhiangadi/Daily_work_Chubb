package com.example.crud.repository;

import com.example.crud.entity.Sample;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SampleRepository extends ReactiveMongoRepository<Sample,String> {
    Flux<Sample> findByPublished(boolean published);
    Flux<Sample> findByTitleContaining(String title);
}
