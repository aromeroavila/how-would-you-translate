package com.arao.hwyt.net;

/**
 * Implementations will contain all the data related to the request to be executed. This data
 * will be used by the {@link RequestFactory} to create a library specific Request
 *
 * @param <T> Result type of the Request
 */
public interface HwytRequest<T> {

    /**
     * @return Endpoint for this request. In case it is a GET request and this contains any
     * parameter, these should be included in the Url whenever this method is called
     */
    String getUrl();

    /**
     * @return The type of request base on {@link com.android.volley.Request.Method}
     */
    int getMethod();

    /**
     * @return The class name for the result type. To be used by {@link com.google.gson.Gson} to
     * extract the POJO from the result Json String
     */
    Class<T> getClazz();

    /**
     * @return The body of the request in case it is a POST request. In any other case this will
     * be null.
     * All requests submitted to the server will have a Json body
     */
    String getJsonBody();
}
