package com.arao.hwyt.net;

import com.arao.hwyt.model.requestParams.LoginRequestParams;

public class UrlProviderImpl implements UrlProvider {

    // URL and Paths
    private final static String BASE_URL = "http://192.168.0.13:8080/hwyt_server";
    private final static String LOGIN_REQUEST_PATH = "/Login";
    private final static String REGISTER_REQUEST_PATH = "/Register";
    private final static String CHECK_USER_REQUEST_PATH = "/CheckUser";

    // Request parameter fields
    private final static String LOGIN_REQUEST_USERNAME_FIELD = "username";
    private final static String LOGIN_REQUEST_PASSWORD_FIELD = "password";


    @Override
    public String getLoginUrl(LoginRequestParams loginRequestParams) {
        return addParamsToLoginRequest(loginRequestParams);
    }

    @Override
    public String getRegisterUrl() {
        return BASE_URL + REGISTER_REQUEST_PATH;
    }

    @Override
    public String getCheckUserUrl() {
        return BASE_URL + CHECK_USER_REQUEST_PATH;
    }

    @SuppressWarnings("StringBufferReplaceableByString")
    private String addParamsToLoginRequest(LoginRequestParams loginRequestParams) {
        StringBuilder stringBuilder = new StringBuilder(BASE_URL);
        stringBuilder.append(LOGIN_REQUEST_PATH);
        stringBuilder.append("?");
        stringBuilder.append(LOGIN_REQUEST_USERNAME_FIELD);
        stringBuilder.append("=");
        stringBuilder.append(loginRequestParams.getmUsername());
        stringBuilder.append("&");
        stringBuilder.append(LOGIN_REQUEST_PASSWORD_FIELD);
        stringBuilder.append("=");
        stringBuilder.append(loginRequestParams.getmEncryptedPassword());
        return stringBuilder.toString();
    }
}
