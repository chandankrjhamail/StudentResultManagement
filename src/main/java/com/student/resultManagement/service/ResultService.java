package com.student.resultManagement.service;

import com.student.resultManagement.entity.Result;
import com.student.resultManagement.repository.ResultRepository;
import com.student.resultManagement.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ResultService {

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    ResultUtil resultUtil;


    public Mono<Result> saveResult(Result result) {
        //return resultUtil.populateResult(result);
        return Mono.just(null);
    }
}
