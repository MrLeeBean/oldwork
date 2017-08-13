package com.nfu.oldwork.model;

/**
 * Created by yiliu on 2017/8/13.
 */

public class CommunicationInfo {

    /**
     * id : 交流ID
     * title : 标题
     * releasePeople : 发布人
     * content : 内容
     * viewCount : 浏览次数
     * communicationType : 交流类别
     * createdate : 发布时间，如2017-06-02
     * respondCount : 回复次数
     * picurl : 图片路径
     */

    private String id;
    private String title;
    private String releasePeople;
    private String content;
    private String viewCount;
    private String communicationType;
    private String createdate;
    private String respondCount;
    private String picurl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleasePeople() {
        return releasePeople;
    }

    public void setReleasePeople(String releasePeople) {
        this.releasePeople = releasePeople;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getCommunicationType() {
        return communicationType;
    }

    public void setCommunicationType(String communicationType) {
        this.communicationType = communicationType;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getRespondCount() {
        return respondCount;
    }

    public void setRespondCount(String respondCount) {
        this.respondCount = respondCount;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    @Override
    public String toString() {
        return "CommunicationInfo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", releasePeople='" + releasePeople + '\'' +
                ", content='" + content + '\'' +
                ", viewCount='" + viewCount + '\'' +
                ", communicationType='" + communicationType + '\'' +
                ", createdate='" + createdate + '\'' +
                ", respondCount='" + respondCount + '\'' +
                ", picurl='" + picurl + '\'' +
                '}';
    }
}
