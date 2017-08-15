package com.nfu.oldwork.config;

/**
 * Created by wpp.
 *
 * @description
 * @date 2017/7/20
 */
public interface ConnectUrl {
    String baseUrl = "http://17103938iy.iask.in";

    String getNewsList = baseUrl + "/bjllapp/bjllXxfbQuery/bjllXxfbQueryAction.do?method=GetNewsList&signKey=" + ApiConfig.signKey;
    String getNewsDetail = baseUrl + "/bjllapp/bjllXxfbQuery/bjllXxfbQueryAction.do?method=GetNewsDetail&signKey=" + ApiConfig.signKey;
    String getTurnPic = baseUrl + "/bjllapp/bjllXxfbQuery/bjllXxfbQueryAction.do?method=GetTurnPicture&signKey=" + ApiConfig.signKey;
    String getOpinionFeedback = baseUrl + "/bjllapp/opinionBackManage/opinionBackManageAction.do?method=GetOpinionFeedback";
    String getBusinessConditions = baseUrl + "/bjllapp/bjllFwManage/bjllFwManageAction.do?method=GetBusinessConditions&signKey=" + ApiConfig.signKey;
    String getXbsFws = baseUrl + "/bjllapp/bjllFwManage/bjllFwManageAction.do?method=GetXbsFws&signKey=" + ApiConfig.signKey;
    String getNearXbsFws = baseUrl + "/bjllapp/bjllFwManage/bjllFwManageAction.do?method=GetNearXbsFws&signKey=" + ApiConfig.signKey;

    String getXbsFwsDetail = baseUrl + "/bjllapp/bjllFwManage/bjllFwManageAction.do?method=GetXbsFwsDetail&signKey=" + ApiConfig.signKey;
    String getNewsListByKey = baseUrl + "/bjllapp/bjllXxfbQuery/bjllXxfbQueryAction.do?method=GetKeywordNewsList&signKey=" + ApiConfig.signKey;
    String getOldWorkInstitutions = baseUrl + "/bjllapp/bjllXxfbQuery/bjllXxfbQueryAction.do?method=GetOldWorkInstitutions&signKey=" + ApiConfig.signKey;
    String getCityInstitutions = baseUrl + "/bjllapp/bjllXxfbQuery/bjllXxfbQueryAction.do?method=GetCityInstitutions&signKey=" + ApiConfig.signKey;

    String query_card = "https://fhzx.bjrcb.com/appoint/cardSchedulQuery.jhtml";
//

    String getPublicServiceAgencies = baseUrl + "/bjllapp/bjllFwManage/bjllFwManageAction.do?method=GetPublicServiceAgencies&signKey=" + ApiConfig.signKey;

    //String getOlderExpenseCalendar = "http://xcjjyl.hhxc.gov.cn:9527/healthy/externalApp/topUp";  //获取养老消费记录
    String getOlderExpenseCalendar = "http://zhika.laoling.bjmzj.gov.cn:8080/healthy/externalApp/trading"; //获取养老消费记录
    String getOlderBalance = "http://zhika.laoling.bjmzj.gov.cn:8080/healthy/externalApp/balance";  //获取余额

    String GetCommunicationList = baseUrl + "/bjllapp/appUserCommunication/appUserCommunicationAction.do?method=GetCommunicationList";  //获取交流详情
    String postCommQuestion = baseUrl + "/bjllapp/appUserCommunication/appUserCommunicationAction.do?method=GetAppUserCommunication";  //提交问题
    String GetLoginAppUser = baseUrl + "\n" + "/bjllapp/bjllUserManage/bjllUserManageAction.do?method=GetLoginAppUser";  //登录
    String GetUpdateAppUserPassword = baseUrl + "\n" + "/bjllapp/bjllUserManage/bjllUserManageAction.do?method=GetUpdateAppUserPassword";  //修改密码
    String getCommunicationDetail = baseUrl + "/bjllapp/appUserCommunication/appUserCommunicationAction.do?method=GetCommunicationDetail";  //获取交流详情
    String GetAppUserRespond = baseUrl + "/bjllapp/appUserCommunication/appUserCommunicationAction.do?method=GetAppUserRespond"; //回复交流详情
    String GetLoginAppYlUser = baseUrl + "/bjllapp/bjllUserManage/bjllUserManageAction.do?method=GetLoginAppYlUser";  //现有登录
}
