package com.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import lombok.Getter;

@Getter
public class Shop {

    private String shopName;

    public Shop(String shopName) {
        this.shopName = shopName;
    }

    private double calculatePrice(String product) {
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    private void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public Future<Double> getPriceAsync(String product) {
        final CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            final double price = calculatePrice(product);
            futurePrice.complete(price);
        }).start();
        return futurePrice;
    }

    // 비동기 로직을 처리 시 Exception이 발생하면 이 메서드를 호출한 스레드에 Exception을 전파해야 함
    public Future<Double> getPriceAsyncV2(String product) {
        final CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                final double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception exception) {
                futurePrice.completeExceptionally(exception);
            }
        }).start();
        return futurePrice;
    }

    // 팩토리메서드로 비동기 CompletableFuture 사용하기
    public Future<Double> getPriceAsyncV3(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    private static void doSomethingElse() {
        System.out.println("do someThing Else");
        System.out.println("do someThing Else");
        System.out.println("do someThing Else");
        System.out.println("do someThing Else");
        System.out.println("do someThing Else");
        System.out.println("do someThing Else");
        System.out.println("do someThing Else");
        System.out.println("do someThing Else");
        System.out.println("do someThing Else");
    }

    public static void main(String[] args) {
        final Shop shop = new Shop("BestShop");
        final long start = System.nanoTime();
        // 비동기 코드 start
        final Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        final long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("invocation returned after " + invocationTime + " msecs");
        // 비동기 코드(메서드)는 바로 반환되고 해당 메서드의 다음 작업을 한다
        doSomethingElse();
        doSomethingElse();
        doSomethingElse();
        doSomethingElse();
        doSomethingElse();

        try {
            // 위의 작업이 다 끝나면 future에 값이 반환될때까지 block 한다.
            final Double price = futurePrice.get();
            System.out.printf("price is %.2f\n", price);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        final long retrievalTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }
}
