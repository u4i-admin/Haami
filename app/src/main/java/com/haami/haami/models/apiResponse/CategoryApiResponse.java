package com.haami.haami.models.apiResponse;

import com.haami.haami.models.responses.CategoryResponse;

import java.util.List;

public class CategoryApiResponse {
    private int count;
    private List<CategoryResponse> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<CategoryResponse> getData() {
        return data;
    }

    public void setData(List<CategoryResponse> data) {
        this.data = data;
    }
}
