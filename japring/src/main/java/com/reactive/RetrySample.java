package com.reactive;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;

public class RetrySample {

    public static void main(String[] args) {
        final Flowable<Integer> flowable = Flowable.<Integer>create(emitter -> {
            System.out.println("Flowable 처리 시작");
            for (int i = 1; i <= 3; i++) {
                if (i == 2) {
                    throw new Exception("예외 발생");
                }
                emitter.onNext(i);
            }
            emitter.onComplete();
            System.out.println("Flowable 처리 완료");
        }, BackpressureStrategy.BUFFER).doOnSubscribe(subscription -> {
            System.out.println("flowable: doOnSubscribe");
        }).retry(2);

        flowable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("subscriber: onSubscribe");
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("data=" + integer);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("error=" + t);
            }

            @Override
            public void onComplete() {
                System.out.println("종료");
            }
        });
    }
}