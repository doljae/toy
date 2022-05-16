package com.example.service;

import org.springframework.stereotype.Service;

import com.example.domain.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ExceptionService {

    public void sendAoa(Employee employee) {
        log.info("send aoa={}", employee);
    }

    public void handleItem(Employee employee) {
        try {
            log.info("handle item={}", employee);
            if (employee.getId() == 4) {
                makeException();
            }
        } catch (Exception e) {
            log.info("exception occured!!");
        }
    }

    public void handleItemWitoutCatchException(Employee employee) {
        log.info("handle item={}", employee);
        if (employee.getId() == 4) {
            makeException();
        }

    }

    private void makeException() {
        throw new RuntimeException();
    }
}
