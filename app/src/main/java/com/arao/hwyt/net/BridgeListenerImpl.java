package com.arao.hwyt.net;

import com.android.volley.VolleyError;

class BridgeListenerImpl<T> implements BridgeListener<T> {

    private RequestListener<T> mRequestListener;

    @Override
    public void setListener(RequestListener<T> requestListener) {
        mRequestListener = requestListener;
    }

    @Override
    public void onResponse(T response) {
        mRequestListener.onRequestSuccess(response);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        mRequestListener.onRequestFail();
    }
}
