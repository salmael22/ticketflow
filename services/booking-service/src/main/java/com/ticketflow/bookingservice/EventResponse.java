package com.ticketflow.bookingservice;

import java.math.BigDecimal;

public record EventResponse(
    Long id,
    String name,
    String location,
    int availablePlaces,
    BigDecimal price
) {}
