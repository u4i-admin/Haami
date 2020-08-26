package com.haami.haami.models.apiResponse;

import com.haami.haami.models.responses.SettingResponse;

import java.util.List;

public class SettingApiResponse {
    private int count;
    private List<SettingResponse> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<SettingResponse> getData() {
        return data;
    }

    public void setData(List<SettingResponse> data) {
        this.data = data;
    }
}
