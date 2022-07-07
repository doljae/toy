package com.example;

import java.util.Arrays;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.configuration.CheckProxyUtil;

@EnableBatchProcessing
@SpringBootApplication
public class ToyBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToyBatchApplication.class, args);

//        final CheckProxyUtil checkProxyUtil = new CheckProxyUtil();
//        for (String beanDefinitionName : CheckProxyUtil.applicationContext.getBeanDefinitionNames()) {
//            System.out.println(beanDefinitionName);
//            System.out.println(CheckProxyUtil.applicationContext.getBean(beanDefinitionName).getClass().getName());
//            System.out.println();
//        }

    }
}
