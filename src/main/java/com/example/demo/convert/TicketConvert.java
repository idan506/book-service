package com.example.demo.convert;

import com.example.demo.entity.TicketEntity;
import com.example.demo.model.Ticket;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketConvert {

    @Autowired
    private ModelMapper modelMapper;

    public Ticket toModel(TicketEntity ticketEntity) {
        return modelMapper.map(ticketEntity, Ticket.class);
    }

    public TicketEntity toEntity(Ticket t){
        return modelMapper.map(t, TicketEntity.class);
    }
}
