package com.student.resultManagement.service;

import com.student.resultManagement.entity.Result;
import com.student.resultManagement.entity.Student;
import com.student.resultManagement.exception.StudentResultManagementException;
import com.student.resultManagement.repository.ResultRepository;
import com.student.resultManagement.repository.StudentRepository;
import com.student.resultManagement.validator.StudentValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Service
@Slf4j
public class StudentManagementServiceImpl implements StudentManagementService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    Sinks.Many<String> sink;

    @Override
    public Mono<Student> saveStudent(Student student) {
        StudentValidator.validate(student);
        student.setStatus("Active");
        return studentRepository.save(student);
    }

    @Override
    public Mono<Student> findStudentByRollNumber(int rollNumber) {
        return studentRepository.findByRollNumber(rollNumber)
                .switchIfEmpty(Mono.error(new StudentResultManagementException("Student is not present with Roll Number : " + rollNumber)));
    }

    @Override
    public Flux<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Mono<Student> deleteStudentByRollNumber(int rollNumber) {
        StudentValidator.validStudent(rollNumber);
        return studentRepository.findByRollNumber(rollNumber)
                .map(student -> {
                    student.setStatus("Deleted");
                    return student;
                }).flatMap(student -> studentRepository.save(student))
                .switchIfEmpty(Mono.error(new StudentResultManagementException("Student is not present with Roll Number : " + rollNumber)));
    }

    @Override
    public Mono<Result> saveResult(Result result) {
        return computePositionInClass(result);
    }

    @Async
    private Mono<Result> computePositionInClass(Result result) {
        result.setPositionInClass(1);
        Mono<Result> resultMono = resultRepository.findByRollNumber(result.getRollNumber())
                .defaultIfEmpty(result)
                .flatMap(alreadyPresentResult -> {
                    // Roll Number validation
                    if(result.getRollNumber() < 1 || result.getRollNumber() > 100)
                        return Mono.error(new StudentResultManagementException("Roll Number should be within 1 to 100, but now passed: " + result.getRollNumber()));
                    // Grade validation
                    if(result.getGrade() < 1 || result.getGrade() > 10)
                        return Mono.error(new StudentResultManagementException("Grade should be within 1 to 10, but now passed: " + result.getGrade()));
                    if(alreadyPresentResult.get_id() == null) {
                        return resultRepository.findAllByObtainedMarksBetweenOrderByObtainedMarksDesc(0, result.getObtainedMarks())
                                .flatMap(result1 -> {
                                    result1.setPositionInClass(result1.getPositionInClass() + 1);
                                    return resultRepository.save(result1);
                                }).take(1).next().flatMap(result1 -> {
                                    result.setPositionInClass(result1.getPositionInClass() - 1);
                                    return resultRepository.save(result);
                                }).switchIfEmpty(resultRepository.findAllByOrderByObtainedMarksAsc().take(1).next().flatMap(result1 -> {
                                    result.setPositionInClass(result1.getPositionInClass() + 1);
                                    return resultRepository.save(result);
                                }).switchIfEmpty(resultRepository.save(result)));
                    }
                    else {
                        return Mono.error(new StudentResultManagementException("Result is already present with Roll Number : " + result.getRollNumber()));
                    }
                });
        return resultMono;


    }

    @Override
    public Mono<Result> getResultByRollNumber(int rollNumber) {
        return resultRepository.findByRollNumber(rollNumber)
                .map(result -> {
                    sink.emitNext(result.toString(), Sinks.EmitFailureHandler.FAIL_FAST);
                    return result;
                });
    }

}
