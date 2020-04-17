package com.tsy.plutusnative.callback;

import androidx.annotation.Nullable;

import com.tsy.plutusnative.model.UserInfoResult;

public abstract class UserInfoCallback implements Callback {

    public abstract void onResponse(boolean isSuccess, @Nullable UserInfoResult userInfoResult);

    @Override
    public void success(String type, @Nullable Object result) {

    }

    @Override
    public void error(String type, String errorCode, String errorMessage, Object errorDetails) {

    }

    @Override
    public void notImplemented(String type) {

    }
}
