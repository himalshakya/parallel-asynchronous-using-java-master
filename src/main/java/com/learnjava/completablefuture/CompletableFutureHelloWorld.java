package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//import static com.learnjava.util.CommonUtil.delay;
import static com.learnjava.util.CommonUtil.delay;
import static com.learnjava.util.LoggerUtil.log;

public class CompletableFutureHelloWorld {

    private HelloWorldService hws;

    public CompletableFutureHelloWorld(HelloWorldService hws) {
        this.hws = hws;
    }

    public CompletableFuture<String> helloWorld() {

        return CompletableFuture
                .supplyAsync(hws::helloWorld)
                .thenApply(String::toUpperCase);
//                .thenAccept(result -> {
//                    log("Result is " + result);
//                })
//                .join();

//        log("Done...");
//        delay(2000);
    }

    public CompletableFuture<String> helloWorld_withSize(){
        return CompletableFuture
                .supplyAsync(hws::helloWorld)
                .thenApply(result -> {
                    return result.length() + " - " + result.toUpperCase();
                });
    }

    public String helloWorld_approach(){
        String hello = hws.hello();
        String world = hws.world();
        return hello + world;
    }

    public String helloWorld_multiple_async_calls(){

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());

        return hello.thenCombine(world, (h, w) -> h+w)
                .thenApply(String::toUpperCase)
                .join();
    }

    public String helloWorld_multiple_async_calls2(){

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(
                () -> {
                    delay(1000);
                    return " Hi CompletableFuture!";
                });

        return hello.thenCombine(world, (h, w) -> h+w)
                .thenCombine(hiCompletableFuture, (previous, current) -> previous + current)
                .thenApply(String::toUpperCase)
                .join();
    }

    public String helloWorld_multiple_async_calls_log(){

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(
                () -> {
                    delay(1000);
                    return " Hi CompletableFuture!";
                });

        return hello.thenCombine(world, (h, w) -> {
                    log("thenCombine h/w");
                    return h+w;
                })
                .thenCombine(hiCompletableFuture, (previous, current) -> {
                    log("thenCombine prev/current");
                    return previous + current;
                })
                .thenApply(s -> {
                    log("thenApply for string");
                    return s.toUpperCase();
                })
                .join();
    }

    public String helloWorld_multiple_async_calls_log_async_callable(){

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(
                () -> {
                    delay(1000);
                    return " Hi CompletableFuture!";
                });

        return hello.thenCombineAsync(world, (h, w) -> {
                    log("thenCombine h/w");
                    return h+w;
                })
                .thenCombineAsync(hiCompletableFuture, (previous, current) -> {
                    log("thenCombine prev/current");
                    return previous + current;
                })
                .thenApplyAsync(s -> {
                    log("thenApply for string");
                    return s.toUpperCase();
                })
                .join();
    }

    public String helloWorld_multiple_async_calls_log_async_callable_executorService(){

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello(), executorService);
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world(), executorService);
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(
                () -> {
                    delay(1000);
                    return " Hi CompletableFuture!";
                },executorService);

        return hello.thenCombineAsync(world, (h, w) -> {
                    log("thenCombine h/w");
                    return h+w;
                }, executorService)
                .thenCombineAsync(hiCompletableFuture, (previous, current) -> {
                    log("thenCombine prev/current");
                    return previous + current;
                }, executorService)
                .thenApplyAsync(s -> {
                    log("thenApply for string");
                    return s.toUpperCase();
                }, executorService)
                .join();
    }

    public String helloWorld_multiple_async_calls_custom_threadpool(){
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello(), executorService);
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world(), executorService);
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(
                () -> {
                    delay(1000);
                    return " Hi CompletableFuture!";
                }, executorService);

        return hello.thenCombine(world, (h, w) -> {
            log("thenCombine h/w");
            return h+w;
        })
                .thenCombine(hiCompletableFuture, (previous, current) -> {
                    log("thenCombine prev/current");
                    return previous + current;
                })
                .thenApply(s -> {
                    log("thenApply for string");
                    return s.toUpperCase();
                })
                .join();
    }

    public String helloWorld_4_async_calls(){

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(
                () -> {
                    delay(1000);
                    return " Hi CompletableFuture!";
                });
        CompletableFuture<String> hiCompletableFuture2 = CompletableFuture.supplyAsync(
                () -> {
                    delay(1000);
                    return " additional words!";
                });

        return hello.thenCombine(world, (h, w) -> h+w)
                .thenCombine(hiCompletableFuture, (previous, current) -> previous + current)
                .thenCombine(hiCompletableFuture2, (previous, current) -> previous + current)
            .thenApply(String::toUpperCase)
            .join();
    }

    public CompletableFuture<String> helloWorld_thenCompose(){

        return CompletableFuture.supplyAsync(hws::hello)
                .thenCompose((previous) -> hws.worldFuture(previous))
                .thenApply(String::toUpperCase);
    }
}
