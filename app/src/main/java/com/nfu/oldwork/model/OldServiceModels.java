package com.nfu.oldwork.model;

import java.util.List;

/**
 * Created by Administrator on 2017/8/4.
 */

public class OldServiceModels extends BaseInfo {
    /**
     * recordCount : 471
     * pageCount : 48
     * data : [{"contactPhone":"010-69785231","institutionName":"北京昌平安乐祥居老年公寓","institutionType":"养老机构","introduce":"","officeHours":"星期一至星期五每日上午9:00至12:00，下午13:30至17:00。法定节假日按照国家规定执行","id":"1","countyName":"昌平区","distance":"24135","address":"北京市昌平区南口镇龙虎台村","zipCode":"102202","longitude":"116.163266","latitude":"40.244772","institutionUrl":""},{"contactPhone":"13801395324","institutionName":"北京昌平区崔村镇敬老院","institutionType":"养老机构","introduce":"","officeHours":"星期一至星期五每日上午9:00至12:00，下午13:30至17:00。法定节假日按照国家规定执行","id":"2","countyName":"昌平区","distance":"38002","address":"北京市昌平区崔村镇政府北侧","zipCode":"102212","longitude":"116.359519","latitude":"40.226284","institutionUrl":""},{"contactPhone":"010-89786226","institutionName":"北京昌平喜洋洋养老服务中心","institutionType":"养老机构","introduce":"","officeHours":"星期一至星期五每日上午9:00至12:00，下午13:30至17:00。法定节假日按照国家规定执行","id":"3","countyName":"昌平区","distance":"41292","address":"北京市昌平区崔村镇南庄村东","zipCode":"102212","longitude":"116.393385","latitude":"40.220395","institutionUrl":""},{"contactPhone":"010-61780993","institutionName":"北京昌平玉福园敬老院","institutionType":"养老机构","introduce":"","officeHours":"星期一至星期五每日上午9:00至12:00，下午13:30至17:00。法定节假日按照国家规定执行","id":"4","countyName":"昌平区","distance":"44748","address":"北京市昌平区兴寿镇香屯村南","zipCode":"102211","longitude":"116.417368","latitude":"40.199175","institutionUrl":""},{"contactPhone":"010-89758116转1000","institutionName":"北京汇晨老年公寓","institutionType":"养老机构","introduce":"","officeHours":"星期一至星期五每日上午9:00至12:00，下午13:30至17:00。法定节假日按照国家规定执行","id":"5","countyName":"昌平区","distance":"48916","address":"北京昌平区北七家镇八仙庄","zipCode":"100209","longitude":"116.424634","latitude":"40.146314","institutionUrl":""},{"contactPhone":"010-61782469","institutionName":"北京龙脉养老服务有限公司","institutionType":"养老机构","introduce":"","officeHours":"星期一至星期五每日上午9:00至12:00，下午13:30至17:00。法定节假日按照国家规定执行","id":"6","countyName":"昌平区","distance":"44603","address":"北京市昌平区小汤山镇龙脉温泉花园小区120","zipCode":"102211","longitude":"116.406196","latitude":"40.186774","institutionUrl":""},{"contactPhone":"15011423505","institutionName":"北京隆华老年福利中心","institutionType":"养老机构","introduce":"","officeHours":"星期一至星期五每日上午9:00至12:00，下午13:30至17:00。法定节假日按照国家规定执行","id":"7","countyName":"昌平区","distance":"43514","address":"北京市昌平区兴寿镇桃林村果园北","zipCode":"102212","longitude":"116.434571036","latitude":"40.246313902","institutionUrl":""},{"contactPhone":"010-60713599","institutionName":"北京市昌平区爱地老人颐养中心","institutionType":"养老机构","introduce":"","officeHours":"星期一至星期五每日上午9:00至12:00，下午13:30至17:00。法定节假日按照国家规定执行","id":"8","countyName":"昌平区","distance":"29642","address":"昌平区水库路西侧朝凤社区（朝凤路临300号）","zipCode":"102200","longitude":"116.267375","latitude":"40.243203","institutionUrl":""},{"contactPhone":"13522574568","institutionName":"北京市昌平区百善镇敬老院","institutionType":"养老机构","introduce":"","officeHours":"星期一至星期五每日上午9:00至12:00，下午13:30至17:00。法定节假日按照国家规定执行","id":"9","countyName":"昌平区","distance":"39980","address":"北京市昌平区百善镇百善村东北","zipCode":"102211","longitude":"116.333698","latitude":"40.172182","institutionUrl":""},{"contactPhone":"13391621562","institutionName":"北京市昌平区北七家镇老年社区服务中心","institutionType":"养老机构","introduce":"","officeHours":"星期一至星期五每日上午9:00至12:00，下午13:30至17:00。法定节假日按照国家规定执行","id":"10","countyName":"昌平区","distance":"48728","address":"北京市昌平区北七家镇白庙村","zipCode":"102209","longitude":"116.392055","latitude":"40.114489","institutionUrl":""}]
     * pageSize : 10
     * currentPage : 1
     */

    private int recordCount;
    private int pageCount;
    private int pageSize;
    private int currentPage;
    private List<OldServiceModel> data;

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<OldServiceModel> getData() {
        return data;
    }

    public void setData(List<OldServiceModel> data) {
        this.data = data;
    }

}
