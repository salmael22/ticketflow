
package com.ticketflow.bookingservice;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ticketflow.resilience.event-service")
public class EventServiceResilienceProperties {

    private int slidingWindowSize;
    private int minimumNumberOfCalls;
    private int failureRateThreshold;
    private Duration waitInOpenState;
    private int permittedCallsInHalfOpenState;
    private Duration timeLimit;

    public int getSlidingWindowSize() {
        return slidingWindowSize;
    }

    public void setSlidingWindowSize(int slidingWindowSize) {
        this.slidingWindowSize = slidingWindowSize;
    }

    public int getMinimumNumberOfCalls() {
        return minimumNumberOfCalls;
    }

    public void setMinimumNumberOfCalls(int minimumNumberOfCalls) {
        this.minimumNumberOfCalls = minimumNumberOfCalls;
    }

    public int getFailureRateThreshold() {
        return failureRateThreshold;
    }

    public void setFailureRateThreshold(int failureRateThreshold) {
        this.failureRateThreshold = failureRateThreshold;
    }

    public Duration getWaitInOpenState() {
        return waitInOpenState;
    }

    public void setWaitInOpenState(Duration waitInOpenState) {
        this.waitInOpenState = waitInOpenState;
    }

    public int getPermittedCallsInHalfOpenState() {
        return permittedCallsInHalfOpenState;
    }

    public void setPermittedCallsInHalfOpenState(int permittedCallsInHalfOpenState) {
        this.permittedCallsInHalfOpenState = permittedCallsInHalfOpenState;
    }

    public Duration getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Duration timeLimit) {
        this.timeLimit = timeLimit;
    }
}
