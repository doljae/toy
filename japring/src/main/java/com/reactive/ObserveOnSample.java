package com.reactive;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subscribers.ResourceSubscriber;

public class ObserveOnSample {

    public static void main(String[] args) throws InterruptedException {
        final Flowable<Long> flowable = Flowable.interval(300L, TimeUnit.MILLISECONDS)
                                                .onBackpressureDrop();

        // 받는 쪽의 버퍼가 1이고 생산하는 쪽의 전략이 BackPressureDrop이기 때문에 아무리 많이 주려고 해도
        // 버퍼 사이즈만큼만 받을 수 있고 나머진 버려짐
        flowable.observeOn(Schedulers.computation(), false, 2)
                .subscribe(new ResourceSubscriber<Long>() {
                    @Override
                    public void onNext(Long item) {
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            System.exit(1);
                        }

                        final String threadName = Thread.currentThread().getName();
                        System.out.println(threadName + ": " + item);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }

                });
        Thread.sleep(7000L);
    }
}
