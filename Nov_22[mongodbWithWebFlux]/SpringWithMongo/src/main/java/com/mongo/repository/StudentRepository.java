package com.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.mongo.entity.Student;

public interface StudentRepository extends MongoRepository<Student, String> {

}
