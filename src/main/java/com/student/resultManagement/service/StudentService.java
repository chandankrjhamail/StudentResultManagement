package com.student.resultManagement.service;

import com.student.resultManagement.entity.Student;
import com.student.resultManagement.repository.StudentRepository;
import com.student.resultManagement.util.StudentUtil;
import com.student.resultManagement.validator.StudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Mono<Student> saveStudent(Student student) {
        StudentValidator.validate(student);
        student = StudentUtil.setStudentStatusActive(student);
        return studentRepository.save(student);
    }
}
