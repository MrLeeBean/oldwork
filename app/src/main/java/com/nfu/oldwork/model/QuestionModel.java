package com.nfu.oldwork.model;

/**
 * Created by Administrator on 2017-8-14.
 */

public class QuestionModel {
    private int signKey;
    private int id;
    private String title;
    private String content;
    private String releasePeople;
    private int communicationType;
    private int operType;
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

    public String getReleasePeople() {
        return releasePeople;
    }

    public void setReleasePeople(String releasePeople) {
        this.releasePeople = releasePeople;
    }

    public int getCommunicationType() {
        return communicationType;
    }

    public void setCommunicationType(int communicationType) {
        this.communicationType = communicationType;
    }

    public int getOperType() {
        return operType;
    }

    public void setOperType(int operType) {
        this.operType = operType;
    }

    public String getStrBase64() {
        return strBase64;
    }

    public void setStrBase64(String strBase64) {
        this.strBase64 = strBase64;
    }
}
