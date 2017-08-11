package com.nfu.oldwork.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-7-27.
 */

public class Transaction implements Serializable{

    /**
     * blanxs : YES
     * blff : 2017年1月1日起,北京市农商银行营业网点申请办理
     * blanlj : https://fhzx.bjrcb.com/appoint/choosePension.jhtml
     * ywmc : 养老助残卡
     * tbsm : 材料：1.京籍老年人（包括京籍离退休军人）：持户口本、身份证原件，以及近期一寸标准白底彩色照片一张进行申请。2.外埠老年人：持有效身份证件和《居住证》的原件，以及近期一寸标准白底彩色照片一张进行申请
     * bltj : 1、具有本市户籍的60周岁及以上的老年人<br/>2、在本市行政区域内办理《居住证》的60周岁及以上的外埠老年人
     */

    private String blanxs;
    private String blff;
    private String blanlj;
    private String ywmc;
    private String tbsm;
    private String bltj;

    public String getBlanxs() {
        return blanxs;
    }

    public void setBlanxs(String blanxs) {
        this.blanxs = blanxs;
    }

    public String getBlff() {
        return blff;
    }

    public void setBlff(String blff) {
        this.blff = blff;
    }

    public String getBlanlj() {
        return blanlj;
    }

    public void setBlanlj(String blanlj) {
        this.blanlj = blanlj;
    }

    public String getYwmc() {
        return ywmc;
    }

    public void setYwmc(String ywmc) {
        this.ywmc = ywmc;
    }

    public String getTbsm() {
        return tbsm;
    }

    public void setTbsm(String tbsm) {
        this.tbsm = tbsm;
    }

    public String getBltj() {
        return bltj;
    }

    public void setBltj(String bltj) {
        this.bltj = bltj;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "blanxs='" + blanxs + '\'' +
                ", blff='" + blff + '\'' +
                ", blanlj='" + blanlj + '\'' +
                ", ywmc='" + ywmc + '\'' +
                ", tbsm='" + tbsm + '\'' +
                ", bltj='" + bltj + '\'' +
                '}';
    }
}
