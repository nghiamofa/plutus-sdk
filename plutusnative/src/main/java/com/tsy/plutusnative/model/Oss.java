package com.tsy.plutusnative.model;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Oss {
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("bucket")
    @Expose
    private String bucket;
    @SerializedName("keyId")
    @Expose
    private String keyId;
    @SerializedName("keySecret")
    @Expose
    private String keySecret;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getKeySecret() {
        return keySecret;
    }

    public void setKeySecret(String keySecret) {
        this.keySecret = keySecret;
    }

    public static Oss convertToOss(Object object) {
        return new Gson().fromJson(object.toString(), Oss.class);
    }

    @Override
    public String toString() {
        return "Oss{" +
                "url='" + url + '\'' +
                ", bucket='" + bucket + '\'' +
                ", keyId='" + keyId + '\'' +
                ", keySecret='" + keySecret + '\'' +
                '}';
    }
}
