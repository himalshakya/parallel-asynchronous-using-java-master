package com.learnjava.parallelsstreams;

import java.util.stream.IntStream;

import static com.learnjava.util.CommonUtil.*;
import static com.learnjava.util.LoggerUtil.log;

public class ParallelStreamPerformance {

    public int sum_using_intstream(int count, boolean isParallel){
        startTimer();
        IntStream intStream = IntStream.rangeClosed(0, count);

        if (isParallel){
            intStream.parallel();
        }

        int sum = intStream.sum();
        timeTaken();
        stopWatchReset();
        return sum;
    }

//    public int sum_using_list(int count, boolean isParallel){
//        startTimer();
//        IntStream intStream = IntStream.rangeClosed(0, count);
//
//        if (isParallel){
//            intStream.parallel();
//        }
//
//        int sum = intStream
//                .map(x -> Integer.)
//                .sum();
//        timeTaken();
//        stopWatchReset();
//        return sum;
//    }

    public static void main(String[] args) {
        ParallelStreamPerformance parallelStreamPerformance = new ParallelStreamPerformance();
        int sum1 = parallelStreamPerformance.sum_using_intstream(10, false);
        log("Sum : " + sum1);
        int sum2 = parallelStreamPerformance.sum_using_intstream(10, true);
        log("Sum : " + sum2);
    }
}
