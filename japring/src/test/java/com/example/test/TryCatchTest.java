package com.example.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TryCatchTest {

    @Test
    void test() {
        // fail
        Assertions.assertThrows(AccountNotVerifiedException.class, this::testMethod);
    }

    private void testMethod() {
        try {
            System.out.println("do something");
            if (true) {
                throw new AccountTokenExpiredException();
            }
        } catch (Exception e) {
            throw new AccountNotVerifiedException();
        }
    }

    static class AccountTokenExpiredException extends RuntimeException {}

    ;

    static class AccountNotVerifiedException extends RuntimeException {}

    ;
}
