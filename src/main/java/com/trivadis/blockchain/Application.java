package com.trivadis.blockchain;
/*
 * (C) Copyright 2015-2017 Trivadis AG. All rights reserved.
 */

import com.trivadis.blockchain.helper.LoggingRequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marco Facetti
 */

@EnableAutoConfiguration
@SpringBootApplication
public class Application {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new LoggingRequestInterceptor());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
