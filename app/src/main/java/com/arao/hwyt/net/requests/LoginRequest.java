package com.arao.hwyt.net.requests;

import com.arao.hwyt.model.User;
import com.arao.hwyt.net.constants.RestApiConstants;
import com.arao.hwyt.util.PasswordEncrypter;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

public class LoginRequest extends BaseRequest<User> {

    @Override
    public String getUrl() {
        return mUrlProvider.getLoginUrl();
    }

    @Override
    public Class<User> getClazz() {
        return User.class;
    }

//    private static String getUrlWithEncryptedPassword(String username, String password) throws GeneralSecurityException,
//            UnsupportedEncodingException {
//        StringBuilder stringBuilder = new StringBuilder(URL);
//        stringBuilder.append("?");
//        stringBuilder.append(RestApiConstants.USER_NAME);
//        stringBuilder.append("=");
//        stringBuilder.append(username);
//        stringBuilder.append("&");
//        stringBuilder.append(RestApiConstants.PASSWORD);
//        stringBuilder.append("=");
//        stringBuilder.append(PasswordEncrypter.encrypt(password));
//        return stringBuilder.toString();
//    }
}
