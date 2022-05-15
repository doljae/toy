package com.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

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

    final Future<Double> futurePrice(String product) {
        return CompletableFuture.supplyAsync(() -> new Shop("a").getPrice(product))
                                .thenCombine( // 서로 결과에 영향을 주지 않는 두 비동기 메서드를 a, b를 실행 후 b의 결과를 a 스레드로 가져와서 결합
                                              // .thenCompose()는 b 작업은 a 작업의 결과물이 필요할 경우 a 작업의 결과를 기다려 받아 b 작업을 수행할 때 사용함
                                              // thenComposeAsync(), thenCombineAsync()도 있긴한데 굳이 다른 스레드에서 context switching 시킬 필요 없으면 안 쓰는게 좋음, 운 나쁘면 새 스레드 사용을 위해 대기해야함
                                              CompletableFuture.supplyAsync(
                                                  () -> new Shop("b").getPrice(product)),
                                              (a, b) -> a * b)
                                .orTimeout(1000L, TimeUnit.MILLISECONDS); // 이 작업에 대해서 timeout 설정
    }
}
