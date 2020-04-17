package com.tsy.plutusnative.callback;

import com.tsy.plutusnative.utils.VerificationType;

public interface SmsVerificationCallBack {
    void onSuccess(VerificationType verificationType);
}
