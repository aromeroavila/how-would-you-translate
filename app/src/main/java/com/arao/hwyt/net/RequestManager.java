package com.arao.hwyt.net;

public interface RequestManager {

    <T> void executeRequest(Request<T> request, RequestListener<T> requestListener);
}
