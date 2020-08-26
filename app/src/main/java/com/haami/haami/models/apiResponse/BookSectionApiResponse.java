package com.haami.haami.models.apiResponse;

import com.haami.haami.models.responses.BookSectionResponse;

import java.util.List;

public class BookSectionApiResponse {
    private int count;
    private List<BookSectionResponse> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<BookSectionResponse> getData() {
        return data;
    }

    public void setData(List<BookSectionResponse> data) {
        this.data = data;
    }
}
