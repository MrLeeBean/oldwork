package com.nfu.oldwork.model;

import java.util.List;

/**
 * Created by Administrator on 2017-8-17.
 */

public class FilialStarList {

    /**
     * recordCount : 1
     * pageCount : 1
     * data : [{"postalParcel":"111111","baRemarks":"11111111111111111111111111111","jobType":"办事人员和管理人员","sex":"女","linkPhone":"13809890987","cityIdea":"批量审核通过","countiesIdea":"批量审核通过","workUnit":"1","polity":"中共党员","storyType":"家庭孝老","cultureType":"高中","nation":"汉族","recommendType":"个人推荐","selfStory":"111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111","oldName":"刘惠芳","starNumber":"0","id":"1244706","address":"1","certificatesNumber":"110102193112221586","linkTelephone":"00000000","birthdate":"1931-12-22","workState":"市级审核通过","applyway":"正常申请"}]
     * pageSize : 10
     * currentPage : 1
     */

    private int recordCount;
    private int pageCount;
    private int pageSize;
    private int currentPage;
    private List<FilialStar> data;

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

    public List<FilialStar> getData() {
        return data;
    }

    public void setData(List<FilialStar> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "FilialStarList{" +
                "recordCount=" + recordCount +
                ", pageCount=" + pageCount +
                ", pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", data=" + data +
                '}';
    }
}
