package com.example.demo.entity;

import com.example.demo.convert.Base64StringConverter;

import javax.persistence.*;
import javax.persistence.metamodel.ListAttribute;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Book")
public class BookEntity extends AbstractBaseEntity {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ticket_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_id"))
    public List<TicketEntity> tickets;

    public List<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketEntity> tickets) {
        this.tickets = tickets;
    }

    @Column
    private String name;

    @Column(unique = true)
    private String ISBN;

    @Column
    private int publishYear;

    @Column
    private String shortDescription;

    @Column
    private String author;

    @Column
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Lob
    @Column
    @Convert(converter = Base64StringConverter.class)
    private String image;

    @Column
    private boolean status;

    @Column
    private boolean flag;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
