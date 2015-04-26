package com.arao.hwyt.net;

class BridgeListenerFactoryImpl implements BridgeListenerFactory {

    @Override
    public <T> BridgeListener<T> getBridgeListener() {
        return new BridgeListenerImpl<T>();
    }
}
