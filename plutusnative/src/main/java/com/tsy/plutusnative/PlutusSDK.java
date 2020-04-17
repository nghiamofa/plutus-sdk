package com.tsy.plutusnative;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tsy.plutusnative.callback.LoginCallback;
import com.tsy.plutusnative.callback.PlutusCallback;
import com.tsy.plutusnative.callback.PlutusResultCallback;
import com.tsy.plutusnative.callback.SmsVerificationCallBack;
import com.tsy.plutusnative.callback.UserInfoCallback;
import com.tsy.plutusnative.model.Bank;
import com.tsy.plutusnative.model.Contact;
import com.tsy.plutusnative.model.LoginResult;
import com.tsy.plutusnative.model.Result;
import com.tsy.plutusnative.model.UserInfoResult;
import com.tsy.plutusnative.utils.PlutusContrants;
import com.tsy.plutusnative.utils.VerificationType;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.MethodChannel;

public class PlutusSDK implements PlutusSDKHelper {
    private final static String TAG = PlutusSDK.class.getSimpleName();
    private MethodChannel methodChannel;

    public PlutusSDK(FlutterEngine flutterEngine) {
        methodChannel = new MethodChannel(flutterEngine.getDartExecutor(), "com.tsy.plutus");
    }

    public PlutusSDK(Context context) {
        FlutterEngine flutterEngine = new FlutterEngine(context);
        flutterEngine.getDartExecutor().executeDartEntrypoint(
                DartExecutor.DartEntrypoint.createDefault()
        );
        methodChannel = new MethodChannel(flutterEngine.getDartExecutor(), "com.tsy.plutus");
    }

    @Override
    public void initialize(String secretKey, boolean isLoadedTodayNews, final PlutusCallback callback) {
        List<Object> arguments = generateArguments(secretKey, isLoadedTodayNews);
        final String type = PlutusContrants.init;
        methodChannel.invokeMethod(type, arguments, new MethodChannel.Result() {
            @Override
            public void success(Object result) {
                callback.success(type, result);
            }

            @Override
            public void error(String errorCode, String errorMessage, Object errorDetails) {
                callback.error(type, errorCode, errorMessage, errorDetails);
            }

            @Override
            public void notImplemented() {
                callback.notImplemented(type);
            }
        });
    }

    @Override
    public void getSmsVerificationType(String phone, String remoteConfigString, final SmsVerificationCallBack callback) {
        List<Object> arguments = generateArguments(phone, remoteConfigString);
        methodChannel.invokeMethod(PlutusContrants.getVerificationMethod, arguments, new MethodChannel.Result() {
            @Override
            public void success(Object result) {
                String type = String.valueOf(result);
                switch (type) {
                    case "firebase":
                        callback.onSuccess(VerificationType.FIREBASE);
                        break;
                    case "backend":
                        callback.onSuccess(VerificationType.BACKEND);
                        break;
                    default:
                        callback.onSuccess(VerificationType.UNKNOWN);
                }
            }

            @Override
            public void error(String errorCode, String errorMessage, Object errorDetails) {}

            @Override
            public void notImplemented() {}
        });
    }

    @Override
    public void login(String phone, String phoneModel, final LoginCallback callback) {
        List<Object> arguments = generateArguments(phone, phoneModel);
        methodChannel.invokeMethod(PlutusContrants.login, arguments, new MethodChannel.Result() {
            @Override
            public void success(Object result) {
                try {
                    JSONObject resultObject = new JSONObject(result.toString());
                    Log.e(TAG, resultObject.toString());
                    boolean isSuccess = resultObject.getBoolean("isSuccess");
                    String message = resultObject.getString("message");
                    String userId = resultObject.getString("userId");
                    int code = resultObject.getInt("code");
                    callback.onResponse(new LoginResult(code, message, isSuccess, userId));
                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onResponse(new LoginResult(-666, e.getMessage(), false, null));
                }
            }

            @Override
            public void error(String errorCode, String errorMessage, Object errorDetails) {
                Log.e(TAG, errorCode + errorMessage + errorDetails);
            }

            @Override
            public void notImplemented() {}
        });
    }

