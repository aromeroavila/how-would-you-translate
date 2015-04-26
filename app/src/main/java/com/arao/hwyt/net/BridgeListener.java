package com.arao.hwyt.net;

import com.android.volley.Response;

/**
 * Implementations should provide a way to decouple the app specific {@link RequestListener} and the
 * library specific {@link com.android.volley.Response.Listener}
 */
interface BridgeListener<T> extends Response.Listener<T>, Response.ErrorListener {

    /**
     * Sets the app specific {@link RequestListener} so it gets notified when the library
     * specific is.
     */
    void setListener(RequestListener<T> requestListener);
}
