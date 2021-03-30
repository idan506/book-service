package com.example.demo.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Ticket")
public class TicketEntity extends AbstractBaseEntity {

    @Column
    private String idUser;

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ticket_books",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<BookEntity> books;

    @Column
    private Date dayOfHire;

    @Column
    private Date expirationDate;

    @Column
    private boolean status;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDayOfHire() {
        return dayOfHire;
    }

    public void setDayOfHire(Date dayOfHire) {
        this.dayOfHire = dayOfHire;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
