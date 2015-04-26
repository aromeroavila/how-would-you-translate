package com.arao.hwyt.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.arao.hwyt.model.enums.LanguageConstant;
import com.arao.hwyt.model.enums.LanguageLevel;

public class Language implements Parcelable {

    private LanguageConstant mLanguageConstant;
    private LanguageLevel mLanguageLevel;


    public Language(LanguageConstant languageConstant, LanguageLevel languageLevel) {
        mLanguageConstant = languageConstant;
        mLanguageLevel = languageLevel;
    }

    public Language(Parcel in) {
        mLanguageConstant = in.readParcelable(LanguageConstant.class.getClassLoader());
        mLanguageLevel = in.readParcelable(LanguageLevel.class.getClassLoader());
    }


    public LanguageConstant getLanguageConstant() {
        return mLanguageConstant;
    }

    public void setLanguageConstant(LanguageConstant languageConstant) {
        mLanguageConstant = languageConstant;
    }

    public LanguageLevel getLanguageLevel() {
        return mLanguageLevel;
    }

    public void setLanguageLevel(LanguageLevel languageLevel) {
        mLanguageLevel = languageLevel;
    }


    public static final Creator<Language> CREATOR = new Creator<Language>() {
        public Language createFromParcel(Parcel in) {
            return new Language(in);
        }

        public Language[] newArray(int size) {
            return new Language[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mLanguageConstant, PARCELABLE_WRITE_RETURN_VALUE);
        dest.writeParcelable(mLanguageLevel, PARCELABLE_WRITE_RETURN_VALUE);
    }
}
