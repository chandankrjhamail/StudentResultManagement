package com.student.resultManagement.util;

import org.springframework.stereotype.Component;

@Component
public class ResultUtil {
//
//    @Autowired
//    static
//    ResultRepository resultRepository;
//
//    public static Mono<Result> populateResult() {
//
//        // populating Remarks
//        if(result.getObtainedMarks() >= 50)
//            result.setRemarks("passed");
//        else
//            result.setRemarks("failed");
//
//        result.setPositionInClass(1);
//
//        // Populating PositionInClass
//        Flux<Result> resultFlux = resultRepository.findByObtainedMarksBetweenOrderByObtainedMarksDesc(0, result.getObtainedMarks());
//
//        List<Result> collect1 = resultFlux.toStream().collect(Collectors.toList());
//
//        for (int i=0; i<collect1.size();i++){
//            collect1.get(i).setPositionInClass(i+1);
//        }
//
//        Flux.just(collect1).flatMap(c->resultRepository.saveAll(c));
//
//
//    }
//
//    @Async
//    public synchronized void computeNewResult() {
//        resultRepository.findByObtainedMarksOrderByObtainedMarksDesc
//    }
//
//    private Publisher<?> getX(Result s) {
//    }
//
//    Flux<Result> collect = resultFlux.toStream().map(s -> updatePosition(s, position))
//        resultFlux.take(1).map(result1 ->  {
//            result.setPositionInClass(result1.getPositionInClass());
//            return result1;
//        });
//
//        Flux<Result> map1 = resultFlux.map(result1 -> {
//            result1.setPositionInClass(result1.getPositionInClass() + 1);
//            resultRepository.save(result1);
//            return result1;
//        });
//
//        return resultRepository.save(result);
//    }
//
//    private Mono<Result> updatePosition(Result s, Integer position) {
//        Result result= new Result();
//        result.setPositionInClass(position++);
//        return Mono.just(result);
//    }

}
