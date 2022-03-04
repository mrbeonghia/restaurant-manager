package com.salon.custom.service.authentication;

import com.salon.custom.security.CustomUserDetail;

public class TokenVerifyResult {

    private boolean isValid;
    private Long authEventId;
    private CustomUserDetail userDetail;

    public TokenVerifyResult() {
    }

    public TokenVerifyResult(boolean isValid) {
        this.isValid = isValid;
    }

    public TokenVerifyResult(boolean isValid, Long authEventId) {
        this.isValid = isValid;
        this.authEventId = authEventId;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public Long getAuthEventId() {
        return authEventId;
    }

    public void setAuthEventId(Long authEventId) {
        this.authEventId = authEventId;
    }

    public CustomUserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(CustomUserDetail userDetail) {
        this.userDetail = userDetail;
    }
}
