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
     "countryId":"所在区县ID",
     "streetId":"所在街道ID,
     "communityId":"所在社区ID",
     "levelId":"用户级别",
     "strError":"错误信息"
     */

    private String loginId;
    private String userId;
    private String userName;
    private String cityId;
    private String countryId;
    private String streetId;
    private String communityId;
    private String levelId;
    private LoginInfo loginInfo;

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
                ", countryId='" + countryId + '\'' +
                ", streetId='" + streetId + '\'' +
                ", communityId='" + communityId + '\'' +
                ", levelId='" + levelId + '\'' +
                ", loginInfo=" + loginInfo.toString() +
                '}';
    }
}
