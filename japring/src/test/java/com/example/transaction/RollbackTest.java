package com.example.transaction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
public class RollbackTest {

    @Autowired
    RollbackService rollbackService;

    @Test
    void runtimeEx() {
        Assertions.assertThrows(RuntimeException.class, () -> rollbackService.runtimeEx());
    }

    @Test
    void checkedEx() {
        Assertions.assertThrows(MyException.class, () -> rollbackService.checkedEx());
    }

    @Test
    void checkedExRollbackFor() {
        Assertions.assertThrows(MyException.class, () -> rollbackService.checkedExRollback());
    }

    @TestConfiguration
    static class RollbackTestConfig {
        @Bean
        RollbackService rollbackService() {
            return new RollbackService();
        }
    }

    @Slf4j
    static class RollbackService {
        @Transactional
        public void runtimeEx() {
            log.info("call runtime ex");
            throw new RuntimeException();
        }

        @Transactional
        public void checkedEx() throws MyException {
            log.info("call checked ex");
            throw new MyException();
        }

        @Transactional(rollbackFor = MyException.class)
        public void checkedExRollback() throws MyException {
            log.info("call rollbackfor");
            throw new MyException();
        }
    }

    static class MyException extends Exception {

    }
}
