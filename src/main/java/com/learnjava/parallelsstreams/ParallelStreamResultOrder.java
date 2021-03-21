package com.learnjava.parallelsstreams;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import static com.learnjava.util.LoggerUtil.log;

public class ParallelStreamResultOrder {

    public static List<Integer> listOrder(List<Integer> inputList){

        return inputList.parallelStream()
                .map(integer -> integer * 2)
                .collect(Collectors.toList());
    }

    public static int reduce(){
        int sum = List.of(1,2,3,4)
                .stream()
                .reduce(0, (x, y) -> x + y);


        return  sum;

    }

    public static void main(String[] args) {
        List<Integer> inputList = List.of(1,2,3,4,5,6,7,8);
        log("inputList " + inputList);
        List<Integer> result = listOrder(inputList);
        log("result : " + result);

        int sum = reduce();
        log("Sum : " + sum);
    }
}
