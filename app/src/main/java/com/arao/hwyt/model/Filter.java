package com.arao.hwyt.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Filter implements Parcelable {

    private String mFilterTitle;
    private List<Language> mOriginLanguages;
    private List<Language> mDestinationLanguages;
    private User mQuestionUser;
    private User mAnswerUser;

    public Filter() {

    }

    public Filter(Parcel in) {
        mFilterTitle = in.readString();
        in.readList(mOriginLanguages, Language.class.getClassLoader());
        in.readList(mDestinationLanguages, Language.class.getClassLoader());
        mQuestionUser = in.readParcelable(User.class.getClassLoader());
        mAnswerUser = in.readParcelable(User.class.getClassLoader());
    }


    public String getFilterTitle() {
        return mFilterTitle;
    }

    public void setFilterTitle(String filterTitle) {
        mFilterTitle = filterTitle;
    }

    public List<Language> getOriginLanguages() {
        return mOriginLanguages;
    }

    public void setOriginLanguage(List<Language> originLanguages) {
        mOriginLanguages = originLanguages;
    }

    public List<Language> getDestinationLanguage() {
        return mDestinationLanguages;
    }

    public void setDestinationLanguage(List<Language> destinationLanguages) {
        mDestinationLanguages = destinationLanguages;
    }

    public User getQuestionUser() {
        return mQuestionUser;
    }

    public void setQuestionUser(User questionUser) {
        mQuestionUser = questionUser;
    }


    public static final Creator<Filter> CREATOR = new Creator<Filter>() {
        public Filter createFromParcel(Parcel in) {
            return new Filter(in);
        }

        public Filter[] newArray(int size) {
            return new Filter[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mFilterTitle);
        dest.writeList(mOriginLanguages);
        dest.writeList(mDestinationLanguages);
        dest.writeParcelable(mQuestionUser, PARCELABLE_WRITE_RETURN_VALUE);
        dest.writeParcelable(mAnswerUser, PARCELABLE_WRITE_RETURN_VALUE);
    }
}
