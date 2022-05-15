package com.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BestPriceFinder {

    private final List<Shop2> shops = Arrays.asList(
        new Shop2("BestPrice"),
        new Shop2("LetsSaveBig"),
        new Shop2("MyFavoriteShop"),
        new Shop2("BuyItAll"),
        new Shop2("ShopEasy"));

    private final Executor executor = Executors.newFixedThreadPool(shops.size(), (Runnable r) -> {
        Thread t = new Thread(r);
        // CompletableFuture의 장점1, Executor를 사용해 커스터마이징
        // 이 pool의 스레드를 데몬스레드로 만듦, 일반 스레드와 성능은 같으나 자바 프로그램이 종료될 때 강제로 실행이 종료될 수 있음
        // 일반 스레드가 살아있으면 절대 죽지않음, 무한 대기
        return t;
    });

    public List<String> findPricesSequential(String product) {
        return shops.stream()
                    .map(shop -> shop.getPrice(product))
                    .map(Quote::parse)
                    .map(Discount::applyDiscount)
                    .collect(Collectors.toList());
    }

    public List<String> findPricesParallel(String product) {
        return shops.parallelStream()
                    .map(shop -> shop.getPrice(product))
                    .map(Quote::parse)
                    .map(Discount::applyDiscount)
                    .collect(Collectors.toList());
    }

    public List<String> findPricesFuture(String product) {
        List<CompletableFuture<String>> priceFutures = findPricesStream(product)
            .collect(Collectors.<CompletableFuture<String>>toList());

        return priceFutures.stream()
                           .map(CompletableFuture::join)
                           .collect(Collectors.toList());
    }

    public Stream<CompletableFuture<String>> findPricesStream(String product) {
        return shops.stream()
                    .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                    .map(future -> future.thenApply(Quote::parse))
                    .map(future -> future.thenCompose(
                        quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));
    }

    public void printPricesStream(String product) {
        long start = System.nanoTime();
        CompletableFuture[] futures = findPricesStream(product)
            .map(f -> f.thenAccept(s -> System.out.println(
                s + " (done in " + ((System.nanoTime() - start) / 1_000_000) + " msecs)")))
            .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures).join();
        System.out.println(
            "All shops have now responded in " + ((System.nanoTime() - start) / 1_000_000) + " msecs");
    }

    public static void main(String[] args) {
        BestPriceFinder bestPriceFinder = new BestPriceFinder();
        System.out.println(bestPriceFinder.findPricesSequential("apple"));
        System.out.println();
        System.out.println(bestPriceFinder.findPricesStream("apple"));
        System.out.println();
        System.out.println(bestPriceFinder.findPricesParallel("apple"));
        System.out.println();
        System.out.println(bestPriceFinder.findPricesFuture("apple"));
        System.out.println();
        bestPriceFinder.printPricesStream("apple");
    }

}
