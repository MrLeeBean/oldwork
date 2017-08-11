package com.nfu.oldwork.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/4.
 */

public class OldServiceModel implements Serializable{
    /**
     * contactPhone : 010-69785231
     * institutionName : 北京昌平安乐祥居老年公寓
     * institutionType : 养老机构
     * introduce :
     * officeHours : 星期一至星期五每日上午9:00至12:00，下午13:30至17:00。法定节假日按照国家规定执行
     * id : 1
     * countyName : 昌平区
     * distance : 24135
     * address : 北京市昌平区南口镇龙虎台村
     * zipCode : 102202
     * longitude : 116.163266
     * latitude : 40.244772
     * institutionUrl :
     */

    private String contactPhone;
    private String institutionName;
    private String institutionType;
    private String introduce;
    private String officeHours;
    private String id;
    private String countyName;
    private String distance;
    private String address;
    private String zipCode;
    private String longitude;
    private String latitude;
    private String institutionUrl;

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getInstitutionType() {
        return institutionType;
    }

    public void setInstitutionType(String institutionType) {
        this.institutionType = institutionType;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getInstitutionUrl() {
        return institutionUrl;
    }

    public void setInstitutionUrl(String institutionUrl) {
        this.institutionUrl = institutionUrl;
    }

    @Override
    public String toString() {
        return "OldServiceModel{" +
                "contactPhone='" + contactPhone + '\'' +
                ", institutionName='" + institutionName + '\'' +
                ", institutionType='" + institutionType + '\'' +
                ", introduce='" + introduce + '\'' +
                ", officeHours='" + officeHours + '\'' +
                ", id='" + id + '\'' +
                ", countyName='" + countyName + '\'' +
                ", distance='" + distance + '\'' +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", institutionUrl='" + institutionUrl + '\'' +
                '}';
    }
}
