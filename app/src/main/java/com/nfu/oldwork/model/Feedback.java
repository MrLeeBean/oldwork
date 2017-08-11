package com.nfu.oldwork.model;

/**
 * Created by Administrator on 2017-7-28.
 */

public class Feedback {
    private int signKey;
    private String feedbackContent;
    private String contacterName;
    private String contacterMobile;
    private int opinionType;

    public int getSignKey() {
        return signKey;
    }

    public void setSignKey(int signKey) {
        this.signKey = signKey;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public String getContacterName() {
        return contacterName;
    }

    public void setContacterName(String contacterName) {
        this.contacterName = contacterName;
    }

    public String getContacterMobile() {
        return contacterMobile;
    }

    public void setContacterMobile(String contacterMobile) {
        this.contacterMobile = contacterMobile;
    }

    public int getOpinionType() {
        return opinionType;
    }

    public void setOpinionType(int opinionType) {
        this.opinionType = opinionType;
    }
}
