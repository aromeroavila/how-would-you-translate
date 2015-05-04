package com.arao.hwyt.net.requests;

import com.android.volley.Request;
import com.arao.hwyt.model.User;

public class RegisterRequest extends BaseRequest<User> {

    private User mUser;

    public RegisterRequest(User user) {
        super();
        mUser = user;
    }

    @Override
    public String getUrl() {
        return mUrlProvider.getRegisterUrl();
    }

    @Override
    public int getMethod() {
        return Request.Method.POST;
    }

    @Override
    public Class<User> getResultClazz() {
        return User.class;
    }

    @Override
    public Object getBody() {
        return mUser;
    }
}
