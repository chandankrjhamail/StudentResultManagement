package com.student.resultManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import java.time.Instant;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    @Id
    private String _id;
    @NonNull
    private int totalMarks;
    @NonNull
    private int obtainedMarks;
    @NonNull
    private int rollNumber;
    private int grade;
    private String remarks;
    private int positionInClass;
    private Instant createdOn;
    private Instant updatedOn;
    private Object test;

}
