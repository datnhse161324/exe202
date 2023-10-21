package com.example.exe201.models;

public class OrderDetail {
    private String orderCode;
    private String materialName;
    private double materialAmount;

    public OrderDetail(String orderCode, String materialName,double materialAmount) {
        this.orderCode = orderCode;
        this.materialName = materialName;
        this.materialAmount = materialAmount;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public double getMaterialAmount() {
        return materialAmount;
    }

    public void setMaterialAmount(double materialAmount) {
        this.materialAmount = materialAmount;
    }
}
