package com.example.transaction;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class InternalCallV2Test {

    @Autowired
    private CallService callService;

    @Test
    void printProxy() {
        log.info("aop class={}", callService.getClass());
        Assertions.assertThat(AopUtils.isAopProxy(callService)).isTrue();
    }

    @Test
    void externalCall() {
        callService.external();
    }

    @TestConfiguration
    static class TxApplyBasicConfig {
        @Bean
        CallService callService() {
            return new CallService(internalCallService());
        }

        @Bean
        InternalCallService internalCallService() {
            return new InternalCallService();
        }
    }

    @Slf4j
    @RequiredArgsConstructor
    static class CallService {

        private final InternalCallService internalCallService;

        public void external() {
            log.info("call external");
            printTxInfo();
            internalCallService.internal();
        }

        private void printTxInfo() {
            final boolean actualTransactionActive =
                TransactionSynchronizationManager.isActualTransactionActive();
            log.info("tx active={}", actualTransactionActive);
        }
    }

    @Slf4j
    static class InternalCallService {
        @Transactional
        public void internal() {
            log.info("call internal");
            printTxInfo();
        }

        private void printTxInfo() {
            final boolean actualTransactionActive =
                TransactionSynchronizationManager.isActualTransactionActive();
            log.info("tx active={}", actualTransactionActive);
        }
    }
}
