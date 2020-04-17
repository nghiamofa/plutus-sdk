package com.tsy.plutusnative.callback;

import androidx.annotation.Nullable;

public interface Callback {
    void success(String type, @Nullable Object result);
    void error(String type, String errorCode, String errorMessage, Object errorDetails);
    void notImplemented(String type);
}