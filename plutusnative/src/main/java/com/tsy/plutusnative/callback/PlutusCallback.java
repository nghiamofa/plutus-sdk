package com.tsy.plutusnative.callback;

public abstract class PlutusCallback implements Callback {

    @Override
    public void error(String type, String errorCode, String errorMessage, Object errorDetails) {

    }

    @Override
    public void notImplemented(String type) {

    }
}