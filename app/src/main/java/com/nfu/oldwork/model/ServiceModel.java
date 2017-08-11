package com.nfu.oldwork.model;

import java.io.Serializable;

/**
 * Created by user on 2017/7/27.
 */

public class ServiceModel implements Serializable {

    /**
     * shopCountryName : 服务商所属区县
     * shopCountryId : 服务商所属区县CODE
     * shopStreetName : 服务商所属街道
     * shopStreetId : 服务商所属街道CODE
     * shopCommunityName : 服务商所属社区
     * shopcommunityId : 服务商所属社区CODE
     *
     * shopName : 服务商名称
     * shopType : 服务类型
     * shopManager : 联系人
     * shopTelephone : 联系人电话
     * servicePhone : 服务电话
     * businessAddress : 商户地址
     * shopPic : 商户图片
     *
     * shopNumber : 商户编号
     * infoSource : 渠道发展来源
     * shopId : 服务商编号
     *
     * shopOther : 其他字段
     * "longitude":"经度",
     "latitude":"纬度"

     *
     */

    private String shopCountryName;
    private String shopCountryId;
    private String shopStreetName;
    private String shopStreetId;
    private String shopCommunityName;
    private String shopcommunityId;
    private String shopName;
    private String shopType;
    private String shopManager;
    private String shopTelephone;
    private String servicePhone;
    private String businessAddress;
    private String shopNumber;
    private String infoSource;
    private String shopId;
    private String shopPic;
    private String shopOther;
    private String distance;
    private String longitude;
    private String latitude;
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



    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }



    public String getShopCountryName() {
        return shopCountryName;
    }

    public void setShopCountryName(String shopCountryName) {
        this.shopCountryName = shopCountryName;
    }

    public String getShopCountryId() {
        return shopCountryId;
    }

    public void setShopCountryId(String shopCountryId) {
        this.shopCountryId = shopCountryId;
    }

    public String getShopStreetName() {
        return shopStreetName;
    }

    public void setShopStreetName(String shopStreetName) {
        this.shopStreetName = shopStreetName;
    }

    public String getShopStreetId() {
        return shopStreetId;
    }

    public void setShopStreetId(String shopStreetId) {
        this.shopStreetId = shopStreetId;
    }

    public String getShopCommunityName() {
        return shopCommunityName;
    }

    public void setShopCommunityName(String shopCommunityName) {
        this.shopCommunityName = shopCommunityName;
    }

    public String getShopcommunityId() {
        return shopcommunityId;
    }

    public void setShopcommunityId(String shopcommunityId) {
        this.shopcommunityId = shopcommunityId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public String getShopManager() {
        return shopManager;
    }

    public void setShopManager(String shopManager) {
        this.shopManager = shopManager;
    }

    public String getShopTelephone() {
        return shopTelephone;
    }

    public void setShopTelephone(String shopTelephone) {
        this.shopTelephone = shopTelephone;
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getShopNumber() {
        return shopNumber;
    }

    public void setShopNumber(String shopNumber) {
        this.shopNumber = shopNumber;
    }

    public String getInfoSource() {
        return infoSource;
    }

    public void setInfoSource(String infoSource) {
        this.infoSource = infoSource;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopPic() {
        return shopPic;
    }

    public void setShopPic(String shopPic) {
        this.shopPic = shopPic;
    }

    public String getShopOther() {
        return shopOther;
    }

    public void setShopOther(String shopOther) {
        this.shopOther = shopOther;
    }

    @Override
    public String toString() {
        return "ServiceModel{" +
                "shopCountryName='" + shopCountryName + '\'' +
                ", shopCountryId='" + shopCountryId + '\'' +
                ", shopStreetName='" + shopStreetName + '\'' +
                ", shopStreetId='" + shopStreetId + '\'' +
                ", shopCommunityName='" + shopCommunityName + '\'' +
                ", shopcommunityId='" + shopcommunityId + '\'' +
                ", shopName='" + shopName + '\'' +
                ", shopType='" + shopType + '\'' +
                ", shopManager='" + shopManager + '\'' +
                ", shopTelephone='" + shopTelephone + '\'' +
                ", servicePhone='" + servicePhone + '\'' +
                ", businessAddress='" + businessAddress + '\'' +
                ", shopNumber='" + shopNumber + '\'' +
                ", infoSource='" + infoSource + '\'' +
                ", shopId='" + shopId + '\'' +
                ", shopPic='" + shopPic + '\'' +
                ", shopOther='" + shopOther + '\'' +
                ", distance='" + distance + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }
}
