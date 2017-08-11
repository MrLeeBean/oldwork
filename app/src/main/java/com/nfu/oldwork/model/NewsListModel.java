package com.nfu.oldwork.model;

/**
 * Created by Administrator on 2017-7-26.
 */

public class NewsListModel extends BaseInfo{

    /**
     * iResult : 0
     * strResult : {"recordCount":22,"pageCount":22,"data":[{"id":"1100","content":"关于中央财政支持开展居家和社区养老服务改革试点工作","title":"关于中央财政支持开展居家和社区养老服务改革试点工作的通知","createdate":"2017-06-13","picurl":""}],"pageSize":1,"currentPage":1}
     */

    private String strResult;


    public String getStrResult() {
        return strResult;
    }

    public void setStrResult(String strResult) {
        this.strResult = strResult;
    }

    @Override
    public String toString() {
        return "NewsListModel{" +
                "strResult='" + strResult + '\'' +
                '}';
    }
}
