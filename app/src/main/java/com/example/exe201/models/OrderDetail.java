package com.example.exe201.models;

public class OrderDetail {
    private String orderCode;
    private String materialName;
    private double materialAmount;
    private Material material;

    public OrderDetail(String orderCode, String materialName, double materialAmount, Material material) {
        this.orderCode = orderCode;
        this.materialName = materialName;
        this.materialAmount = materialAmount;
        this.material = material;
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

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
