package com.example.exe201;

public class Voucher {
    private int voucherID;
    private String voucherName;
    private String expiredDate;
    private int voucherPrice;
    private String description;

    public Voucher(int voucherID, String voucherName, String expiredDate, int voucherPrice, String description) {
        this.voucherID = voucherID;
        this.voucherName = voucherName;
        this.expiredDate = expiredDate;
        this.voucherPrice = voucherPrice;
        this.description = description;
    }

    public int getVoucherID() {
        return voucherID;
    }

    public void setVoucherID(int voucherID) {
        this.voucherID = voucherID;
    }

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public int getVoucherPrice() {
        return voucherPrice;
    }

    public void setVoucherPrice(int voucherPrice) {
        this.voucherPrice = voucherPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
