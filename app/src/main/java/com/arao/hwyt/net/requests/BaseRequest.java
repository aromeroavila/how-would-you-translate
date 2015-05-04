package com.arao.hwyt.net.requests;

import com.arao.hwyt.net.Request;
import com.arao.hwyt.net.UrlProvider;

import static com.arao.hwyt.net.NetModule.urlProvider;

abstract class BaseRequest<T> implements Request<T> {

    protected final UrlProvider mUrlProvider;

    protected BaseRequest() {
        this(urlProvider());
    }

    protected BaseRequest(UrlProvider urlProvider) {
        mUrlProvider = urlProvider;
    }

    /**
     * By default, request will use GET HttpMethod
     */
    @Override
    public int getMethod() {
        return com.android.volley.Request.Method.GET;
    }

    /**
     * By default, request don't have any body
     */
    @Override
    public Object getBody() {
        return null;
    }
}
