package com.nfu.oldwork.model;

/**
 * Created by Administrator on 2017-8-14.
 */

public class ReplyModel {
    private int signKey;
    private int id;
    private String title;
    private String content;
    private String respondPeople;
    private String respondPeopleId;
    private String strBase64;

    public int getSignKey() {
        return signKey;
    }

    public void setSignKey(int signKey) {
        this.signKey = signKey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRespondPeople() {
        return respondPeople;
    }

    public void setRespondPeople(String respondPeople) {
        this.respondPeople = respondPeople;
    }

    public String getRespondPeopleId() {
        return respondPeopleId;
    }

    public void setRespondPeopleId(String respondPeopleId) {
        this.respondPeopleId = respondPeopleId;
    }

    public String getStrBase64() {
        return strBase64;
    }

    public void setStrBase64(String strBase64) {
        this.strBase64 = strBase64;
    }
}
