package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.*;

class CompletableFutureHelloWorldTest {

    HelloWorldService hws = new HelloWorldService();
    CompletableFutureHelloWorld cfhw = new CompletableFutureHelloWorld(hws);

    @Test
    void helloWorld() {

        CompletableFuture<String> completableFuture = cfhw.helloWorld();

        completableFuture
                .thenAccept(s -> {
                    assertEquals("HELLO WORLD", s);
                })
                .join();
    }

    @Test
    void helloWorld_withSize(){
        CompletableFuture<String> completableFuture = cfhw.helloWorld_withSize();

        completableFuture
                .thenAccept(s -> {
                    assertEquals("11 - HELLO WORLD", s);
                })
                .join();
    }

    @Test
    void helloWorld_async() {

        String helloworld = cfhw.helloWorld_multiple_async_calls();
        assertEquals("HELLO WORLD!", helloworld);
    }

    @Test
    void helloWorld_async2() {

        String helloworld = cfhw.helloWorld_multiple_async_calls2();
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", helloworld);
    }

    @Test
    void helloWorld_async_log() {

        String helloworld = cfhw.helloWorld_multiple_async_calls_log();
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", helloworld);
    }

    @Test
    void helloWorld_async_log_async_callable() {

        String helloworld = cfhw.helloWorld_multiple_async_calls_log_async_callable();
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", helloworld);
    }

    @Test
    void helloWorld_async_log_async_callable_2() {

        String helloworld = cfhw.helloWorld_multiple_async_calls_log_async_callable_executorService();
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", helloworld);
    }

    @Test
    void helloWorld_async_log_thread_pool() {

        String helloworld = cfhw.helloWorld_multiple_async_calls_custom_threadpool();
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", helloworld);
    }

    @Test
    void helloWorld_async3() {

        String helloworld = cfhw.helloWorld_4_async_calls();
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE! ADDITIONAL WORDS!", helloworld);
    }

    @Test
    void helloWorld_thenCompose() {

        startTimer();

        CompletableFuture<String> completableFuture = cfhw.helloWorld_thenCompose();

        completableFuture
                .thenAccept(s -> {
                    assertEquals("HELLO WORLD!", s);
                })
                .join();

        timeTaken();
    }
}