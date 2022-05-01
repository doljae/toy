package com.future;

public class MergeFlow {

    public static void main(String[] args) {

        final SimpleCell c3 = new SimpleCell("C3");
        final SimpleCell c2 = new SimpleCell("C2");
        final SimpleCell c1 = new SimpleCell("C1");

        c1.subscribe(c3);
        c1.onNext(10);
        c2.onNext(20);
    }
}
