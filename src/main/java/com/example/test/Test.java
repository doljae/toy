package com.example.test;

import lombok.Builder;

public class Test {

    public static void main(String[] args) {
        B a = (B) method(0);
    }

    static class A {

    }

    @Builder
    static class B extends A {
        public String methodB() {
            return "b";
        }
    }

    @Builder
    static class C extends A {
        public String methodC() {
            return "c";
        }
    }

    public static A method(int i) {
        if (i > 0) {
            return B.builder().build();
        }
        return C.builder().build();
    }
}
