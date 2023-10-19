package com.example.exe201.models;

public class Order {
    private int orderID;
    private String orderCode;
    private String userName;
    private double materialAmount;
    private String createDate;
    private String status;
    private String address;
    private String orderDate;
    private String orderTime;
    private int orderPoint;

    public Order(int orderID, String orderCode, String userName, double materialAmount, String createDate, String status, String address, String orderDate, String orderTime) {
        this.orderID = orderID;
        this.orderCode = orderCode;
        this.userName = userName;
        this.materialAmount = materialAmount;
        this.createDate = createDate;
        this.status = status;
        this.address = address;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderPoint = 0;
    }

    public Order(int orderID, String orderCode, String userName, double materialAmount, String createDate, String status, String address, String orderDate, String orderTime, int orderPoint) {
        this.orderID = orderID;
        this.orderCode = orderCode;
        this.userName = userName;
        this.materialAmount = materialAmount;
        this.createDate = createDate;
        this.status = status;
        this.address = address;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderPoint = orderPoint;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getMaterialAmount() {
        return materialAmount;
    }

    public void setMaterialAmount(double materialAmount) {
        this.materialAmount = materialAmount;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public int getOrderPoint() {
        return orderPoint;
    }

    public void setOrderPoint(int orderPoint) {
        this.orderPoint = orderPoint;
    }
}
