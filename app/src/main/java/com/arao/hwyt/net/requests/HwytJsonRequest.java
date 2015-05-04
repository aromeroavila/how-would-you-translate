//package com.arao.hwyt.net.requests;//package romero.angel.hwyt.comm;
//
//import com.android.volley.NetworkResponse;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonRequest;
//
//public class HwytJsonRequest<T> extends JsonRequest<T> {
//
//    private final static String BASE_URL = "http://192.168.0.13:8080/hwyt_server";
//
//    protected Response.Listener<T> mListener;
//    protected HwytRequestListener mHwytRequestListener;
//
//    public HwytJsonRequest(HwytRequestListener hwytRequestListener, int method,
//                           String relativeUrl, String requestBody, Response.Listener<T> listener, Response.ErrorListener errorListener) {
//        super(method, BASE_URL + relativeUrl, requestBody, listener, errorListener);
//
//        mListener = listener;
//        mHwytRequestListener = hwytRequestListener;
//        mHwytRequestListener.onRequestStarted("Server", "retrieving data");
//    }
//
//    @Override
//    protected Response<T> parseNetworkResponse(NetworkResponse response) {
//        return null;
//    }
//
//    @Override
//    protected void deliverResponse(T response) {
//        mListener.onResponse(response);
//        mHwytRequestListener.onRequestFinished();
//    }
//
//    @Override
//    public void deliverError(VolleyError error) {
//        super.deliverError(error);
//        mHwytRequestListener.onRequestFinished();
//    }
//}
