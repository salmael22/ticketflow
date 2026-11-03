package com.ticketflow.bookingservice;

import java.math.BigDecimal;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BookingService {

    private final EventClient eventClient;

    public BookingService(EventClient eventClient) {
        this.eventClient = eventClient;
    }

    public BookingResponse createBooking(BookingRequest request) {

        if (request.eventId() == null || request.quantity() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Evenement et quantite valides obligatoires"
            );
        }

        EventResponse event;

        try {
            event = eventClient.getEvent(request.eventId());
        } catch (FeignException.NotFound ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Evenement introuvable"
            );
        }

        if (request.quantity() > event.availablePlaces()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Places insuffisantes"
            );
        }

        BigDecimal total = event.price()
                .multiply(BigDecimal.valueOf(request.quantity()));

        return new BookingResponse(
                event.id(),
                event.name(),
                request.quantity(),
                total,
                "PENDING"
        );
    }
}