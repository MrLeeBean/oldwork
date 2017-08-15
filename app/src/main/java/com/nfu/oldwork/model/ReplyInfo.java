package com.nfu.oldwork.model;

/**
 * Created by Administrator on 2017-8-15.
 */

public class ReplyInfo{

    /**
     * respondPeople : 回复人
     * respondContent : 回复内容
     * respondDate : 回复时间，如2017-8-14 16:15:01
     * respondPicurl : 回复图片路径
     */

    private String respondPeople;
    private String respondContent;
    private String respondDate;
    private String respondPicurl;
    private String squence;

    public String getRespondPeople() {
        return respondPeople;
    }

    public void setRespondPeople(String respondPeople) {
        this.respondPeople = respondPeople;
    }

    public String getRespondContent() {
        return respondContent;
    }

    public void setRespondContent(String respondContent) {
        this.respondContent = respondContent;
    }

    public String getRespondDate() {
        return respondDate;
    }

    public void setRespondDate(String respondDate) {
        this.respondDate = respondDate;
    }

    public String getRespondPicurl() {
        return respondPicurl;
    }

    public void setRespondPicurl(String respondPicurl) {
        this.respondPicurl = respondPicurl;
    }

    public String getSquence() {
        return squence;
    }

    public void setSquence(String squence) {
        this.squence = squence;
    }

    @Override
    public String toString() {
        return "ReplyInfo{" +
                "respondPeople='" + respondPeople + '\'' +
                ", respondContent='" + respondContent + '\'' +
                ", respondDate='" + respondDate + '\'' +
                ", respondPicurl='" + respondPicurl + '\'' +
                '}';
    }
}
