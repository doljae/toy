package com.reactive;

public class SynchronizedPoint2 {

    private int x;
    private int y;

    synchronized void rightUp() {
        x += 1;
        y += 1;

    }

    synchronized int getX() {
        return x;

    }

    synchronized int getY() {
        return y;

    }
}
