package com.example.demo.controller;

import com.example.demo.model.Ticket;
import com.example.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping
    public List<Ticket> getAllTicket(){
        return ticketService.getAllTicket();
    }

    @GetMapping("/{id}")
    public Ticket findTicketById(@PathVariable (name="id") long id){
        return ticketService.findById(id);
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
    public List<Ticket> listTicket(){
        return ticketService.listTicket(3);
    }
}
