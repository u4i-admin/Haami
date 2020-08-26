package com.haami.haami.models.requests;

import com.haami.haami.models.enums.BookTypeEnum;

import java.util.Date;

public class BookRequest {
    private String bookName;
    private String writer;
    public BookTypeEnum bookType;
    public Boolean isAudio;
    public String fileUrl;
    public String magazineNumber;
    public Date releaseDate;
    public String bookText;
    public String imageUrl;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public BookTypeEnum getBookType() {
        return bookType;
    }

    public void setBookType(BookTypeEnum bookType) {
        this.bookType = bookType;
    }

    public Boolean getAudio() {
        return isAudio;
    }

    public void setAudio(Boolean audio) {
        isAudio = audio;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getMagazineNumber() {
        return magazineNumber;
    }

    public void setMagazineNumber(String magazineNumber) {
        this.magazineNumber = magazineNumber;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getBookText() {
        return bookText;
    }

    public void setBookText(String bookText) {
        this.bookText = bookText;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
