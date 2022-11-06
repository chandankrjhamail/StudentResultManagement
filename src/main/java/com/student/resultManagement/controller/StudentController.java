package com.student.resultManagement.controller;

import com.student.resultManagement.entity.Student;
import com.student.resultManagement.exception.StudentResultManagementException;
import com.student.resultManagement.repository.StudentRepository;
import com.student.resultManagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    @PostMapping("/controller/students")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<Student> save(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @GetMapping("/controller/students/{rollNumber}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<Student> save(@PathVariable int rollNumber) {
        return studentRepository.findByRollNumber(rollNumber);
    }
}
