package com.example.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Component
public class CheckProxyUtil implements ApplicationContextAware {

    public static ApplicationContext applicationContext;

//    public void log() {
//        log.info("==================================================");
//        log.info("!");
//        log.info("!");
//        System.out.println(applicationContext.getBean("employeeReader"));
//        log.info("is Aop Proxy?: " + AopUtils.isAopProxy(applicationContext.getBean("employReader")));
//        log.info(
//                "is CGLIB Proxy?: " + AopUtils.isCglibProxy(applicationContext.getBean("employReader")));
//        log.info("is Singleton bean?: " + applicationContext.isSingleton("employReader"));
//        log.info("is Prototype bean?: " + applicationContext.isPrototype("employReader"));
//        log.info("!!");
//        log.info("==================================================");
//    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
