package com.nfu.oldwork.model;

import java.util.List;

/**
 * Created by Administrator on 2017-8-18.
 */

public class AssistiveCardList {

    /**
     * recordCount : 1
     * pageCount : 1
     * data : [{"sex":"男","householdStreet":"万寿路街道","residenceStreet":"德胜","householdAddress":"天安门7号107","residenceCommunity":"六铺炕水电社区居委会","isNonlocal":"否","cityPushedDate":"2017-08-18","cityPushed":"否","householdCommunity":"复兴路26号社区居民委员会","bankCard":null,"residenceCounty":"西城区","contacterTel":"65432100","id":"80032588","householdCounty":"昌平区","certificatesNumber":"110101198301210316","name":"李牧","birthdate":"1936-09-21","makeCardSuccess":"否","residenceAddress":"鼓楼大街","contacterMobile":"13897469532"}]
     * pageSize : 10
     * currentPage : 1
     */

    private int recordCount;
    private int pageCount;
    private int pageSize;
    private int currentPage;
    private List<AssistiveCard> data;

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

    public List<AssistiveCard> getData() {
        return data;
    }

    public void setData(List<AssistiveCard> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AssistiveCardList{" +
                "recordCount=" + recordCount +
                ", pageCount=" + pageCount +
                ", pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", data=" + data +
                '}';
    }
}
