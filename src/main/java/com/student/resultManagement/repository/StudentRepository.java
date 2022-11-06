package com.student.resultManagement.repository;

import com.student.resultManagement.entity.Student;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface StudentRepository extends ReactiveMongoRepository<Student, String> {
    public Mono<Student> findByRollNumber(int rollNumber);
}
