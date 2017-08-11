package com.nfu.oldwork.model;

import java.util.List;

/**
 * Created by yiliu on 2017/8/5.
 */

public class Recorders {

    /**
     * total : 1
     * Items : [{"balance":"20000","time":"20170704"}]
     * status : Success
     */

    private int total;
    private String status;
    private List<ItemsBean> Items;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ItemsBean> getItems() {
        return Items;
    }

    public void setItems(List<ItemsBean> Items) {
        this.Items = Items;
    }

    public static class ItemsBean {
        /**
         * balance : 20000
         * time : 20170704
         */

        private String balance;
        private String time;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
