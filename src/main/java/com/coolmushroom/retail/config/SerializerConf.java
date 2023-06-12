package com.coolmushroom.retail.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.blackbird.BlackbirdModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class SerializerConf {
    @Primary
    @Bean
    public ObjectMapper serializer(Jackson2ObjectMapperBuilder builder) {
        var propertyNamingStrategy = new PropertyNamingStrategies.SnakeCaseStrategy();
        return builder
                .propertyNamingStrategy(propertyNamingStrategy)
                .featuresToDisable(
                        SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                        SerializationFeature.FAIL_ON_EMPTY_BEANS,
                        DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .modules(new JavaTimeModule(), new Jdk8Module(), new BlackbirdModule())
                .build();
    }
}
