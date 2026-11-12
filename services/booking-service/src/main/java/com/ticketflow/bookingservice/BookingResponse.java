package com.ticketflow.bookingservice;

import java.math.BigDecimal;

public record BookingResponse(
    Long eventId,
    String eventName,
    int quantity,
    BigDecimal totalPrice,
    String status
) {}
