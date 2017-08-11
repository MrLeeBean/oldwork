package com.nfu.oldwork.model;

import java.util.List;

/**
 * Created by Administrator on 2017-7-25.
 */

public class TurnPicModel extends BaseInfo {

    /**
     * iResult : 0
     * strResult : [{"picTitle":"北京市老龄工作委员会召开2016年第二次全体会议","picurl":"http://www.bjageing.gov.cn/sdyl/viewpic?pic=xxfbk346pd3W.jpg"},{"picTitle":"2016年度北京\u201c孝星榜样\u201d揭晓暨\u201c孝星\u201d宣讲团首场报告会在京举行","picurl":"http://www.bjageing.gov.cn/sdyl/viewpic?pic=xxfbtB884653.jpg"},{"picTitle":"2016年度北京\u201c孝星榜样\u201d投票启动","picurl":"http://www.bjageing.gov.cn/sdyl/viewpic?pic=xxfb5382qjdc.jpg"}]
     */


    private List<StrResultBean> strResult;



    public List<StrResultBean> getStrResult() {
        return strResult;
    }

    public void setStrResult(List<StrResultBean> strResult) {
        this.strResult = strResult;
    }

    public static class StrResultBean {
        /**
         * picTitle : 北京市老龄工作委员会召开2016年第二次全体会议
         * picurl : http://www.bjageing.gov.cn/sdyl/viewpic?pic=xxfbk346pd3W.jpg
         */

        private String picTitle;
        private String picurl;
        private String id;

        public String getPicTitle() {
            return picTitle;
        }

        public void setPicTitle(String picTitle) {
            this.picTitle = picTitle;
        }

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "StrResultBean{" +
                    "picTitle='" + picTitle + '\'' +
                    ", picurl='" + picurl + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "TurnPicModel{" +
                "strResult=" + strResult +
                '}';
    }
}
