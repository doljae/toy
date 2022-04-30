package com.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // efficient CompletableFuture

        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        int x = 1337;

        final CompletableFuture<Integer> a = new CompletableFuture<>();
        final CompletableFuture<Integer> b = new CompletableFuture<>();
        final CompletableFuture<Integer> c = a.thenCombine(b, (y, z) -> y + z);

        executorService.submit(() -> a.complete(f(x)));
        executorService.submit(() -> b.complete(g(x)));

        System.out.println(c.get());
        executorService.shutdown();

    }

    private static Integer g(int x) {
        return null;
    }

    private static Integer f(int x) {
        return null;
    }
}
