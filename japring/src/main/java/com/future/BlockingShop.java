package com.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BlockingShop {

    static final List<Shop> shops = Arrays.asList(new Shop("ShopA"),
                                                  new Shop("ShopB"),
                                                  new Shop("ShopC"),
                                                  new Shop("ShopD"));

    // getPrice() 한 건당 1초 정도 시간이 걸리니 shops.size()만큼 시간이 늘어날 것이다.
    static List<String> findPrices(String product) {
        return shops.stream().map(shop -> String.format("%s price is %.2f", shop.getShopName(),
                                                        shop.getPrice(product))).toList();
    }

    // parallelStream을 이용한 병렬 처리, 1개의 shop을 조회하는 시간보다 아주 약간 더 걸린다.
    static List<String> findPricesV2(String product) {
        return shops.parallelStream().map(shop -> String.format("%s price is %.2f", shop.getShopName(),
                                                                shop.getPrice(product))).toList();
    }

    // CompletableFuture를 이용한 비동기 처리, 별도 스레드에서 비동기로 작업을 처리하고 결과를 blocking해서 묶는다.
    static List<String> findPricesV3(String product) {
        // 1번 map으로 비동기 메서드를 수행해 가격을 각각 계산한다.
        final List<CompletableFuture<String>> pricesFuture =
            shops.stream().map(shop -> CompletableFuture.supplyAsync(() ->
                                                                         String.format("%s price is %.2f",
                                                                                       shop.getShopName(),
                                                                                       shop.getPrice(product)
                                                                         ))).toList();
        // 2번 map으로 수행된 CompletableFuture의 결과를 기다리면서 비동기 메서드가 끝나길 기다린다.
        return pricesFuture.stream().map(CompletableFuture::join).toList();
    }

    public static void main(String[] args) {
        System.out.println("==== blocking method start ====");
        final long start = System.nanoTime();
        System.out.println(findPrices("iPhone30"));
        final long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");

        System.out.println("==== use parallelStream start ====");
        final long start2 = System.nanoTime();
        System.out.println(findPricesV2("iPhone30"));
        final long duration2 = (System.nanoTime() - start2) / 1_000_000;
        System.out.println("Done in " + duration2 + " msecs");

        System.out.println("==== use CompletableFuture start ====");
        final long start3 = System.nanoTime();
        System.out.println(findPricesV3("iPhone30"));
        final long duration3 = (System.nanoTime() - start3) / 1_000_000;
        System.out.println("Done in " + duration3 + " msecs");
    }
}
