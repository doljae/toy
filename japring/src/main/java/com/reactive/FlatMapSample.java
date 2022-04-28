package com.reactive;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Flowable;

public class FlatMapSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<String> flowable = Flowable.just("A", "B", "C")
                                            .flatMap(data -> Flowable.just(data).delay(2000L, TimeUnit.MILLISECONDS));

        flowable.subscribe(data -> {
            final String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": " + data);
        });

        Thread.sleep(4000L);
    }
}
