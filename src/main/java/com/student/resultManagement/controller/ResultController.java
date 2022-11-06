package com.student.resultManagement.controller;

import com.student.resultManagement.entity.Result;
import com.student.resultManagement.handler.CustomWebSocketHandler;
import com.student.resultManagement.repository.ResultRepository;
import com.student.resultManagement.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ResultController {

    @Autowired
    ResultService resultService;

    @Autowired
    ResultRepository resultRepository;

    @PostMapping("/controller/results")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<Result> save(@RequestBody Result result) {
        return resultService.saveResult(result);
    }


}
