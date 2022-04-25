package com.reactive;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Flowable;

public class sample3 {

    private enum State {
        ADD, MUL
    }

    private static State callMethod;

    public static void main(String[] args) throws InterruptedException {
        callMethod = State.ADD;

        final Flowable<Long> flowable = Flowable.interval(300L, TimeUnit.MILLISECONDS)
                                                .take(7)
                                                .scan((sum, data) -> {
                                                    if (callMethod == State.ADD) {
                                                        return sum + data;
                                                    }
                                                    return sum * data;
                                                });

        flowable.subscribe(data -> System.out.println("data= " + data));

        Thread.sleep(1000);
        System.out.println("계산 방법 변경");
        callMethod = State.MUL;

        Thread.sleep(1000);
    }
}
