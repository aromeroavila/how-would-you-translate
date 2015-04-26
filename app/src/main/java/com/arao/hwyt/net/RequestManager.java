package com.arao.hwyt.net;

public interface RequestManager {

    <T> void executeRequest(HwytRequest<T> request, RequestListener<T> requestListener);
}
