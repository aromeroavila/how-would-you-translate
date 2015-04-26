package com.arao.hwyt.net;

/**
 * Implementations will construct new instances of {@link BridgeListener}
 */
interface BridgeListenerFactory {

    /**
     * @param <T> Result type of the listeners
     * @return A new instance of {@link BridgeListener}
     */
    <T> BridgeListener<T> getBridgeListener();
}
