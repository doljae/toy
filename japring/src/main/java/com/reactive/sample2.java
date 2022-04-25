package com.reactive;

import io.reactivex.rxjava3.core.Flowable;

public class sample2 {
    public static void main(String[] args) {
        final Flowable<Integer> flowable = Flowable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                                                   .filter(data -> data % 2 == 0)
                                                   .map(data -> data * 100);

        flowable.subscribe(data -> System.out.println("data=" + data));

        final Flowable<Long> fixedFlowable = Flowable.just(System.currentTimeMillis());
        final Flowable<Long> dynamicFlowable = Flowable.fromCallable(System::currentTimeMillis);

    }
}
