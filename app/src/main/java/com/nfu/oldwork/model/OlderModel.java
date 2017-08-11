package com.nfu.oldwork.model;

/**
 * Created by Administrator on 2017-8-4.
 */

public class OlderModel {

    /**
     * id : 工作机构ID
     * institutionsName : 工作机构名称
     * telephone : 工作机构电话
     */

    private String id;
    private String institutionsName;
    private String telephone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstitutionsName() {
        return institutionsName;
    }

    public void setInstitutionsName(String institutionsName) {
        this.institutionsName = institutionsName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "OlderModel{" +
                "id='" + id + '\'' +
                ", institutionsName='" + institutionsName + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
