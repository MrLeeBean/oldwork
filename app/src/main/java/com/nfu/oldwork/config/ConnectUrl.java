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

}
