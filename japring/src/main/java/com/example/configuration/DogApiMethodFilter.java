package com.example.configuration;

import java.lang.reflect.Method;

import org.springdoc.core.filters.OpenApiMethodFilter;
import org.springframework.core.annotation.AnnotationUtils;

import com.example.annotation.ForDog;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DogApiMethodFilter implements OpenApiMethodFilter {
    @Override
    public boolean isMethodToInclude(Method method) {
        return AnnotationUtils.findAnnotation(method, ForDog.class) != null;
    }
}
