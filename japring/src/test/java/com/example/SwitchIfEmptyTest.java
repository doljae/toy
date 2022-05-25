package com.example;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class SwitchIfEmptyTest {
    @Test
    void test() {
        final Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5, 6)
                                       .map(value -> {
                                           System.err.println(".map");
                                           return value;
                                       })
                                       .switchIfEmpty(switchIfEmpty(2));

        flux.subscribe(new Subscriber<Integer>() {
            private Subscription subscription;

            @Override
            public void onSubscribe(Subscription s) {
                subscription = s;
                subscription.request(1L);
            }

            @Override
            public void onNext(Integer integer) {
                System.err.println(integer);
                subscription.request(1L);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    static Mono<Integer> switchIfEmpty(int value) {
        System.err.println(".switchIfEmpty");
        return Mono.just(value);
    }
}
