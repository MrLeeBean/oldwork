package com.nfu.oldwork.model;

import java.util.List;

/**
 * Created by yiliu on 2017/8/4.
 */

public class OldWorkInfo extends BaseInfo {

    /**
     * iResult : 0
     * strResult : [{"institutionsName":"市信访电话","telephone":"65859523"},{"institutionsName":"96156电话","telephone":"96156"}]
     */

    private List<OlderModel> strResult;

    public List<OlderModel> getStrResult() {
        return strResult;
    }

    public void setStrResult(List<OlderModel> strResult) {
        this.strResult = strResult;
    }

    @Override
    public String toString() {
        return "OldWorkInfo{" +
                "strResult=" + strResult +
                '}';
    }
}
