package com.example.study;

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
        Observable<Integer> observable = Observable.create(emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
            emitter.onComplete();
            emitter.onNext(4);
        });

        observable.subscribe(integer -> System.out.println("onNext: " + integer),
                             throwable -> System.out.println("onError: " + throwable),
                             () -> System.out.println("onComplete")
        );
    }

    @Test
    void just() {
        Disposable s1 = Observable.just(1, 2, 3).subscribe(System.out::println);
        Disposable s2 = Observable.just(1, 2, 3).map(x -> x * 10).subscribe(System.out::println);
    }

    @Test
    void create() {
        Disposable subscribe = Observable.create(emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
            emitter.onComplete();
        }).subscribe(System.out::println);
    }
}
