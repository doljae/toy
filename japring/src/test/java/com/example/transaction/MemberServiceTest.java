package com.example.transaction;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.containers.AbstractTestContainers;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class MemberServiceTest extends AbstractTestContainers {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LogRepository logRepository;

    @Test
    void outerTxOff_success() {
        final String username = "outerTxOff_success";

        memberService.joinV1(username);

        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
    }

    @Test
    void outerTxOff_fail() {

        final String username = "로그예외_outerTxOff_fail";

        assertThrows(RuntimeException.class, () -> memberService.joinV1(username));

        // ?
        assertFalse(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isEmpty());

    }

    @Test
    void singleTx() {
        final String username = "singleTx";

        memberService.joinV1(username);

        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
    }

    @Test
    void recoverException_fail() {
        final String username = "로그예외_recoverException_fail";

        //
        assertDoesNotThrow(() -> memberService.joinV2(username));

        assertFalse(memberRepository.find(username).isEmpty());
        assertTrue(logRepository.find(username).isEmpty());
    }

    @Test
    void recoverException_success() {
        final String username = "로그예외_recoverException_fail";

        memberService.joinV2(username);

        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isEmpty());
    }
}
