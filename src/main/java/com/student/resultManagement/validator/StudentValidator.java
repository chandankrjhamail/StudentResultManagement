package com.student.resultManagement.validator;

import com.student.resultManagement.entity.Student;
import com.student.resultManagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentValidator {

    @Autowired
    StudentRepository studentRepository;

    public static boolean validate(Student student) {

        // Roll Number validation
        if(student.getRollNumber() < 1 || student.getRollNumber() > 100)
            return false;

        // We need to check if the roll number exists in the db
        if(!validStudent(student.getRollNumber()))
            return false;

        // Grade validation
        if(student.getGrade() < 1 || student.getGrade() > 10)
            return false;

        return true;
    }

    public static boolean validStudent(int rollNumber) {
        /*studentRepository.findByRollNumber(student.getRollNumber())
                .switchIfEmpty(Mono.error(new StudentFoundException(student.getRollNumber())));*/
        return true;
    }

}
