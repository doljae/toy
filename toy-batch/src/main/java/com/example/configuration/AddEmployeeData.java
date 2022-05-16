package com.example.configuration;

import java.time.OffsetDateTime;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AddEmployeeData implements ApplicationListener<ApplicationStartedEvent> {

    private final EmployeeRepository employeeRepository;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        for (int i = 0; i < 10; i++) {
            final Employee employee = new Employee("employee" + i, OffsetDateTime.now());
            employeeRepository.save(employee);
        }
    }
}
