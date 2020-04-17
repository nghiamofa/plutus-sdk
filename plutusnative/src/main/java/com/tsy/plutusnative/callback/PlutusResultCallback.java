package com.tsy.plutusnative.callback;

import androidx.annotation.Nullable;

import com.tsy.plutusnative.model.Result;

public abstract class PlutusResultCallback implements Callback {

    public abstract void onResult(String type, Result result);

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