    @Override
    public void getAppName(final PlutusCallback callback) {
        final String type = PlutusContrants.getAppName;
        methodChannel.invokeMethod(type, null, new MethodChannel.Result() {
            @Override
            public void success(Object result) {
                callback.success(type, result);
            }

            @Override
            public void error(String errorCode, String errorMessage, Object errorDetails) {
                callback.error(type, errorCode, errorMessage, errorDetails);
            }

            @Override
            public void notImplemented() {
                callback.notImplemented(type);
            }
        });
    }

    @Override
    public void isSubmitting(final PlutusCallback callback) {
        final String type = PlutusContrants.isSubmitting;
        methodChannel.invokeMethod(type, null, new MethodChannel.Result() {
            @Override
            public void success(Object result) {
                callback.success(type, result);
            }

            @Override
            public void error(String errorCode, String errorMessage, Object errorDetails) {
                callback.error(type, errorCode, errorMessage, errorDetails);
            }

            @Override
            public void notImplemented() {
                callback.notImplemented(type);
            }
        });
    }

    @Override
    public void getNotice(final PlutusCallback callback) {
        final String type = PlutusContrants.getNotice;
        methodChannel.invokeMethod(type, null, new MethodChannel.Result() {
            @Override
            public void success(Object result) {
                callback.success(type, result);
            }

            @Override
            public void error(String errorCode, String errorMessage, Object errorDetails) {
                callback.error(type, errorCode, errorMessage, errorDetails);
            }

            @Override
            public void notImplemented() {
                callback.notImplemented(type);
            }
        });
    }

    @Override
    public void getUserInfo(UserInfoCallback callback) {
        getUserInfo(null, callback);
    }

