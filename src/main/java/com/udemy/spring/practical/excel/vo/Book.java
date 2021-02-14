package com.udemy.spring.practical.excel.vo;

public class Book {

    private String isbn;
    private String subject;
    private String author;
    private String publishDate;
    private int price;

    public Book() {
    }

    public Book(String isbn, String subject, String author, String publishDate, int price) {
        this.isbn = isbn;
        this.subject = subject;
        this.author = author;
        this.publishDate = publishDate;
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
