package com.example.feign;

import java.util.List;

import org.springframework.cloud.openfeign.CollectionFormat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.Color;
import com.example.dto.ResponseDto;

@FeignClient(name = "test-feign", url = "http://localhost:1234")
public interface TestFeignClient {

    @GetMapping("/test")
    @CollectionFormat(feign.CollectionFormat.CSV)
    ResponseDto test(@RequestParam("types") List<Color> types);
}
