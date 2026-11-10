
package com.ticketflow.bookingservice;

import feign.FeignException;
import feign.RetryableException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BookingExceptionHandler {

    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<Void> handleNotFound(FeignException.NotFound ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({
            RetryableException.class,
            CallNotPermittedException.class,
            NoFallbackAvailableException.class,
            FeignException.ServiceUnavailable.class
    })
    public ResponseEntity<String> handleServiceUnavailable(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Event-Service est indisponible. Veuillez réessayer plus tard.");
    }
}
