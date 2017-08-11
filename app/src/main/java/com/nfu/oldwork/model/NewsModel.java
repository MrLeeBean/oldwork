package com.nfu.oldwork.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-7-26.
 */

public class NewsModel implements Serializable{

    /**
     * id : 新闻记录ID
     * title : 新闻标题
     * content : 新闻内容
     * createdate : 创建时间，如2017-06-02
     */

    private String id;
    private String title;
    private String content;
    private String createdate;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    @Override
    public String toString() {
        return "NewsModel{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdate='" + createdate + '\'' +
                ", picurl='" + picurl + '\'' +
                '}';
    }
}
