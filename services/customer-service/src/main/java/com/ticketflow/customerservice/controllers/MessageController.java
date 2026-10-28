package com.ticketflow.customerservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @GetMapping("/message")
    public String message() {
        return "Bienvenue dans TicketFlow !";
    }
}