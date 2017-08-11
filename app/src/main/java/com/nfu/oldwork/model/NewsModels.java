package com.nfu.oldwork.model;

import java.util.List;

/**
 * Created by Administrator on 2017-7-26.
 */

public class NewsModels {

    /**
     * recordCount : 22
     * pageCount : 5
     * data : [{"id":"1100","content":"关于中央财政支持开展居家和社区养老服务改革试点工作","title":"关于中央财政支持开展居家和社区养老服务改革试点工作的通知","createdate":"2017-06-13","picurl":""},{"id":"1101","content":"国务院办公厅关于进一步扩大旅游文化体育健康养老教育","title":"国务院办公厅关于进一步扩大旅游文化体育健康养老教育培训等领域消费的意见","createdate":"2017-06-13","picurl":""},{"id":"1102","content":"《北京通\u2014养老助残卡管理办法（暂行）》的通知201","title":"《北京通\u2014养老助残卡管理办法（暂行）》的通知","createdate":"2017-06-13","picurl":""},{"id":"1103","content":"北京市老龄工作委员会印发《关于加强老年人分类保障的","title":"北京市老龄工作委员会印发《关于加强老年人分类保障的指导意见》的通知","createdate":"2017-06-13","picurl":""},{"id":"1104","content":"北京市民政局北京市财政局北京市老龄工作委员会办公室","title":"北京市民政局 北京市财政局北京市老龄工作委员会办公室关于2016年开展养老助餐服务体系试点建设工作的通知","createdate":"2017-06-13","picurl":""}]
     * pageSize : 5
     * currentPage : 1
     */

    private int recordCount;
    private int pageCount;
    private int pageSize;
    private int currentPage;
    private List<NewsModel> data;

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

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<NewsModel> getData() {
        return data;
    }

    public void setData(List<NewsModel> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NewsModels{" +
                "recordCount=" + recordCount +
                ", pageCount=" + pageCount +
                ", pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", data=" + data +
                '}';
    }
}
