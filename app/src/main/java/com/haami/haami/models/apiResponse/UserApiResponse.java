package com.haami.haami.models.apiResponse;

import com.haami.haami.models.responses.UserResponse;

import java.util.List;

public class UserApiResponse {
    private int count;
    private List<UserResponse> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<UserResponse> getData() {
        return data;
    }

    public void setData(List<UserResponse> data) {
        this.data = data;
    }
}
