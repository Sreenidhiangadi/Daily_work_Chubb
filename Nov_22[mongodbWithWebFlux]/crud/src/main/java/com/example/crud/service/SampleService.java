package com.example.crud.service;

import com.example.crud.entity.Sample;
import com.example.crud.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class SampleService {
    @Autowired
    SampleRepository sampleRepository;
    public Flux<Sample> findAll() {
        return sampleRepository.findAll();
    }
    public Flux<Sample> findByTitleContaining(String title){
        return sampleRepository.findByTitleContaining(title);
    }
    public Mono<Sample> findById(String id){
        return sampleRepository.findById(id);
    }
    public Mono<Sample> save(Sample sample) {
        return sampleRepository.save(sample);
    }
    public Mono<Sample> update(String id, Sample sample) {
        return sampleRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalTutorial -> {
                    if (optionalTutorial.isPresent()) {
                        sample.setId(id);
                        return sampleRepository.save(sample);
                    }

                    return Mono.empty();
                });
    }
    public Mono<Void> deleteById(String id) {
        return sampleRepository.deleteById(id);
    }
    public Mono<Void> deleteAll() {
        return sampleRepository.deleteAll();
    }
    public Flux<Sample> findByPublished(boolean isPublished) {
        return sampleRepository.findByPublished(isPublished);
    }
}
