package com.example.exe201.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Material implements Parcelable {
    private int materialID;
    private String materialName;
    private int unitPrice;

    public Material(int materialID, String materialName, int unitPrice) {
        this.materialID = materialID;
        this.materialName = materialName;
        this.unitPrice = unitPrice;
    }

    protected Material(Parcel in) {
        materialID = in.readInt();
        materialName = in.readString();
        unitPrice = in.readInt();
    }

    public static final Creator<Material> CREATOR = new Creator<Material>() {
        @Override
        public Material createFromParcel(Parcel in) {
            return new Material(in);
        }

        @Override
        public Material[] newArray(int size) {
            return new Material[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(materialID);
        dest.writeString(materialName);
        dest.writeInt(unitPrice);
    }
}
