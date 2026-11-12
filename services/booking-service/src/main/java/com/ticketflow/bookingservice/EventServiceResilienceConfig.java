
package com.ticketflow.bookingservice;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventServiceResilienceConfig {

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> eventServiceCustomizer(
            EventServiceResilienceProperties properties) {

        return factory -> factory.configureDefault(id ->
                new Resilience4JConfigBuilder(id)
                        .circuitBreakerConfig(
                                CircuitBreakerConfig.custom()
                                        .slidingWindowSize(properties.getSlidingWindowSize())
                                        .minimumNumberOfCalls(properties.getMinimumNumberOfCalls())
                                        .failureRateThreshold(properties.getFailureRateThreshold())
                                        .waitDurationInOpenState(properties.getWaitInOpenState())
                                        .permittedNumberOfCallsInHalfOpenState(
                                                properties.getPermittedCallsInHalfOpenState())
                                        .ignoreExceptions(
                                                feign.FeignException.NotFound.class,
                                                feign.FeignException.BadRequest.class)
                                        .build())
                        .timeLimiterConfig(
                                TimeLimiterConfig.custom()
                                        .timeoutDuration(properties.getTimeLimit())
                                        .build())
                        .build()
        );
    }
}
