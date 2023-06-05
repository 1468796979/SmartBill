package com.pang.smartbill.db;

public class ChartItemBean {
    int sImageId;
    String type;
    float ratio;   //the proportion
    float totalMoney;  //The total amount of money for this item

    public ChartItemBean() {
    }

    public void setsImageId(int sImageId) {
        this.sImageId = sImageId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getsImageId() {
        return sImageId;
    }

    public String getType() {
        return type;
    }

    public float getRatio() {
        return ratio;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public ChartItemBean(int sImageId, String type, float ratio, float totalMoney) {
        this.sImageId = sImageId;
        this.type = type;
        this.ratio = ratio;
        this.totalMoney = totalMoney;
    }
}
