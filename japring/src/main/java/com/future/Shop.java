package com.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {

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
}
