package com.example.test;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FolkJoinPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // use ExecutorService
//        log.info("use ExecutorService");
//        final ExecutorService executorService = Executors.newFixedThreadPool(5);
//        final List<Future<String>> futures = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//            final int index = i;
//            futures.add(executorService.submit(() -> {
//                Thread.sleep(5000);
//                return Thread.currentThread().getName() + ", index=" + index + ", ended at " + new Date();
//            }));
//        }
//
//        for (Future<String> future : futures) {
//            final String result = future.get();
//            log.info("Thread result: " + result);
//        }
//        executorService.shutdown();

        // use stream + parallel()
//        log.info("use stream + parallel()");
//        IntStream.range(0, 20).parallel().forEach(index -> {
//            System.out.println(
//                "Starting " + Thread.currentThread().getName() + ",    index=" + index + ", " + new Date());
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                log.error("InterruptedException", e);
//            }
//        });

        log.info("custom FolkJoinPool");
        final ForkJoinPool forkJoinPool = new ForkJoinPool(5);
        forkJoinPool.submit(() -> {
            IntStream.range(0, 10).parallel().forEach(index -> {
                System.out.println(
                    "Starting " + Thread.currentThread().getName() + ", index=" + index + ", " + new Date());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {

                }
            });
        }).get();

    }
}

