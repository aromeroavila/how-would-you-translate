package com.arao.hwyt.net.requests;

import com.arao.hwyt.net.Request;
import com.arao.hwyt.net.UrlProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static com.arao.hwyt.net.NetModule.urlProvider;

abstract class BaseRequest<T> implements Request<T> {

    protected final UrlProvider mUrlProvider;
    protected final Gson mGson;

    protected BaseRequest() {
        this(urlProvider(), new GsonBuilder().create());
    }

    protected BaseRequest(UrlProvider urlProvider, Gson gson) {
        mUrlProvider = urlProvider;
        mGson = gson;
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
    public String getJsonBody() {
        return null;
    }
}
