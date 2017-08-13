package com.nfu.oldwork.model;

/**
 * Created by yiliu on 2017/8/13.
 */

public class CommunicationModel {
    private int signKey;
    private int typeId;
    private int iPageSize;
    private int iCurrentPage;
    private int iRecordCount;

    public int getSignKey() {
        return signKey;
    }

    public void setSignKey(int signKey) {
        this.signKey = signKey;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
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
