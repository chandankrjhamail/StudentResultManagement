package com.student.resultManagement.handler;


import com.student.resultManagement.exception.StudentResultManagementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {


    @ExceptionHandler(StudentResultManagementException.class)
    public ResponseEntity<?> handleStudentResultManagementException(StudentResultManagementException resultException){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error message", resultException.getMessage());
        errorMap.put("status", HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.ok(errorMap);
    }
}
