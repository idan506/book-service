package com.example.demo.model;

public class SearchCondition {

    private String name;

    private String ISBN;

    private int publishYear;

    private String shortDescription;

    private String author;

    private int totalNumberTicket;

    public int getTotalNumberTicket() {
        return totalNumberTicket;
    }

    public void setTotalNumberTicket(int totalNumberTicket) {
        this.totalNumberTicket = totalNumberTicket;
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
}
