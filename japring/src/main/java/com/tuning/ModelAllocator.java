package com.tuning;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ModelAllocator implements Runnable {

    private volatile boolean shutdown = false;

    private double chanceOfLongLived = 0.02;
    private int multiplierForLongLived = 20;
    private int x = 1024;
    private int y = 1024;
    private int mbPerSec = 50;
    private int shortLivedMs = 100;
    private int nThreads = 8;
    private Executor executor = Executors.newFixedThreadPool(nThreads);

    @Override
    public void run() {

        final int mainSleep = (int) (1000.0 / mbPerSec);

        while (!shutdown) {
            for (int i = 0; i < mbPerSec; i++) {
                ModelObjectAllocation to = new ModelObjectAllocation(x, y, lifeTime());
                executor.execute(to);
                try {
                    Thread.sleep(mainSleep);
                } catch (InterruptedException e) {
                    shutdown = true;
                }
            }
        }
    }

    public int lifeTime() {
        if (Math.random() < chanceOfLongLived) {
            return multiplierForLongLived * shortLivedMs;
        }

        return shortLivedMs;
    }

    public static void main(String[] args) {
        new ModelAllocator().run();
    }
}
