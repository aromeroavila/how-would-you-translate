package com.arao.hwyt.net.requests;

import com.arao.hwyt.model.User;
import com.arao.hwyt.model.requestParams.LoginRequestParams;

public class LoginRequest extends BaseRequest<User> {

    private LoginRequestParams mLoginRequestParams;

    public LoginRequest(LoginRequestParams loginRequestParams) {
        super();
        mLoginRequestParams = loginRequestParams;
    }

    @Override
    public String getUrl() {
        return mUrlProvider.getLoginUrl(mLoginRequestParams);
    }

    @Override
    public Class<User> getResultClazz() {
        return User.class;
    }
}
