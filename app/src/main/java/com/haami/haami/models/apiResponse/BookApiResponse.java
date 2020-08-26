package com.haami.haami.models.apiResponse;

import com.haami.haami.models.responses.BookResponse;

import java.util.List;

public class BookApiResponse {
    private int count;
    private List<BookResponse> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<BookResponse> getData() {
        return data;
    }

    public void setData(List<BookResponse> data) {
        this.data = data;
    }
}
