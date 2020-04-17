package com.tsy.testapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.tsy.plutusnative.PlutusSDK;
import com.tsy.plutusnative.callback.LoginCallback;
import com.tsy.plutusnative.callback.PlutusCallback;
import com.tsy.plutusnative.callback.PlutusResultCallback;
import com.tsy.plutusnative.callback.UserInfoCallback;
import com.tsy.plutusnative.model.Bank;
import com.tsy.plutusnative.model.LoginResult;
import com.tsy.plutusnative.model.Oss;
import com.tsy.plutusnative.model.Result;
import com.tsy.plutusnative.model.UserInfoResult;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    PlutusSDK plutusSDK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);
        findViewById(R.id.button10).setOnClickListener(this);
        findViewById(R.id.button11).setOnClickListener(this);
        findViewById(R.id.button12).setOnClickListener(this);
        findViewById(R.id.button13).setOnClickListener(this);
        findViewById(R.id.button14).setOnClickListener(this);
        findViewById(R.id.button15).setOnClickListener(this);
        findViewById(R.id.button16).setOnClickListener(this);
        findViewById(R.id.button17).setOnClickListener(this);
        findViewById(R.id.button18).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
//    {
//        "firebase": ["093"],
//        "be": ["090"],
//        "other": "firebase" // có thể là be
//    }
                String simulatedRemoteString = "{\"firebase\": [\"\"],\"be\": [\"090\"],\"other\": \"unknown\"}";
                plutusSDK.getSmsVerificationType("0932051902", simulatedRemoteString, verificationType -> {
                    Log.i("SDK", "Verification Typed :" + verificationType.name());
                });
                break;
            case R.id.button2:
                plutusSDK.login("0587321816", "iPhone 12", new LoginCallback() {
                    @Override
                    public void onResponse(LoginResult loginResult) {
                        Log.i("SDK", "Logged In :" + loginResult.getUserId());
                    }
                });
                break;
            case R.id.button3:
                Log.i("SDK", "Clicked");
                plutusSDK.getAppName(new PlutusCallback() {
                    @Override
                    public void success(String type, @Nullable Object result) {

                    }
                });
                break;
            case R.id.button4:
                plutusSDK.getNotice(new PlutusCallback() {
                    @Override
                    public void success(String type, @Nullable Object result) {

                    }
                });
                break;
            case R.id.button5:
                plutusSDK.isSubmitting(new PlutusCallback() {
                    @Override
                    public void success(String type, @Nullable Object result) {
                        Log.i("SDK", "is Submitng :" + result);
                    }
                });
                break;
            case R.id.button6:
                plutusSDK.getUserInfo(new UserInfoCallback() {
                    @Override
                    public void onResponse(boolean isSuccess, @Nullable UserInfoResult userInfoResult) {

                    }
                });
            case R.id.button7:
                plutusSDK.requestBackendOtp("0774967251", new PlutusResultCallback() {
                    @Override
                    public void onResult(String type, Result result) {
                        Log.i("SDK", result.toString());
                    }
                });
                break;
            case R.id.button8:
                plutusSDK.verifyBackendOtp("0774967251", "7134", "iPhone 20", new PlutusResultCallback() {
                    @Override
                    public void onResult(String type, Result result) {
                        Log.i("SDK", result.toString());
                    }
                });
                break;
            case R.id.button9:
                plutusSDK.getBankList(new PlutusCallback() {
                    @Override
                    public void success(String type, @Nullable Object result) {
                        if (result != null) {
                            List<Bank> banks = Bank.convertToList(result);
                            Log.i("SDK", banks.toString());
                        }
                    }
                });
                break;
            case R.id.button10:
                plutusSDK.submitBankVerification("Bank gì kệ tui", "Chi nhánh gì kệ tui", "Wukong", "123456789", new PlutusResultCallback() {
                    @Override
                    public void onResult(String type, Result result) {
                        Log.i("SDK", result.toString());
                    }
                });
                break;
            case R.id.button18:
                plutusSDK.getOssInformation(new PlutusCallback() {
                    @Override
                    public void success(String type, @Nullable Object result) {
                        Oss information = Oss.convertToOss(result);
                        Log.i("SDK", information.toString());
                    }
                });
                break;
            case R.id.button11:
                break;
            case R.id.button12:
                break;
            case R.id.button13:
                break;
            case R.id.button14:
                break;
            case R.id.button15:
                break;
            case R.id.button16:
                break;
            case R.id.button17:
                break;

        }
    }
}
