package com.nfu.oldwork.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-7-25.
 */

public class BaseInfo implements Serializable{

    /**
     * iResult : 状态代码800=保存成功|100=保存失败|101=参数错误|102系统异常
     * strError : 错误信息
     */

    private String iResult;
    private String strError;

    public String getIResult() {
        return iResult;
    }

    public void setIResult(String iResult) {
        this.iResult = iResult;
    }

    public String getStrError() {
        return strError;
    }

    public void setStrError(String strError) {
        this.strError = strError;
    }

    @Override
    public String toString() {
        return "BaseInfo{" +
                "iResult='" + iResult + '\'' +
                ", strError='" + strError + '\'' +
                '}';
    }
}
