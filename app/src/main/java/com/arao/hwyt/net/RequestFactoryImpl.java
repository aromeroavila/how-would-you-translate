package com.arao.hwyt.net;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;

class RequestFactoryImpl implements RequestFactory {

    private final Gson mGson;

    RequestFactoryImpl() {
        mGson = new GsonBuilder().create();
    }

    @Override
    public <T> Request<T> getREquest(HwytRequest<T> hwytRequest, BridgeListener<T> bridgeListener) {

        final Class<T> clazz = hwytRequest.getClazz();

        return new JsonRequest<T>(hwytRequest.getMethod(),
                hwytRequest.getUrl(),
                hwytRequest.getJsonBody(),
                bridgeListener,
                bridgeListener) {

            @Override
            protected Response<T> parseNetworkResponse(NetworkResponse response) {
                try {
                    String json = new String(
                            response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    return Response.success(
                            mGson.fromJson(json, clazz),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JsonSyntaxException e) {
                    return Response.error(new ParseError(e));
                }
            }
        };
    }
}