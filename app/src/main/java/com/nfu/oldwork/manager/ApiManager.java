package com.nfu.oldwork.manager;


import com.nfu.oldwork.config.ConnectUrl;
import com.nfu.oldwork.utils.LogUtil;
import com.nfu.oldwork.utils.NetUtil;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wpp.
 *
 * @description
 * @date 2017/7/20
 */
public class ApiManager {
    private static volatile ApiManager mInstance;
    private ApiManager(){
    }

    public static ApiManager getInstance(){
        if(mInstance == null){
            synchronized (ApiManager.class){
                if(mInstance == null){
                    mInstance = new ApiManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取新闻列表
     * @param dictionID 栏目ID {@link ApiConfig#dictionIDForInformation}{@link ApiConfig#dictionIdForMedia}
     *                  {@link ApiConfig#dictionIdForNotice}{@link ApiConfig#dictionIdForPolicy}
     *                  {@link ApiConfig#dictionIdForDynamics}
     * @param pageSize 每页记录条数
     * @param currentPage 当前第几页
     * @param iRecordCount 记录总数，第一页传0，第二次调用传上一次服务器返回的数据
     * @param sortBy 排序，可选{@link ApiConfig#sortByASC} {@link ApiConfig#sortByDESC}
     */
    public void getNewsList(String dictionID, int pageSize, int currentPage, int iRecordCount,String strOrderBy, String sortBy,Callback callback){
        String url = ConnectUrl.getNewsList + "&dictionid=" + dictionID + "&iPageSize=" + pageSize + "&iCurrentPage=" + currentPage
                + "&iRecordCount=" + iRecordCount + "&strOrderBy="+strOrderBy+"&strSortBy=" + sortBy;
        LogUtil.i("ApiManager--->getNewsList--->url::"+url);
        NetUtil.doGet(url,null,callback);
    }

    public void getNewsListByKey(String dictionID,String keyword,int pageSize, int currentPage, int iRecordCount,String strOrderBy,String sortBy,Callback callback){
        String url = ConnectUrl.getNewsListByKey + "&dictionid=" + dictionID + "&keyword=" + keyword + "&iPageSize=" + pageSize + "&iCurrentPage=" + currentPage
                + "&iRecordCount=" + iRecordCount + "&strOrderBy=" + strOrderBy + "&strSortBy=" + sortBy;
        LogUtil.i("ApiManager--->getNewsListByKey--->url::"+url);
        NetUtil.doGet(url,null,callback);
    }

    public void getAllNewsList(int pageSize, int currentPage, int iRecordCount,String strOrderBy, String sortBy,Callback callback){
        String url = ConnectUrl.getNewsList + "&iPageSize=" + pageSize + "&iCurrentPage=" + currentPage
                + "&iRecordCount=" + iRecordCount + "&strOrderBy="+strOrderBy+"&strSortBy=" + sortBy;
        LogUtil.i("ApiManager--->getNewsList--->url::"+url);
        NetUtil.doGet(url,null,callback);
    }

    /**
     * 获取新闻内容
     * @param newsId 新闻id
     */
    public void getNewsDetail(String newsId,Callback callback){
        String url = ConnectUrl.getNewsDetail + "&newsid=" + newsId;
        LogUtil.i("ApiManager--->getNewsDetail--->url::"+url);
        NetUtil.doGet(url,null,callback);
    }

    /**
     * 获取轮播图片
     * @param dictionID 栏目id {@link ApiConfig#dictionIDForInformation}{@link ApiConfig#dictionIdForMedia}
     *                  {@link ApiConfig#dictionIdForNotice}{@link ApiConfig#dictionIdForPolicy}
     *                  {@link ApiConfig#dictionIdForDynamics}
     */
    public void getTurnPic(String dictionID, Callback callback){
        String url = ConnectUrl.getTurnPic + "&dictionid=" + dictionID;
        LogUtil.i("ApiManager--->getTurnPic--->url::"+url);
        NetUtil.doGet(url,null,callback);
    }


    public void postOpinionFeedBack(String jsonStr,Callback callback){
        String url = ConnectUrl.getOpinionFeedback;
        LogUtil.i("ApiManager--->getOpinionFeedBack--->url::"+url + ",jsonStr::"+jsonStr);
        NetUtil.doPost(url,jsonStr,callback);
    }

    /**
     * 业务办理
     * @param businessId 业务ID {@link ApiConfig#businessForHeightAge}{@link ApiConfig#businessForHeightAgeLost}
     *                  {@link ApiConfig#businessForMedicalAid}{@link ApiConfig#businessForYouDaiKa}
     *                  {@link ApiConfig#businessForYouDaiZheng}{@link ApiConfig#businessForZhuCanKa}
     */
    public void getBusinessConditions(String businessId,Callback callback){
        String url = ConnectUrl.getBusinessConditions + "&businessid=" + businessId;
        LogUtil.i("ApiManager--->getBusinessConditions--->url::"+url);
        NetUtil.doGet(url,null,callback);
    }

    /**
     * 服务查询
     */
    public void getXbsFws(String serviceTypeId, int pageSize, int currentPage, int iRecordCount, String longitude ,String latitude,String shopName,Callback callback){
        String url = ConnectUrl.getXbsFws + "&serviceTypeId=" + serviceTypeId + "&iPageSize=" + pageSize + "&iCurrentPage=" + currentPage + "&iRecordCount=" + iRecordCount +"&longitude="+longitude+"&latitude=" +latitude+ "&shopName=" + shopName;
        LogUtil.i("ApiManager--->getNewsDetail--->url::"+url);
        NetUtil.doGet(url,null,callback);
    }
    /**
     * 附近服务商查询 GetNearXbsFws
     */
    public void getNearXbsFws(String serviceTypeId, int pageSize, int currentPage, int iRecordCount, String longitude ,String latitude,String shopName,Callback callback){
        String url = ConnectUrl.getNearXbsFws + "&serviceTypeId=" + serviceTypeId + "&iPageSize=" + pageSize + "&iCurrentPage=" + currentPage + "&iRecordCount=" + iRecordCount +"&longitude="+longitude+"&latitude=" +latitude+ "&shopName=" + shopName;
        LogUtil.i("ApiManager--->getNewsDetail--->url::"+url);
        NetUtil.doGet(url,null,callback);
    }
    public void getXbsFwsDetail(){

    }
    /*
     养老机构和社区驿站查询
/*  http://17103938iy.iask.in/bjllapp/bjllFwManage/bjllFwManageAction.do?method=GetPublicServiceAgencies&signKey=8002&serviceType=1&iPageSize=10
   &iCurrentPage=0&iRecordCount=0&insName=安乐祥居老年公寓&longitude=116.1042340&latitude=40.4705390*/

    public void getPublicServiceAgencies(String serviceType, int pageSize, int currentPage, int iRecordCount, String longitude ,String latitude,String insName,Callback callback){
        String url = ConnectUrl.getPublicServiceAgencies + "&serviceType=" + serviceType + "&iPageSize=" + pageSize + "&iCurrentPage=" + currentPage + "&iRecordCount=" + iRecordCount +"&longitude="+longitude+"&latitude=" +latitude+ "&insName=" + insName;
        LogUtil.i("ApiManager--->getPublicServiceAgencies--->url::"+url);
        NetUtil.doGet(url,null,callback);
    }


    public void getOldWorkInstitutions(int typeId,int pageSize,int currentPage,int iRecordCount, StringCallback callback){
        String url = ConnectUrl.getOldWorkInstitutions + "&typeId="+typeId+"&iPageSize=" + pageSize + "&iCurrentPage=" + currentPage + "&iRecordCount=" + iRecordCount;
        LogUtil.i("ApiManager--->getOldWorkInstitutions--->url::"+url);
        NetUtil.doGet(url,null,callback);
    }

    public void getOldWorkInstitutionsByName(int typeId,int pageSize,int currentPage,int iRecordCount,String insName,StringCallback callback){
        String url = ConnectUrl.getOldWorkInstitutions + "&typeId="+typeId+"&iPageSize=" + pageSize + "&iCurrentPage=" + currentPage + "&iRecordCount=" + iRecordCount + "&insName=" + insName;
        LogUtil.i("ApiManager--->getOldWorkInstitutions--->url::"+url);
        NetUtil.doGet(url,null,callback);
    }

    public void getCityInstitutions(StringCallback callback){
        String url = ConnectUrl.getCityInstitutions;
        LogUtil.i("ApiManager--->getCityInstitutions--->url::"+url);
        NetUtil.doGet(url,null,callback);
    }

    public void getOlderExpenseCalendar(String cnum,String page,String pageItemCnt,String btime,String etime,StringCallback callback){
        String url = ConnectUrl.getOlderExpenseCalendar;
        LogUtil.i("ApiManager--->getOlderExpenseCalendar--->url::"+url);
        Map<String,String> params = new HashMap<>();
        params.put("cnum",cnum);
        params.put("page",page);
        params.put("pageItemCnt",pageItemCnt);
        params.put("btime",btime);
        params.put("etime",etime);
        NetUtil.doPost(url,params,callback);
    }

    public void getOlderBalance(String cnum,String type,StringCallback callback){
        String url = ConnectUrl.getOlderBalance;
        LogUtil.i("ApiManager--->getOlderBalance--->url::"+url);
        Map<String,String> params = new HashMap<>();
        params.put("cnum",cnum);
        params.put("type",type);

        NetUtil.doPost(url,params,callback);
    }

    public void getCommunicationList(String jsonStr,StringCallback callback){
        String url = ConnectUrl.GetCommunicationList;
        LogUtil.i("ApiManager--->getCommunicationList--->url::"+url + ",jsonStr::"+jsonStr);
        NetUtil.doPost(url,jsonStr,callback);
    }

    public void postCommQuestion(String jsonStr,StringCallback callback){
        String url = ConnectUrl.postCommQuestion;
        LogUtil.i("ApiManager--->postCommQuestion--->url::"+url + ",jsonStr::"+jsonStr);
        NetUtil.doPost(url,jsonStr,callback);
    }
}
