package com.arao.hwyt.net.requests;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.arao.hwyt.net.constants.RestApiConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

public class CheckExistUserRequest extends JsonRequest<Boolean> {

    private final static String RELATIVE_URL = "http://192.168.0.13:8080/hwyt_server/Register";

    private Response.Listener<Boolean> mListener;

    public CheckExistUserRequest(String userId, Response.Listener<Boolean> listener, Response.ErrorListener errorListener)
            throws UnsupportedEncodingException, GeneralSecurityException {
        super(Method.POST, RELATIVE_URL, getCheckUserJsonData(userId), listener, errorListener);
        mListener = listener;
    }

    @Override
    protected Response<Boolean> parseNetworkResponse(NetworkResponse response) {
        try {
            String usernameAvailableJson = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            JsonObject responseJsonObject = new Gson().fromJson(usernameAvailableJson, JsonObject.class);
            if (responseJsonObject.has(RestApiConstants.REGISTER_CHECK_USERNAME_AVAILABILITY_JSON_FIELD_NAME)) {
                JsonElement jsonElement = responseJsonObject.get(RestApiConstants.REGISTER_CHECK_USERNAME_AVAILABILITY_JSON_FIELD_NAME);
                boolean usernameAvailable = jsonElement.getAsBoolean();
                return Response.success(usernameAvailable, HttpHeaderParser.parseCacheHeaders(response));
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
    protected void deliverResponse(Boolean response) {
        mListener.onResponse(response);
    }

    private static String getCheckUserJsonData(String username) throws GeneralSecurityException, UnsupportedEncodingException {
        Gson gson = new GsonBuilder().create();
        JsonObject requestJsonObject = new JsonObject();
        requestJsonObject.addProperty(RestApiConstants.REGISTER_CHECK_USERNAME_AVAILABILITY_JSON_FIELD_NAME, username);
        return gson.toJson(requestJsonObject);
    }
}
