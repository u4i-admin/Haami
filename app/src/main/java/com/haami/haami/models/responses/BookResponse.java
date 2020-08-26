package com.haami.haami.models.responses;

import com.haami.haami.models.enums.BookTypeEnum;

import java.util.Date;
import java.util.List;

public class BookResponse {
    private long bookId;
    private String bookName;
    private Integer sectionCount;
    private String writer;
    private BookTypeEnum bookType;
    private Boolean isAudio;
    private String fileUrl;
    private String magazineNumber;
    private Date releaseDate;
    private String releaseDatePersian;
    private String bookText;
    private String imageUrl;
    private String description;

    private List<BookSectionResponse> sections;

    public long getBookId() { return bookId; }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public List<BookSectionResponse> getSections() {
        return sections;
    }

    public void setSections(List<BookSectionResponse> sections) {
        this.sections = sections;
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

    public Integer getSectionCount() {
        return sectionCount;
    }

    public void setSectionCount(Integer sectionCount) {
        this.sectionCount = sectionCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDatePersian() {
        return releaseDatePersian;
    }

    public void setReleaseDatePersian(String releaseDatePersian) {
        this.releaseDatePersian = releaseDatePersian;
    }
}
