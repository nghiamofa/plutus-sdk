package com.tsy.plutusnative.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserInfoResult {
    @SerializedName("creditLimit")
    @Expose
    private String creditLimit;
    @SerializedName("creditLimitForDisplaying")
    @Expose
    private String creditLimitForDisplaying;
    @SerializedName("loanValues")
    @Expose
    private Map<String, List<Double>> loanValues;
    @SerializedName("statusIndex")
    @Expose
    private Integer statusIndex;
    @SerializedName("isBankVerified")
    @Expose
    private Boolean isBankVerified;
    @SerializedName("isReferencesVerified")
    @Expose
    private Boolean isReferencesVerified;
    @SerializedName("isIdentityVerified")
    @Expose
    private Boolean isIdentityVerified;
    @SerializedName("isJobVerified")
    @Expose
    private Boolean isJobVerified;
    @SerializedName("isUploadedRepayBill")
    @Expose
    private Boolean isUploadedRepayBill;
    @SerializedName("requestedLoan")
    @Expose
    private String requestedLoan;
    @SerializedName("requestedLoanPeriod")
    @Expose
    private String requestedLoanPeriod;
    @SerializedName("actualReceivedLoan")
    @Expose
    private String actualReceivedLoan;
    @SerializedName("expiredTime")
    @Expose
    private String expiredTime;
    @SerializedName("expiredDayCount")
    @Expose
    private String expiredDayCount;
    @SerializedName("expiredFee")
    @Expose
    private String expiredFee;
    @SerializedName("repayBankListInfo")
    @Expose
    private List<Bank> repayBankListInfo;
    @SerializedName("userIdentity")
    @Expose
    private Identity userIdentity;
    @SerializedName("userBankCard")
    @Expose
    private Bank userBankCard;

    public String getCreditLimit() {
        return creditLimit;
    }

    public String getCreditLimitForDisplaying() {
        return creditLimitForDisplaying;
    }

    public Map<String, List<Double>> getLoanValues() {
        return loanValues;
    }

    public Status getStatusIndex() {
        if (statusIndex != null)
            return Status.values()[statusIndex];
        return Status.AVAILABLE_TO_REQUEST;
    }

    public Boolean getBankVerified() {
        return isBankVerified;
    }

    public Boolean getReferencesVerified() {
        return isReferencesVerified;
    }

    public Boolean getIdentityVerified() {
        return isIdentityVerified;
    }

    public Boolean getJobVerified() {
        return isJobVerified;
    }

    public Boolean getUploadedRepayBill() {
        return isUploadedRepayBill;
    }

    public String getRequestedLoan() {
        return requestedLoan;
    }

    public String getRequestedLoanPeriod() {
        return requestedLoanPeriod;
    }

    public String getActualReceivedLoan() {
        return actualReceivedLoan;
    }

    public String getExpiredTime() {
        return expiredTime;
    }

    public String getExpiredDayCount() {
        return expiredDayCount;
    }

    public String getExpiredFee() {
        return expiredFee;
    }

    public List<Bank> getRepayBankListInfo() {
        return repayBankListInfo;
    }

    public Identity getUserIdentity() {
        return userIdentity;
    }

    public Bank getUserBankCard() {
        return userBankCard;
    }

    public Map<String, List<Double>> filterAvailableValues() {
        Map<String, List<Double>> aCopiedOfValues = new HashMap<>(this.loanValues);

        for(Iterator<String> iterator = aCopiedOfValues.keySet().iterator(); iterator.hasNext();) {
            String key = iterator.next();
            if(aCopiedOfValues.get(key) == null || aCopiedOfValues.get(key).isEmpty()) {
                iterator.remove();
            }
        }
        return aCopiedOfValues;
    }
}