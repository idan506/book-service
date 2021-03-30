package com.example.demo.service;

import com.example.demo.convert.TicketConvert;
import com.example.demo.entity.BookEntity;
import com.example.demo.entity.TicketEntity;
import com.example.demo.model.Ticket;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TicketConvert ticketConvert;

    @Value("${quantityBookOfTicket}")
    int numberOfTicket;

    //list ticket admin
    public List<Ticket>  getAllTicket(){
        List<Ticket> result = ticketRepository.findAll().stream()
                .map(ticket -> ticketConvert.toModel(ticket))
                .collect(Collectors.toList());
        return result;
    }

    @Transactional
    public List<Ticket> getAllTicketByStatus(){
        List<Ticket> result = ticketRepository.listTicketByStatusTrue().stream()
                .map(ticket -> ticketConvert.toModel(ticket))
                .collect(Collectors.toList());
        return result;
    }

    @Transactional
    public List<Ticket> listTicket(String id){
        List<Ticket> result = ticketRepository.listTicket(id).stream()
                .map(ticket -> ticketConvert.toModel(ticket))
                .collect(Collectors.toList());
        return result;
    }

    //muon sach
    @Transactional
    public Ticket createTicket(Ticket t){
        TicketEntity ticket = ticketConvert.toEntity(t);
        List<Long> bookIds = ticket.getBooks().stream().map(x -> x.getId()).collect(Collectors.toList());
        List<BookEntity> listBook = bookRepository.findAllById(bookIds);
        ticket.setBooks(listBook);
        if(listBook.size()<= numberOfTicket) {
            ticket = ticketRepository.save(ticket);
            for (BookEntity be : listBook) {
                be.setQuantity(be.getQuantity() - 1);
                if (be.getQuantity() == 0) {
                    bookRepository.updateStatus(be.getId());
                }
            }
        }
        return ticketConvert.toModel(ticket);
    }

    //tra sach
    @Transactional
    public Ticket updateTicket(long id){
        TicketEntity t = ticketRepository.findById(id).get();
        List<Long> bookIds = t.getBooks().stream().map(x -> x.getId()).collect(Collectors.toList());
        List<BookEntity> bookEntities = bookRepository.findAllById(bookIds);
        for (BookEntity bookEntity : bookEntities){
            if(bookEntity.getQuantity()==0){
                bookRepository.updateStatusBook(bookEntity.getId());
            }
            bookEntity.setQuantity(bookEntity.getQuantity() + 1);
        }
        ticketRepository.updateTicket(t.getId());
        bookRepository.saveAll(bookEntities);
        return ticketConvert.toModel(t);
    }

    @Transactional
    public List<Ticket> listTicketExport(LocalDate firstDay, LocalDate lastDay){
        return ticketRepository.listTicketExport(Date.from(firstDay.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(lastDay.atStartOfDay(ZoneId.systemDefault()).toInstant())).stream()
                .map(ticket -> ticketConvert.toModel(ticket))
                .collect(Collectors.toList());
    }
}
