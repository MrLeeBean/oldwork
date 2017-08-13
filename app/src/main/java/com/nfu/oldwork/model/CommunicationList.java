package com.nfu.oldwork.model;

import java.util.List;

/**
 * Created by yiliu on 2017/8/13.
 */

public class CommunicationList {

    /**
     * recordCount : 总记录数
     * pageCount : 总页数
     * pageSize : 每页记录条数 小于等于0不分页
     * currentPage : 当前数据页号
     * data : [{"id":"交流ID","title":"标题","releasePeople":"发布人","content":"内容","viewCount":"浏览次数","communicationType":"交流类别","createdate":"发布时间，如2017-06-02","respondCount":"回复次数","picurl":"图片路径"}]
     */

    private int recordCount;
    private int pageCount;
    private int pageSize;
    private int currentPage;
    private List<CommunicationInfo> data;

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

    public List<CommunicationInfo> getData() {
        return data;
    }

    public void setData(List<CommunicationInfo> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommunicationList{" +
                "recordCount=" + recordCount +
                ", pageCount=" + pageCount +
                ", pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", data=" + data +
                '}';
    }
}
