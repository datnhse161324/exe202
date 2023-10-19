package com.example.exe201.models;

public class Material {
    private int materialID;
    private String materialName;
    private int unitPrice;

    public Material(int materialID, String materialName, int unitPrice) {
        this.materialID = materialID;
        this.materialName = materialName;
        this.unitPrice = unitPrice;
    }

    public int getMaterialID() {
        return materialID;
    }

    public void setMaterialID(int materialID) {
        this.materialID = materialID;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }
}
