package com.arao.hwyt.net;

import com.android.volley.Request;

/**
 * Implementations should provide a way to construct new {@link Request}
 */
interface RequestFactory {

    /**
     * @param hwytRequest    App specific request which holds the information required to execute
     *                       the request.
     * @param bridgeListener Listener that will propagate the response to the app specific
     *                       listener.
     * @param <T>            Type of the result of the request
     * @return A library specific {@link Request} to be executed by the {@link RequestManager}
     */
    <T> Request<T> getREquest(HwytRequest<T> hwytRequest, BridgeListener<T> bridgeListener);
}
