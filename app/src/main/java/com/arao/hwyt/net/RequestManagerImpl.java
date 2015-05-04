package com.arao.hwyt.net;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import static com.arao.hwyt.controller.ControllerModule.appContext;
import static com.arao.hwyt.net.NetModule.bridgeListenerFactory;
import static com.arao.hwyt.net.NetModule.requestFactory;

class RequestManagerImpl implements RequestManager {

    private static RequestManagerImpl mRequestManagerImpl;

    private final RequestFactory mRequestFactory;
    private final BridgeListenerFactory mBridgeListenerFactory;

    private RequestQueue mRequestQueue;


    private RequestManagerImpl() {
        this(appContext(), requestFactory(), bridgeListenerFactory());
    }

    private RequestManagerImpl(Context context,
                               RequestFactory requestFactory,
                               BridgeListenerFactory bridgeListenerFactory) {
        mRequestQueue = Volley.newRequestQueue(context);
        mRequestFactory = requestFactory;
        mBridgeListenerFactory = bridgeListenerFactory;
    }

    static RequestManagerImpl getRequestManager() {
        if (mRequestManagerImpl == null) {
            mRequestManagerImpl = new RequestManagerImpl();
        }
        return mRequestManagerImpl;
    }

    @Override
    public <T> void executeRequest(Request<T> request, RequestListener<T> requestListener) {
        BridgeListener<T> bridgeListener = mBridgeListenerFactory.getBridgeListener();
        bridgeListener.setListener(requestListener);

        mRequestQueue.add(mRequestFactory.getRequest(request, bridgeListener));
    }
}
