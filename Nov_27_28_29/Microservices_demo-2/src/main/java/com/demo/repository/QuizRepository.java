package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {

}