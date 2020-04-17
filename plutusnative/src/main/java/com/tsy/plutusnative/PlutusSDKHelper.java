package com.tsy.plutusnative;

import com.tsy.plutusnative.callback.LoginCallback;
import com.tsy.plutusnative.callback.PlutusCallback;
import com.tsy.plutusnative.callback.PlutusResultCallback;
import com.tsy.plutusnative.callback.SmsVerificationCallBack;
import com.tsy.plutusnative.callback.UserInfoCallback;
import com.tsy.plutusnative.model.Contact;

import java.util.List;

public interface PlutusSDKHelper {
    void initialize(String secretKey, boolean isLoadedTodayNews, final PlutusCallback callback);

    void getSmsVerificationType(String phone, String remoteConfigString, final SmsVerificationCallBack callback);

    void login(String phone, String phoneModel, final LoginCallback callback);

    void getAppName(final PlutusCallback callback);

    void isSubmitting(final PlutusCallback callback);

    void getNotice(final PlutusCallback callback);

    void getUserInfo(final UserInfoCallback callback);

    void getUserInfo(String userId, final UserInfoCallback callback);

    void requestBackendOtp(String phone, PlutusResultCallback callback);

    void verifyBackendOtp(String phone, String otp, String phoneModel, PlutusResultCallback callback);

    void getBankList(final PlutusCallback callback);

    void submitBankVerification(String bankName, String bankBranch, String accountName, String accountNo, PlutusResultCallback callback);

    void submitBankVerification(String userId, String bankName, String bankBranch, String accountName, String accountNo, PlutusResultCallback callback);

    void submitIdentityVerification(String idNo, String birthday, String address, String photoFront, String photoBack, String photoHand, PlutusResultCallback callback);

    void submitIdentityVerification(String userId, String idNo, String birthday, String address, String photoFront, String photoBack, String photoHand, PlutusResultCallback callback);

    void submitReferenceVerification(String phone1, String relationShip1, String name1, String phone2, String relationShip2, String name2, PlutusResultCallback callback);

    void submitReferenceVerification(String userId, String phone1, String relationShip1, String name1, String phone2, String relationShip2, String name2, PlutusResultCallback callback);

    void uploadLocation(String lat, String lng, PlutusResultCallback callback);

    void uploadLocation(String userId, String lat, String lng, PlutusResultCallback callback);

    void uploadContacts(List<Contact> contacts, PlutusResultCallback callback);

    void uploadContacts(String userId, List<Contact> contacts, PlutusResultCallback callback);

    void submitLoan(String userId, String requestLoan, Object timeLength, PlutusResultCallback callback);

    void getOssInformation(PlutusCallback callback);

    void uploadVideo(String userId, String path, PlutusResultCallback callback);

    void uploadRepayBill(String userId, String path, PlutusResultCallback callback);
}
