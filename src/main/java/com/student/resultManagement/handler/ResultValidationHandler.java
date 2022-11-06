package com.student.resultManagement.handler;

import com.student.resultManagement.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ResultValidationHandler extends AbstractValidationHandler<Result, Validator> {

    private ResultValidationHandler(@Autowired Validator validator) {
        super(Result.class, validator);
    }

    @Override
    protected Mono<ServerResponse> processBody(Result validBody, ServerRequest originalRequest) {
        String responseBody = String.format("Hi, %s. Password: %s!", validBody.getRollNumber(), validBody.getObtainedMarks());
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(responseBody), String.class);
    }

}

