package com.nfu.oldwork.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/15.
 */

public class UserInfo extends BaseInfo implements Serializable {

    /**

     * "loginId":"登录名",
     "userId":"用户ID",
     "userName":"用户名称",
     "cityId":"所在市级ID",
     "cityName":"所属市级名称,
     "countryId":"所属区县ID",
     "countryName":"所属区县名称",
     "streetId":"所属街道ID",
     "streetName":"所属街道名称",
     "communityId":"所属社区ID",
     "communityName":"所属社区名称",
     "deptName":"所属部门名称",
     "levelId":"用户级别",
     "strError":"错误信息
     */


    private String loginId;
    private String userId;
    private String userName;
    private String cityId;
    private String communityId;
    private String countryId;
    private String cityName;
    private String countryName;
    private String streetName;
    private String communityName;
    private String deptName;
    private String streetId;

    private String levelId;
    private LoginInfo loginInfo;
    private boolean isLoginSuccess;
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }


    public boolean isLoginSuccess() {
        return isLoginSuccess;
    }

    public void setLoginSuccess(boolean loginSuccess) {
        isLoginSuccess = loginSuccess;
    }



    public LoginInfo getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
    }




    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getStreetId() {
        return streetId;
    }

    public void setStreetId(String streetId) {
        this.streetId = streetId;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getLevelId() {
        return levelId;
    }



    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "loginId='" + loginId + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", cityId='" + cityId + '\'' +
                ", communityId='" + communityId + '\'' +
                ", countryId='" + countryId + '\'' +
                ", cityName='" + cityName + '\'' +
                ", countryName='" + countryName + '\'' +
                ", streetName='" + streetName + '\'' +
                ", communityName='" + communityName + '\'' +
                ", deptName='" + deptName + '\'' +
                ", streetId='" + streetId + '\'' +
                ", levelId='" + levelId + '\'' +
                ", loginInfo=" + loginInfo +
                ", isLoginSuccess=" + isLoginSuccess +
                '}';
    }
}
