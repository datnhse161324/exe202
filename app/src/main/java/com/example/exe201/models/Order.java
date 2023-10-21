package com.example.exe201.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Order implements Parcelable {
    private int orderID;
    private String orderCode;
    private String userName;
    private double materialAmount;
    private String createDate;
    private String status;
    private String getAddress;
    private String getDate;
    private String getTime;
    private int orderPoint;

    public Order(int orderID, String orderCode, String userName, double materialAmount, String createDate, String status, String getAddress, String getDate, String getTime) {
        this.orderID = orderID;
        this.orderCode = orderCode;
        this.userName = userName;
        this.materialAmount = materialAmount;
        this.createDate = createDate;
        this.status = status;
        this.getAddress = getAddress;
        this.getDate = getDate;
        this.getTime = getTime;
        this.orderPoint = 0;
    }

    public Order(int orderID, String orderCode, String userName, double materialAmount, String createDate, String status, String getAddress, String getDate, String getTime, int orderPoint) {
        this.orderID = orderID;
        this.orderCode = orderCode;
        this.userName = userName;
        this.materialAmount = materialAmount;
        this.createDate = createDate;
        this.status = status;
        this.getAddress = getAddress;
        this.getDate = getDate;
        this.getTime = getTime;
        this.orderPoint = orderPoint;
    }

    protected Order(Parcel in) {
        orderID = in.readInt();
        orderCode = in.readString();
        userName = in.readString();
        materialAmount = in.readDouble();
        createDate = in.readString();
        status = in.readString();
        getAddress = in.readString();
        getDate = in.readString();
        getTime = in.readString();
        orderPoint = in.readInt();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

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

    public String getGetAddress() {
        return getAddress;
    }

    public void setGetAddress(String getAddress) {
        this.getAddress = getAddress;
    }

    public String getGetDate() {
        return getDate;
    }

    public void setGetDate(String getDate) {
        this.getDate = getDate;
    }

    public String getGetTime() {
        return getTime;
    }

    public void setGetTime(String getTime) {
        this.getTime = getTime;
    }

    public int getOrderPoint() {
        return orderPoint;
    }

    public void setOrderPoint(int orderPoint) {
        this.orderPoint = orderPoint;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(orderID);
        dest.writeString(orderCode);
        dest.writeString(userName);
        dest.writeDouble(materialAmount);
        dest.writeString(createDate);
        dest.writeString(status);
        dest.writeString(getAddress);
        dest.writeString(getDate);
        dest.writeString(getTime);
        dest.writeInt(orderPoint);
    }
}
