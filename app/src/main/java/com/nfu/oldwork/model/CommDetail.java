package com.nfu.oldwork.model;

import java.util.List;

/**
 * Created by Administrator on 2017-8-15.
 */

public class CommDetail {
    /**
     * recordCount : 0
     * pageCount : 0
     * releasePeople : 张三3
     * data : []
     * createdate : 2017-08-14 17:44
     * pageSize : 10
     * picurl : http://17103938iy.iask.in/bjllapp/imgFile/yhjl/YHJL6u5JGkmo.jpg
     * id : 45
     * content : 都是什么
     * title : 日常生活
     * communicationType : 日常生活
     * currentPage : 0
     * viewCount : 2
     * respondCount :
     */

    private int recordCount;
    private int pageCount;
    private String releasePeople;
    private String createdate;
    private int pageSize;
    private String picurl;
    private String id;
    private String content;
    private String title;
    private String communicationType;
    private int currentPage;
    private String viewCount;
    private String respondCount;
    private List<ReplyInfo> data;

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getReleasePeople() {
        return releasePeople;
    }

    public void setReleasePeople(String releasePeople) {
        this.releasePeople = releasePeople;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCommunicationType() {
        return communicationType;
    }

    public void setCommunicationType(String communicationType) {
        this.communicationType = communicationType;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getRespondCount() {
        return respondCount;
    }

    public void setRespondCount(String respondCount) {
        this.respondCount = respondCount;
    }

    public List<ReplyInfo> getData() {
        return data;
    }

    public void setData(List<ReplyInfo> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommDetail{" +
                "recordCount=" + recordCount +
                ", pageCount=" + pageCount +
                ", releasePeople='" + releasePeople + '\'' +
                ", createdate='" + createdate + '\'' +
                ", pageSize=" + pageSize +
                ", picurl='" + picurl + '\'' +
                ", id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", communicationType='" + communicationType + '\'' +
                ", currentPage=" + currentPage +
                ", viewCount='" + viewCount + '\'' +
                ", respondCount='" + respondCount + '\'' +
                ", data=" + data +
                '}';
    }
}
