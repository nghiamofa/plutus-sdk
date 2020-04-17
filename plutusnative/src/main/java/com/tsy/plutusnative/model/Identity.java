package com.tsy.plutusnative.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Identity {
    @SerializedName("homeAddress")
    @Expose
    private String homeAddress;
    @SerializedName("idCard")
    @Expose
    private String idCard;
    @SerializedName("birthday")
    @Expose
    private String birthday;

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
