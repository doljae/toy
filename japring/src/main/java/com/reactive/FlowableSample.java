package com.reactive;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FlowableSample {
    public static void main(String[] args) throws InterruptedException {

        final Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<String> emitter) throws Throwable {

                final String[] datas = { "Hello, World!", "안녕, RxJava!" };

                for (String data : datas) {
                    if (emitter.isCancelled()) {
                        return;
                    }
                    emitter.onNext(data);
                }
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER);

        flowable.observeOn(Schedulers.computation())
                .subscribe(new Subscriber<String>() {
                    private Subscription subscription;

                    @Override
                    public void onSubscribe(Subscription s) {
                        subscription = s;
                        subscription.request(1);
                    }

                    @Override
                    public void onNext(String data) {
                        final String threadName = Thread.currentThread().getName();
                        System.out.println(threadName + ": " + data);
                        subscription.request(1);
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

        Thread.sleep(1000L);
    }
}
