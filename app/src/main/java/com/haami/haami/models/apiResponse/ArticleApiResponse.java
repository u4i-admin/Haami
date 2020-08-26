package com.haami.haami.models.apiResponse;

import com.haami.haami.models.responses.ArticleResponse;
import java.util.List;

public class ArticleApiResponse {
    private int count;
    private List<ArticleResponse> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ArticleResponse> getData() {
        return data;
    }

    public void setData(List<ArticleResponse> data) {
        this.data = data;
    }
}
