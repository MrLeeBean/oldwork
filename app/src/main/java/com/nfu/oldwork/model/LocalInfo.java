package com.nfu.oldwork.model;

import java.io.Serializable;

import static com.nfu.oldwork.R.id.workaddr_et;
import static com.nfu.oldwork.R.id.worktype_et;

/**
 * Created by Administrator on 2017/8/15.
 */

public class LocalInfo implements Serializable{
    public String name ;
    public String workAddr;
    public String workType;
    public String idCard;
    public String gender;
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkAddr() {
        return workAddr;
    }

    public void setWorkAddr(String workAddr) {
        this.workAddr = workAddr;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Override
    public String toString() {
        return "LocalInfo{" +
                "name='" + name + '\'' +
                ", workAddr='" + workAddr + '\'' +
                ", workType='" + workType + '\'' +
                ", idCard='" + idCard + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
