package com.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Shop {

    private String shopName;

    public Shop(String shopName) {
        this.shopName = shopName;
    }

    public Future<Double> getPriceAsync(String product) {
        final CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            final double price = calculatePrice(product);
            futurePrice.complete(price);
        }).start();
        return futurePrice;
    }

    public double getPrice(String product) {
        return calculatePrice(product);
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
