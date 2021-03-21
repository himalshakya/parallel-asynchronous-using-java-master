package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;
import static com.learnjava.util.LoggerUtil.log;

import static com.learnjava.util.CommonUtil.*;

public class CompletableFutureHelloWorldException {

    private HelloWorldService hws;

    public CompletableFutureHelloWorldException(HelloWorldService hws) {
        this.hws = hws;
    }

    public String helloWorld_3_async_calls_handle(){

        startTimer();

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(
                () -> {
                    delay(1000);
                    return " Hi CompletableFuture!";
                });

        String hw = hello
                .handle((res, e) ->{
                    if (e != null){
                        log("Exception is : " + e.getMessage());
                        return "";
                    }
                    return res;
                })
                .thenCombine(world, (h, w) -> h+w)
                .handle((res, e) ->{
                    if (e != null){
                        log("Exception is : " + e.getMessage());
                        return "";
                    }
                    return res;
                })
                .thenCombine(hiCompletableFuture, (previous, current) -> previous + current)
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();

        return hw;
    }

    public String helloWorld_3_async_calls_exceptionally(){

        startTimer();

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(
                () -> {
                    delay(1000);
                    return " Hi CompletableFuture!";
                });

        String hw = hello
                .exceptionally((e) ->{
                    log("Exception is : " + e.getMessage());
                    return "";
                })
                .thenCombine(world, (h, w) -> h+w)
                .exceptionally((e) ->{
                    log("Exception after : " + e.getMessage());
                    return "";
                })
                .thenCombine(hiCompletableFuture, (previous, current) -> previous + current)
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();

        return hw;
    }

    public String helloWorld_3_async_calls_whencomplete(){

        startTimer();

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(
                () -> {
                    delay(1000);
                    return " Hi CompletableFuture!";
                });

        String hw = hello
                .whenComplete((res, e) ->{
                    if (e != null){
                        log("Exception is : " + e.getMessage());
                    }
                })
                .thenCombine(world, (h, w) -> h+w)
                .whenComplete((res, e) ->{
                    if (e != null){
                        log("Exception is : " + e.getMessage());
                    }
                })
                .exceptionally(e -> {
                    log("Exception after thenCombine is : " + e.getMessage());
                    return "";
                })
                .thenCombine(hiCompletableFuture, (previous, current) -> previous + current)
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();

        return hw;
    }
}
