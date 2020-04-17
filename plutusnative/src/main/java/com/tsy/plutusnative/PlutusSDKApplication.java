package com.tsy.plutusnative;

import io.flutter.app.FlutterApplication;
import io.flutter.view.FlutterMain;

public class PlutusSDKApplication extends FlutterApplication {
    @Override
    public void onCreate() {
        FlutterMain.startInitialization(this);
        super.onCreate();
    }
}
