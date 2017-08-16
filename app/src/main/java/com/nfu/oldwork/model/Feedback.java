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
    private String imgStrFirst;
    private String imgStrSecond;
    private String imgStrThird;

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

    public String getImgStrFirst() {
        return imgStrFirst;
    }

    public void setImgStrFirst(String imgStrFirst) {
        this.imgStrFirst = imgStrFirst;
    }

    public String getImgStrSecond() {
        return imgStrSecond;
    }

    public void setImgStrSecond(String imgStrSecond) {
        this.imgStrSecond = imgStrSecond;
    }

    public String getImgStrThird() {
        return imgStrThird;
    }

    public void setImgStrThird(String imgStrThird) {
        this.imgStrThird = imgStrThird;
    }
}