    @Override
    public void getUserInfo(String userId, final UserInfoCallback callback) {
        List<Object> arguments = generateArguments(userId);
        methodChannel.invokeMethod(PlutusContrants.getInfo, arguments, new MethodChannel.Result() {
            @Override
            public void success(Object result) {
                try {
                    UserInfoResult userInfo = new Gson().fromJson(result.toString(), UserInfoResult.class);
                    if (userInfo != null) {
                        callback.onResponse(true, userInfo);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    callback.onResponse(false, null);
                }
            }

            @Override
            public void error(String errorCode, String errorMessage, Object errorDetails) {
                Log.e(TAG, errorCode + errorMessage + errorDetails);
                callback.error(PlutusContrants.getInfo, errorCode, errorMessage, errorDetails);
            }

            @Override
            public void notImplemented() {
                callback.notImplemented(PlutusContrants.getInfo);
            }
        });
    }

    @Override
    public void requestBackendOtp(String phone, final PlutusResultCallback callback) {
        List<Object> arguments = generateArguments(phone);
        final String type = PlutusContrants.requestBackendOtp;
        methodChannel.invokeMethod(type, arguments, new MethodChannel.Result() {
            @Override
            public void success(Object cb) {
                Result result = new Gson().fromJson(cb.toString(), Result.class);
                callback.onResult(type, result);
            }

            @Override
            public void error(String errorCode, String errorMessage, Object errorDetails) {
                Log.e(TAG, errorCode + errorMessage + errorDetails);
                callback.error(type, errorCode, errorMessage, errorDetails);
            }

            @Override
            public void notImplemented() {
                callback.notImplemented(type);
            }
        });
    }

    @Override
    public void verifyBackendOtp(String phone, String otp, String phoneModel, final PlutusResultCallback callback) {
        List<Object> arguments = generateArguments(phone, otp, phoneModel);
        final String type = PlutusContrants.loginWithOtp;
        methodChannel.invokeMethod(type, arguments, new MethodChannel.Result() {
            @Override
            public void success(Object cb) {
                Result result = new Gson().fromJson(cb.toString(), Result.class);
                callback.onResult(type, result);
            }

            @Override
            public void error(String errorCode, String errorMessage, Object errorDetails) {
                Log.e(TAG, errorCode + errorMessage + errorDetails);
                callback.error(type, errorCode, errorMessage, errorDetails);
            }

            @Override
            public void notImplemented() {
                callback.notImplemented(type);
            }
        });
    }

    @Override
    public void getBankList(final PlutusCallback callback) {
        final String type = PlutusContrants.getListBank;
        methodChannel.invokeMethod(type, null, new MethodChannel.Result() {
            @Override
            public void success(Object cb) {
                callback.success(type, cb);
            }

            @Override
            public void error(String errorCode, String errorMessage, Object errorDetails) {
                Log.e(TAG, errorCode + errorMessage + errorDetails);
                callback.error(type, errorCode, errorMessage, errorDetails);
            }

            @Override
            public void notImplemented() {
                callback.notImplemented(type);
            }
        });
    }

    @Override
    public void submitBankVerification(String bankName, String bankBranch, String accountName, String accountNo, PlutusResultCallback callback) {
        submitBankVerification(null, bankName, bankBranch, accountName, accountNo, callback);
    }

    @Override
    public void submitBankVerification(String userId, String bankName, String bankBranch, String accountName, String accountNo, final PlutusResultCallback callback) {
        List<Object> arguments = generateArguments(accountName, accountNo, bankName, bankBranch, userId);
        final String type = PlutusContrants.submitBankVerification;
        methodChannel.invokeMethod(type, arguments, new MethodChannel.Result() {
            @Override
            public void success(Object cb) {
                Result result = new Gson().fromJson(cb.toString(), Result.class);
                callback.onResult(type, result);
            }

            @Override
            public void error(String errorCode, String errorMessage, Object errorDetails) {
                Log.e(TAG, errorCode + errorMessage + errorDetails);
                callback.error(type, errorCode, errorMessage, errorDetails);
            }

            @Override
            public void notImplemented() {
                callback.notImplemented(type);
            }
        });
    }

    @Override
    public void submitIdentityVerification(String idNo, String birthday, String address, String photoFront, String photoBack, String photoHand, PlutusResultCallback callback) {
        submitIdentityVerification(null, idNo, birthday, address, photoFront, photoBack, photoHand, callback);
    }

    @Override
    public void submitIdentityVerification(String userId, String idNo, String birthday, String address, String photoFront, String photoBack, String photoHand, final PlutusResultCallback callback) {
        List<Object> arguments = generateArguments(photoFront, photoBack, photoHand, idNo, address, birthday, userId);
        final String type = PlutusContrants.submitIdentityVerification;
        methodChannel.invokeMethod(type, arguments, new MethodChannel.Result() {
            @Override
            public void success(Object cb) {
                Result result = new Gson().fromJson(cb.toString(), Result.class);
                callback.onResult(type, result);
            }

            @Override
            public void error(String errorCode, String errorMessage, Object errorDetails) {
                Log.e(TAG, errorCode + errorMessage + errorDetails);
                callback.error(type, errorCode, errorMessage, errorDetails);
            }

            @Override
            public void notImplemented() {
                callback.notImplemented(type);
            }
        });
    }

    @Override
    public void submitReferenceVerification(String phone1, String relationShip1, String name1, String phone2, String relationShip2, String name2, PlutusResultCallback callback) {
        submitReferenceVerification(null, phone1, relationShip1, name1, phone2, relationShip2, name2, callback);
    }

    @Override
    public void submitReferenceVerification(String userId, String phone1, String relationShip1, String name1, String phone2, String relationShip2, String name2, final PlutusResultCallback callback) {
        List<Object> arguments = generateArguments(name1, relationShip1, phone1, name2, relationShip2, phone2, userId);
        final String type = PlutusContrants.submitContractVerification;
        methodChannel.invokeMethod(type, arguments, new MethodChannel.Result() {
            @Override
            public void success(Object cb) {
                Result result = new Gson().fromJson(cb.toString(), Result.class);
                callback.onResult(type, result);
            }

            @Override
            public void error(String errorCode, String errorMessage, Object errorDetails) {
                Log.e(TAG, errorCode + errorMessage + errorDetails);
                callback.error(type, errorCode, errorMessage, errorDetails);
            }

            @Override
            public void notImplemented() {
                callback.notImplemented(type);
            }
        });
    }

    @Override
    public void uploadLocation(String lat, String lng, PlutusResultCallback callback) {
        uploadLocation(null, lat, lng, callback);
    }

    @Override
    public void uploadLocation(String userId, String lat, String lng, final PlutusResultCallback callback) {
        List<Object> arguments = generateArguments(lat, lng, userId);
        final String type = PlutusContrants.uploadLocation;
        methodChannel.invokeMethod(type, arguments, new MethodChannel.Result() {
            @Override
            public void success(Object cb) {
                Result result = new Gson().fromJson(cb.toString(), Result.class);
                callback.onResult(type, result);
            }

            @Override
            public void error(String errorCode, String errorMessage, Object errorDetails) {
                Log.e(TAG, errorCode + errorMessage + errorDetails);
                callback.error(type, errorCode, errorMessage, errorDetails);
            }

            @Override
            public void notImplemented() {
                callback.notImplemented(type);
            }
        });
    }

    @Override
    public void uploadContacts(List<Contact> contacts, PlutusResultCallback callback) {
        uploadContacts(null, contacts, callback);
    }

    @Override
    public void uploadContacts(String userId, List<Contact> contacts, final PlutusResultCallback callback) {
        List<Object> arguments = generateArguments(contacts, userId);
        final String type = PlutusContrants.uploadContracts;
        methodChannel.invokeMethod(type, arguments, new MethodChannel.Result() {
            @Override
            public void success(Object cb) {
                Result result = new Gson().fromJson(cb.toString(), Result.class);
                callback.onResult(type, result);
            }

            @Override
            public void error(String errorCode, String errorMessage, Object errorDetails) {
                Log.e(TAG, errorCode + errorMessage + errorDetails);
                callback.error(type, errorCode, errorMessage, errorDetails);
            }

            @Override
            public void notImplemented() {
                callback.notImplemented(type);
            }
        });
    }

    @Override
    public void submitLoan(String userId, String requestLoan, Object timeLength, final PlutusResultCallback callback) {
        List<Object> arguments = generateArguments(requestLoan, timeLength, userId);
        final String type = PlutusContrants.requestLoan;
        methodChannel.invokeMethod(type, arguments, new MethodChannel.Result() {
            @Override
            public void success(Object cb) {
                Result result = new Gson().fromJson(cb.toString(), Result.class);
                callback.onResult(type, result);
            }

            @Override
            public void error(String errorCode, String errorMessage, Object errorDetails) {
                Log.e(TAG, errorCode + errorMessage + errorDetails);
                callback.error(type, errorCode, errorMessage, errorDetails);
            }

            @Override
            public void notImplemented() {
                callback.notImplemented(type);
            }
        });
    }

    @Override
    public void getOssInformation(final PlutusCallback callback) {
        final String type = PlutusContrants.getOssInfo;
        methodChannel.invokeMethod(type, null, new MethodChannel.Result() {
            @Override
            public void success(Object cb) {
                callback.success(type, cb);
            }

            @Override
            public void error(String errorCode, String errorMessage, Object errorDetails) {
                Log.e(TAG, errorCode + errorMessage + errorDetails);
                callback.error(type, errorCode, errorMessage, errorDetails);
            }

            @Override
            public void notImplemented() {
                callback.notImplemented(type);
            }
        });
    }

    @Override
    public void uploadVideo(String userId, String path, final PlutusResultCallback callback) {
        List<Object> arguments = generateArguments(path, userId);
        final String type = PlutusContrants.uploadVideo;
        methodChannel.invokeMethod(type, arguments, new MethodChannel.Result() {
            @Override
            public void success(Object cb) {
                Result result = new Gson().fromJson(cb.toString(), Result.class);
                callback.onResult(type, result);
            }

            @Override
            public void error(String errorCode, String errorMessage, Object errorDetails) {
                Log.e(TAG, errorCode + errorMessage + errorDetails);
                callback.error(type, errorCode, errorMessage, errorDetails);
            }

            @Override
            public void notImplemented() {
                callback.notImplemented(type);
            }
        });
    }

    @Override
    public void uploadRepayBill(String userId, String path, final PlutusResultCallback callback) {
        List<Object> arguments = generateArguments(path, userId);
        final String type = PlutusContrants.uploadRepayBill;
        methodChannel.invokeMethod(type, arguments, new MethodChannel.Result() {
            @Override
            public void success(Object cb) {
                Result result = new Gson().fromJson(cb.toString(), Result.class);
                callback.onResult(type, result);
            }

            @Override
            public void error(String errorCode, String errorMessage, Object errorDetails) {
                Log.e(TAG, errorCode + errorMessage + errorDetails);
                callback.error(type, errorCode, errorMessage, errorDetails);
            }

            @Override
            public void notImplemented() {
                callback.notImplemented(type);
            }
        });
    }

    private List<Object> generateArguments(Object ...args) {
        return new ArrayList<>(Arrays.asList(args));
    }
}
