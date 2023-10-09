package com.example.exe201;

public class MyVoucher {
    private int voucherOrderID;
    private String userName;
    private String voucherCode;
    private String exchangeDate;

    private String myVoucherName;
    private String myVoucherDescription;

    public MyVoucher(int voucherOrderID, String userName, String voucherCode, String exchangeDate, String myVoucherName, String myVoucherDescription) {
        this.voucherOrderID = voucherOrderID;
        this.userName = userName;
        this.voucherCode = voucherCode;
        this.exchangeDate = exchangeDate;
        this.myVoucherName = myVoucherName;
        this.myVoucherDescription = myVoucherDescription;
    }

    public MyVoucher(String myVoucherName, String voucherCode, String myVoucherDescription) {
        this.voucherCode = voucherCode;
        this.myVoucherName = myVoucherName;
        this.myVoucherDescription = myVoucherDescription;
    }

    public String getMyVoucherName() {
        return myVoucherName;
    }

    public void setMyVoucherName(String myVoucherName) {
        this.myVoucherName = myVoucherName;
    }

    public String getMyVoucherDescription() {
        return myVoucherDescription;
    }

    public void setMyVoucherDescription(String myVoucherDescription) {
        this.myVoucherDescription = myVoucherDescription;
    }

    public int getVoucherOrderID() {
        return voucherOrderID;
    }

    public void setVoucherOrderID(int voucherOrderID) {
        this.voucherOrderID = voucherOrderID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public String getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(String exchangeDate) {
        this.exchangeDate = exchangeDate;
    }
}
