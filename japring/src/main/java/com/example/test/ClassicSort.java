package com.example.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ClassicSort {

    private static final int N = 1_000;
    private static final int I = 150_000;
    private static final List<Integer> testData = new ArrayList<>();
    // -Xms2048m -Xms2048m -XX:+PrintCompilation
    // -Xms2048m -Xms2048m -verbose:gc

    public static void main(String[] args) {
        final Random random = new Random();
        for (int i = 0; i < N; i++) {
            testData.add(random.nextInt(Integer.MAX_VALUE));
        }

        System.out.println("정렬 알고리즘 테스트");

        final double startTime = System.nanoTime();
        for (int i = 0; i < I; i++) {
            final List<Integer> copy = new ArrayList<>(testData);
            Collections.sort(copy);
        }

        final double endTime = System.nanoTime();
        final double timePerOperation = (endTime - startTime) / (1000000000L * I);
        System.out.println("결과: " + (1 / timePerOperation) + " op/s");

    }
}
