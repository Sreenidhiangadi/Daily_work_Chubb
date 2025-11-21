package com.example.crud.service;

import com.example.crud.entity.Sample;
import com.example.crud.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
}
