package com.student.resultManagement.util;

import com.student.resultManagement.entity.Student;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class StudentUtil {

    public static Student setStudentStatusActive(Student student) {

        // populating Status
        student.setStatus("Active");
        return student;
    }

    public static Mono<Student> setStudentStatusDelete(Mono<Student> studentMono) {

        // populating Status
        studentMono.map(student -> {
            student.setStatus("Deleted");
            return student;
        });
        return studentMono;
    }

}
