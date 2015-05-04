package com.arao.hwyt.net;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
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
    public <T> com.android.volley.Request getRequest(Request<T> request, BridgeListener<T> bridgeListener) {

        final Class<T> resultClazz = request.getResultClazz();

        return new JsonRequest<T>(request.getMethod(),
                request.getUrl(),
                mGson.toJson(request.getBody()),
                bridgeListener,
                bridgeListener) {

            @Override
            protected Response<T> parseNetworkResponse(NetworkResponse response) {
                try {
                    String json = new String(
                            response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    return Response.success(
                            mGson.fromJson(json, resultClazz),
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
