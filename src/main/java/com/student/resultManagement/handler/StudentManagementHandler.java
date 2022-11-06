package com.student.resultManagement.handler;

import com.student.resultManagement.entity.Result;
import com.student.resultManagement.entity.Student;
import com.student.resultManagement.service.StudentManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class StudentManagementHandler {

    @Autowired
    StudentManagementService studentManagementService;

    public Mono<ServerResponse> saveStudent(ServerRequest serverRequest) {
        final Mono<Student> studentMono = serverRequest.bodyToMono(Student.class);
        return studentMono.flatMap(studentManagementService::saveStudent)
                .flatMap(data -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(data));
    }

    public Mono<ServerResponse> findStudentByRollNumber(ServerRequest serverRequest) {
        String rollNumber = serverRequest.pathVariable("rollNumber");
        return studentManagementService.findStudentByRollNumber(Integer.parseInt(rollNumber))
                .flatMap(data -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(data))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findAllStudents(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(studentManagementService.findAllStudents(), Student.class);
    }

    public Mono<ServerResponse> deleteStudentByRollNumber(ServerRequest serverRequest) {
        String rollNumber = serverRequest.pathVariable("rollNumber");
        return studentManagementService.deleteStudentByRollNumber(Integer.parseInt(rollNumber))
                .flatMap(data -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(data))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> saveResult(ServerRequest serverRequest) {
        final Mono<Result> resultMono = serverRequest.bodyToMono(Result.class);
        return resultMono.flatMap(studentManagementService::saveResult)
                .flatMap(data -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(data));
    }

    public Mono<ServerResponse> getResultByRollNumber(ServerRequest serverRequest) {
        String rollNumber = serverRequest.pathVariable("rollNumber");
        return studentManagementService.getResultByRollNumber(Integer.parseInt(rollNumber))
                .flatMap(data -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(data))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
