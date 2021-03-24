package com.example.demo.model;

import java.util.Date;
import java.util.List;

public class Ticket {
    private long id;
    private long idUser;
    private boolean status;

    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    private Date dayOfHire;

    private Date expirationDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
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
