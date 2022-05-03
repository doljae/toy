package com.future;

import java.util.Arrays;
import java.util.List;

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

    public static void main(String[] args) {
        System.out.println("==== start ====");
        final long start = System.nanoTime();
        System.out.println(findPrices("iPhone30"));
        final long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }
}
