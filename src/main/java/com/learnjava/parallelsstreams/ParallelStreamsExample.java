package com.learnjava.parallelsstreams;

import com.learnjava.util.DataSet;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.learnjava.util.CommonUtil.*;
import static com.learnjava.util.LoggerUtil.log;

public class ParallelStreamsExample {

    public List<String> stringTransform(List<String> namesList){
        return namesList
                .parallelStream()
                .map(ParallelStreamsExample::addNameLengthTransform)
                .sequential()
                .parallel()
                .collect(Collectors.toList());
    }

    public List<String> stringTransform_1(List<String> namesList, boolean isParallel){

        Stream<String> namesStream = namesList.stream();

        if(isParallel)
            namesStream.parallel();

        return namesStream
                .map(ParallelStreamsExample::addNameLengthTransform)
                .collect(Collectors.toList());
    }

    public List<String> string_toLowerCase(List<String> namesList){
        return namesList
                .stream()
                .map(name -> name.toLowerCase(Locale.ROOT))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> namesList = DataSet.namesList();
        ParallelStreamsExample parallelStreamsExample = new ParallelStreamsExample();
        startTimer();
//        List<String> resultList = parallelStreamsExample.stringTransform(namesList);
        List<String> resultList = parallelStreamsExample.string_toLowerCase(namesList);
        log("resultList : " + resultList);
        timeTaken();
    }

    private static String addNameLengthTransform(String name) {
        delay(500);
        return name.length()+" - "+name ;
    }
}
