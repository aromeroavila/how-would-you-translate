package com.arao.hwyt.model.requestParams;

import android.os.Parcel;
import android.os.Parcelable;

public class LoginRequestParams implements Parcelable {

    private String mUsername;
    private String mEncryptedPassword;

    private LoginRequestParams(Builder builder) {
        this.mUsername = builder.mUsername;
        this.mEncryptedPassword = builder.mEncryptedPassword;
    }

    public String getmEncryptedPassword() {
        return mEncryptedPassword;
    }

    public String getmUsername() {
        return mUsername;
    }

    public static class Builder {

        private String mUsername;
        private String mEncryptedPassword;

        public Builder mUsername(String mUsername) {
            this.mUsername = mUsername;
            return this;
        }

        public Builder mEncryptedPassword(String mEncryptedPassword) {
            this.mEncryptedPassword = mEncryptedPassword;
            return this;
        }

        public LoginRequestParams build() {
            return new LoginRequestParams(this);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mUsername);
        dest.writeString(this.mEncryptedPassword);
    }

    private LoginRequestParams(Parcel in) {
        this.mUsername = in.readString();
        this.mEncryptedPassword = in.readString();
    }

    public static final Parcelable.Creator<LoginRequestParams> CREATOR = new Parcelable.Creator<LoginRequestParams>() {
        public LoginRequestParams createFromParcel(Parcel source) {
            return new LoginRequestParams(source);
        }

        public LoginRequestParams[] newArray(int size) {
            return new LoginRequestParams[size];
        }
    };
}
