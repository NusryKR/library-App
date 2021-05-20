package com.example.public_library;

public class borrowedBooks_Model {
    private String bookId;
    private String bookName;
    private String returnDate;

    public borrowedBooks_Model(String bookId, String bookName, String returnDate) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.returnDate = returnDate;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
