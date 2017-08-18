package com.nfu.oldwork.model;

/**
 * Created by Administrator on 2017-8-18.
 */

public class QueryOlder {
    private int signKey;
    private int iPageSize;
    private int iCurrentPage;
    private int iRecordCount;
    private String name;
    private String cum;
    private int cityId;
    private int countyId;
    private int streetId;
    private int communityId;

    public int getSignKey() {
        return signKey;
    }

    public void setSignKey(int signKey) {
        this.signKey = signKey;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCum() {
        return cum;
    }

    public void setCum(String cum) {
        this.cum = cum;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public int getStreetId() {
        return streetId;
    }

    public void setStreetId(int streetId) {
        this.streetId = streetId;
    }

    public int getCommunityId() {
        return communityId;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }
}
