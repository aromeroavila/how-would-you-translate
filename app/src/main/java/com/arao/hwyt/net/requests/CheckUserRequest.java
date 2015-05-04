package com.arao.hwyt.net.requests;

import com.android.volley.Request;
import com.arao.hwyt.model.requestParams.CheckUserParams;

public class CheckUserRequest extends BaseRequest<Boolean> {

    private CheckUserParams mCheckUserParams;

    public CheckUserRequest(CheckUserParams checkUserParams) {
        super();
        mCheckUserParams = checkUserParams;
    }

    @Override
    public String getUrl() {
        return mUrlProvider.getCheckUserUrl();
    }

    @Override
    public int getMethod() {
        return Request.Method.POST;
    }

    @Override
    public Class<Boolean> getResultClazz() {
        return Boolean.class;
    }

    @Override
    public Object getBody() {
        return mCheckUserParams;
    }
}
