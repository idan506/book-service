package com.example.demo.controller;

import com.example.demo.model.Ticket;
import com.example.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping
    public List<Ticket> getAllTicket(){
        return ticketService.getAllTicket();
    }

    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket){
        return ticketService.createTicket(ticket);
    }

    @PutMapping
    public Ticket updateTicket(@RequestBody Ticket ticket){
        return ticketService.updateTicket(ticket.getId());
    }

    @GetMapping("/list")
    public List<Ticket> listTicket(@RequestHeader Map<String, String> headers){
        String user = headers.get("x-user");
        return ticketService.listTicket(user);
    }

    @GetMapping("/export")
    public List<Ticket> listTicketExport(@RequestParam(name = "firstDay")LocalDate firstDay, @RequestParam(name = "lastDay")LocalDate lastDay){
        return ticketService.listTicketExport(firstDay,lastDay);
    }
}
