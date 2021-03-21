package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletionException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CompletableFutureHelloWorldExceptionTest {

    @Mock
    HelloWorldService helloWorldService;

    @InjectMocks
    CompletableFutureHelloWorldException cfhwe;


    @Test
    void helloWorld_3_async_calls_handle() {

        Mockito.when(helloWorldService.hello()).thenThrow(new RuntimeException(("Exception Occurred")));
        Mockito.when(helloWorldService.world()).thenCallRealMethod();

        String result = cfhwe.helloWorld_3_async_calls_handle();

        assertEquals(" WORLD! HI COMPLETABLEFUTURE!", result);

    }

    @Test
    void helloWorld_3_async_calls_handle_2() {

        Mockito.when(helloWorldService.hello()).thenThrow(new RuntimeException(("Exception Occurred")));
        Mockito.when(helloWorldService.world()).thenThrow(new RuntimeException(("Exception Occurred")));

        String result = cfhwe.helloWorld_3_async_calls_handle();

        assertEquals(" HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloWorld_3_async_calls_handle_3() {

        Mockito.when(helloWorldService.hello()).thenCallRealMethod();
        Mockito.when(helloWorldService.world()).thenCallRealMethod();

        String result = cfhwe.helloWorld_3_async_calls_handle();

        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloWorld_3_async_calls_handle_4() {

        Mockito.when(helloWorldService.hello()).thenThrow(new RuntimeException(("Exception Occurred")));
        Mockito.when(helloWorldService.world()).thenThrow(new RuntimeException(("Exception Occurred")));

        String result = cfhwe.helloWorld_3_async_calls_exceptionally();

        assertEquals(" HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloWorld_3_async_calls_handle_5() {

        Mockito.when(helloWorldService.hello()).thenCallRealMethod();
        Mockito.when(helloWorldService.world()).thenCallRealMethod();

        String result = cfhwe.helloWorld_3_async_calls_handle();

        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloWorld_3_async_calls_handle_6() {

        Mockito.when(helloWorldService.hello()).thenCallRealMethod();
        Mockito.when(helloWorldService.world()).thenCallRealMethod();

        String result = cfhwe.helloWorld_3_async_calls_whencomplete();

        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloWorld_3_async_calls_handle_2_1() {

        Mockito.when(helloWorldService.hello()).thenThrow(new RuntimeException(("Exception Occurred")));
        Mockito.when(helloWorldService.world()).thenThrow(new RuntimeException(("Exception Occurred")));

        String result = cfhwe.helloWorld_3_async_calls_whencomplete();
        assertEquals(" HI COMPLETABLEFUTURE!", result);
    }


}