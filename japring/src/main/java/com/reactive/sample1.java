package com.reactive;

import io.reactivex.rxjava3.core.Flowable;

public class sample1 {

    public static void main(String[] args) {
        final Flowable<String> flowable = Flowable.just("Hello", "World");
        flowable.subscribe(System.out::println);
    }
}
