package com.arao.hwyt.net;

import com.arao.hwyt.model.requestParams.LoginRequestParams;

/**
 * Implementations should provide the different endpoints the app will communicate with
 */
public interface UrlProvider {

    /**
     * @param loginRequestParams GET parameters to be added as query parameters to the URL
     * @return Complete URL for the {@link com.arao.hwyt.net.requests.LoginRequest}
     */
    String getLoginUrl(LoginRequestParams loginRequestParams);

    /**
     * @return URL for the {@link com.arao.hwyt.net.requests.RegisterRequest}
     */
    String getRegisterUrl();

    /**
     * @return URL for the {@link com.arao.hwyt.net.requests.CheckUserRequest}
     */
    String getCheckUserUrl();
}
