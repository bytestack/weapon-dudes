package com.trivadis.blockchain;
/*
 * (C) Copyright 2015-2017 Trivadis AG. All rights reserved.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author Marco Facetti
 */

@EnableAutoConfiguration
@SpringBootApplication
public class Application {

    @Bean
    public RestTemplate
    restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
