package com.reactive;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.rxjava3.core.Flowable;

public class ViolatedReactiveStream1 {

    public static void main(String[] args) {

        Flowable.range(1, 3)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        System.out.println("Subscription start");
                        s.request(Long.MAX_VALUE);
                        System.out.println("onSubScribe: end");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println(integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("에러=" + t);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("완료");
                    }
                });
    }
}
