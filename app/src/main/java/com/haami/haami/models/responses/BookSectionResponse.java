package com.haami.haami.models.responses;

public class BookSectionResponse {
    private long bookSectionId;
    private long bookId;
    private String sectionName;
    private String title;
    private String body;
    private String imageUrl;
    private String audioUrl;

    private BookResponse book;

    public long getBookSectionId() {
        return bookSectionId;
    }

    public void setBookSectionId(long bookSectionId) {
        this.bookSectionId = bookSectionId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public BookResponse getBook() {
        return book;
    }

    public void setBook(BookResponse book) {
        this.book = book;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }
}
