package com.nfu.oldwork.model;

/**
 * Created by Administrator on 2017-8-15.
 */

public class QueryModel {
    private int signKey;
    private String id;
    private int iPageSize;
    private int iCurrentPage;
    private int iRecordCount;

    public int getSignKey() {
        return signKey;
    }

    public void setSignKey(int signKey) {
        this.signKey = signKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getiPageSize() {
        return iPageSize;
    }

    public void setiPageSize(int iPageSize) {
        this.iPageSize = iPageSize;
    }

    public int getiCurrentPage() {
        return iCurrentPage;
    }

    public void setiCurrentPage(int iCurrentPage) {
        this.iCurrentPage = iCurrentPage;
    }

    public int getiRecordCount() {
        return iRecordCount;
    }

    public void setiRecordCount(int iRecordCount) {
        this.iRecordCount = iRecordCount;
    }
}
