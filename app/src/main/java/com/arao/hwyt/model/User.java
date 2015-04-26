package com.arao.hwyt.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class User implements Parcelable {

    private int mId;
    private String mName;
    private String mPassword;
    private Language mMotherLanguage;
    private List<Language> mSpokenLanguages;
    /**
     * Resource id for the avatar image
     */
    private int mAvatarImage;
    private int mRating;


    public User() {
    }

    public User(String name, String password, Language motherLanguage, List<Language> spokenLanguages) {
        this(-1, name, password, motherLanguage, spokenLanguages, 0, 0);
    }

    public User(int id, String name, String password, Language motherLanguage, List<Language> spokenLanguages, int avatarImage, int rating) {
        mId = id;
        mName = name;
        mPassword = password;
        mMotherLanguage = motherLanguage;
        mSpokenLanguages = spokenLanguages;
        mAvatarImage = avatarImage;
        mRating = rating;
    }

    private User(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mPassword = in.readString();
        mMotherLanguage = in.readParcelable(Language.class.getClassLoader());
        in.readList(mSpokenLanguages, Language.class.getClassLoader());
        mAvatarImage = in.readInt();
        mRating = in.readInt();
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public Language getMotherLanguage() {
        return mMotherLanguage;
    }

    public void setMotherLanguage(Language mMotherLanguage) {
        this.mMotherLanguage = mMotherLanguage;
    }

    public List<Language> getSpokenLanguages() {
        return mSpokenLanguages;
    }

    public void setSpokenLanguages(List<Language> mSpokenLanguages) {
        this.mSpokenLanguages = mSpokenLanguages;
    }

    public int getAvatarImage() {
        return mAvatarImage;
    }

    public void setAvatarImage(int avatarImage) {
        mAvatarImage = avatarImage;
    }

    public int getRating() {
        return mRating;
    }

    public void setRating(int mRating) {
        this.mRating = mRating;
    }


    public static final Creator<User> CREATOR = new Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeString(mPassword);
        dest.writeParcelable(mMotherLanguage, Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
        dest.writeList(mSpokenLanguages);
        dest.writeInt(mAvatarImage);
        dest.writeInt(mRating);
    }
}
