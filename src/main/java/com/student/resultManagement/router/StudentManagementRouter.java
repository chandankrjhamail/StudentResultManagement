package com.student.resultManagement.router;

import com.student.resultManagement.handler.StudentManagementHandler;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class StudentManagementRouter {

    @Bean
    public RouterFunction<ServerResponse> routeStudentManagement(StudentManagementHandler studentManagementHandler) {
        return RouterFunctions
                .route(RequestPredicates.POST("/students")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), studentManagementHandler::saveStudent)
                .andRoute(RequestPredicates.GET("/students/{rollNumber}")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), studentManagementHandler::findStudentByRollNumber)
                .andRoute(RequestPredicates.GET("/students")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), studentManagementHandler::findAllStudents)
                .andRoute(RequestPredicates.DELETE("/students/{rollNumber}")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), studentManagementHandler::deleteStudentByRollNumber)
                .andRoute(RequestPredicates.POST("/results")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), studentManagementHandler::saveResult)
                .andRoute(RequestPredicates.GET("/students/result/{rollNumber}")
                    .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), studentManagementHandler::getResultByRollNumber);
    }

    @Bean
    public WebProperties.Resources resources(){
        return new WebProperties.Resources();
    }
}
