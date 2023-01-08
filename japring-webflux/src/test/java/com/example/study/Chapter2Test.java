package com.example.study;

import java.util.List;

import org.junit.jupiter.api.Test;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class Chapter2Test {

    @Test
    void test1() {
        Disposable subscribe = Observable.create(emitter -> {
            emitter.onNext("Hello");
            emitter.onNext("World");
            emitter.onNext("World");
            emitter.onNext("World");
            emitter.onNext("World");
            emitter.onNext("World");
            emitter.onNext("World");
            emitter.onComplete();
        }).subscribe(System.out::println, System.out::println, () -> System.out.println("Done"));
    }

    @Test
    void test2() {
//        Observable.concat(List.of("hello", "world")).forEach(System.out::print);
    }
}
