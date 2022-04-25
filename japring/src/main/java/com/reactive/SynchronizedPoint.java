package com.reactive;

public class SynchronizedPoint {

    private final Object lockObject = new Object();

    private int x;
    private int y;

    void rightUp() {
        synchronized (lockObject) {
            x += 1;
            y += 1;
        }
    }

    int getX() {
        synchronized (lockObject) {
            return x;
        }
    }

    int getY() {
        synchronized (lockObject) {
            return y;
        }
    }
}
