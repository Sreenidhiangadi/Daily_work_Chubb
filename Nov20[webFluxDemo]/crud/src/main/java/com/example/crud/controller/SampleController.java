package com.example.crud.controller;

import com.example.crud.entity.Sample;
import com.example.crud.repository.SampleRepository;
import com.example.crud.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController

public class SampleController {
    @Autowired
    SampleService sampleService;
    @GetMapping("/sample")
    public Flux<Sample> getAllSample(){
        return sampleService.findAll();
    }
    @GetMapping("/sample/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Sample> getTutorialById(@PathVariable("id") String id) {
        return sampleService.findById(id);
    }
    @PostMapping("/sample")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Sample> createTutorial(@RequestBody Sample sample) {
        return sampleService.save(sample);
    }
}
