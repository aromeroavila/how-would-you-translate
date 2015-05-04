package com.arao.hwyt.model.requestParams;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CheckUserParams implements Parcelable {

    @SerializedName("usernameAvailability")
    private String mUsername;

    private CheckUserParams(Builder builder) {
        this.mUsername = builder.mUsername;
    }

    public static class Builder {

        private String mUsername;

        public Builder mUsername(String mUsername) {
            this.mUsername = mUsername;
            return this;
        }

        public CheckUserParams build() {
            return new CheckUserParams(this);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mUsername);
    }

    private CheckUserParams(Parcel in) {
        this.mUsername = in.readString();
    }

    public static final Parcelable.Creator<CheckUserParams> CREATOR = new Parcelable.Creator<CheckUserParams>() {
        public CheckUserParams createFromParcel(Parcel source) {
            return new CheckUserParams(source);
        }

        public CheckUserParams[] newArray(int size) {
            return new CheckUserParams[size];
        }
    };
}
