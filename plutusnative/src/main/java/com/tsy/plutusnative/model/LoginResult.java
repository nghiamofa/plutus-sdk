package com.tsy.plutusnative.model;

public class LoginResult extends Result {
    private final String userId;

    public LoginResult(int code, String message, boolean isSuccess, String userId) {
        super(code, isSuccess, message);
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
