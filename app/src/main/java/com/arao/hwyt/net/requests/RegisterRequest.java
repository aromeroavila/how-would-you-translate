package com.arao.hwyt.net.requests;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.arao.hwyt.model.User;
import com.arao.hwyt.net.constants.RestApiConstants;
import com.arao.hwyt.util.PasswordEncrypter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

public class RegisterRequest extends JsonRequest<Integer> {

    private final static String RELATIVE_URL = "http://192.168.0.13:8080/hwyt_server/Register";

    private Response.Listener<Integer> mListener;

    public RegisterRequest(User user, Response.Listener<Integer> listener, Response.ErrorListener errorListener)
            throws UnsupportedEncodingException, GeneralSecurityException {
        super(Method.POST, RELATIVE_URL, getUserJsonWithEncryptedPassword(user), listener, errorListener);
        mListener = listener;
    }

    @Override
    protected Response<Integer> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonUserId = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            JsonObject responseJsonObject = new JsonParser().parse(jsonUserId).getAsJsonObject();
            if (responseJsonObject.has(RestApiConstants.REGISTER_SUCCEED_USER_ID_RESPONSE)) {
                JsonElement jsonElement = responseJsonObject.get(RestApiConstants.REGISTER_SUCCEED_USER_ID_RESPONSE);
                int userId = jsonElement.getAsInt();
                return Response.success(userId, HttpHeaderParser.parseCacheHeaders(response));
            } else {
                return Response.error(new ParseError(response));
            }
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(Integer response) {
        mListener.onResponse(response);
    }

    private static String getUserJsonWithEncryptedPassword(User user) throws GeneralSecurityException, UnsupportedEncodingException {
        user.setPassword(PasswordEncrypter.encrypt(user.getPassword()));
        Gson gson = new GsonBuilder().create();
        return gson.toJson(user, User.class);
    }
}
