package com.reactive;

import java.util.concurrent.TimeUnit;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FlowableSample2 {
    public static void main(String[] args) throws InterruptedException {

        Flowable.interval(200L, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.computation())
                .subscribe(new Subscriber<>() {
                    private Subscription subscription;
                    private long startTime;

                    @Override
                    public void onSubscribe(Subscription s) {
                        subscription = s;
                        startTime = System.currentTimeMillis();
                        subscription.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Long data) {
                        if (System.currentTimeMillis() - startTime > 2000) {
                            System.out.println("구독 해지 호출");
                            subscription.cancel();
                            System.out.println("구독 해지");
                            return;
                        }
                        System.out.println("data=" + data);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        final String threadName = Thread.currentThread().getName();
                        System.out.println(threadName + ": " + "완료");
                    }
                });

        Thread.sleep(2000L);
    }
}
