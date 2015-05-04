package com.arao.hwyt.net;

/**
 * Implementations should provide a way to construct new {@link com.android.volley.Request}
 */
interface RequestFactory {

    /**
     * @param request    App specific request which holds the information required to execute
     *                       the request.
     * @param bridgeListener Listener that will propagate the response to the app specific
     *                       listener.
     * @param <T>            Type of the result of the request
     * @return A library specific {@link com.android.volley.Request} to be executed by the {@link RequestManager}
     */
    <T> com.android.volley.Request getRequest(Request<T> request, BridgeListener<T> bridgeListener);
}
