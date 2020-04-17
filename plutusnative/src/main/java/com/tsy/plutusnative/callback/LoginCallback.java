package com.tsy.plutusnative.callback;

import androidx.annotation.Nullable;

import com.tsy.plutusnative.model.LoginResult;

public abstract class LoginCallback implements Callback {

    public abstract void onResponse(LoginResult loginResult);

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
