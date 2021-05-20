package com.example.public_library;

public class BorrowBook_Model {
    private String bookId;
    private String bookName;
    private String bookAuthor;
    private String bookType;


    public BorrowBook_Model(String bookId, String bookName, String bookAuthor, String bookType) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookType = bookType;
    }

    public String getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }
}
