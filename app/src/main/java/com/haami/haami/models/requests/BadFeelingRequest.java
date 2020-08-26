package com.haami.haami.models.requests;

public class BadFeelingRequest {
    private long userId;
    private long guideId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getGuideId() {
        return guideId;
    }

    public void setGuideId(long guideId) {
        this.guideId = guideId;
    }
}
