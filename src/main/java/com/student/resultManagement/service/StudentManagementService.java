package com.student.resultManagement.service;

import com.student.resultManagement.entity.Result;
import com.student.resultManagement.entity.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

    public interface StudentManagementService {

        Mono<Student> saveStudent(Student student);
        Mono<Student> findStudentByRollNumber(int rollNumber);
        Flux<Student> findAllStudents();
        Mono<Student> deleteStudentByRollNumber(int rollNumber);

        Mono<Result> saveResult(Result result);

        Mono<Result> getResultByRollNumber(int parseInt);
    }
