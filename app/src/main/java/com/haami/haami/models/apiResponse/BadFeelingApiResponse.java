package com.haami.haami.models.apiResponse;

import com.haami.haami.models.responses.BadFeelingResponse;

import java.util.List;

public class BadFeelingApiResponse {
    private int count;
    private List<BadFeelingResponse> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<BadFeelingResponse> getData() {
        return data;
    }

    public void setData(List<BadFeelingResponse> data) {
        this.data = data;
    }
}
