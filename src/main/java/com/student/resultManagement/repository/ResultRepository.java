package com.student.resultManagement.repository;

import com.student.resultManagement.entity.Result;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ResultRepository extends ReactiveMongoRepository<Result, String> {

    Flux<Result> findAllByObtainedMarksBetweenOrderByObtainedMarksDesc(int marksGT, int marksLT);

    Flux<Result> findAllByOrderByObtainedMarksAsc();

    Mono<Result> findByRollNumber(int rollNumber);
}
