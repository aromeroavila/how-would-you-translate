package com.arao.hwyt.net;

/**
 * Class responsible of injecting dependencies to the different components of the package
 * as well as offering instances to the public available components of the package.
 */
public class NetModule {

    static RequestManager requestManager() {
        return RequestManagerImpl.getRequestManager();
    }

    //----------------------------- Package scope injections -------------------------------------//

    static RequestFactory requestFactory() {
        return new RequestFactoryImpl();
    }

    static BridgeListenerFactory bridgeListenerFactory() {
        return new BridgeListenerFactoryImpl();
    }
}
