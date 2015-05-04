package com.arao.hwyt.net;

public class UrlProviderImpl implements UrlProvider {

    @Override
    public String getLoginUrl() {
        return "http://192.168.0.13:8080/hwyt_server/Login";
    }

    @Override
    public String getRegisterUrl() {
        return "http://192.168.0.13:8080/hwyt_server/Register";
    }
}
