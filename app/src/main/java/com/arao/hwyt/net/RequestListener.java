package com.arao.hwyt.net;

public interface RequestListener<T> {

    void onRequestSuccess(T response);

    // TODO Make this to receive a generic error that matches VolleyError
    void onRequestFail();
}
