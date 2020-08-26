package com.haami.haami.models.responses;

import androidx.annotation.Nullable;

import java.util.Date;

public class BadFeelingResponse {
    private long badFeelingId;
    private long userId;
    private Date requestDateTime;
    private String requestDateTimePersian;
    private Long guideId;
    private String guideTelegramId;

    private UserResponse user;
    private UserResponse guide;

    public long getBadFeelingId() {
        return badFeelingId;
    }

    public void setBadFeelingId(long badFeelingId) {
        this.badFeelingId = badFeelingId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(Date requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public String getRequestDateTimePersian() {
        return requestDateTimePersian;
    }

    public void setRequestDateTimePersian(String requestDateTimePersian) {
        this.requestDateTimePersian = requestDateTimePersian;
    }

    public Long getGuideId() {
        return guideId;
    }

    public void setGuideId(Long guideId) {
        this.guideId = guideId;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public UserResponse getGuide() {
        return guide;
    }

    public void setGuide(UserResponse guide) {
        this.guide = guide;
    }

    public String getGuideTelegramId() {
        return guideTelegramId;
    }

    public void setGuideTelegramId(String guideTelegramId) {
        this.guideTelegramId = guideTelegramId;
    }
}
