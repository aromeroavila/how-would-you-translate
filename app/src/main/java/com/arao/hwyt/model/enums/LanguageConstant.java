package com.arao.hwyt.model.enums;

import android.os.Parcel;
import android.os.Parcelable;

import com.arao.hwyt.R;
import com.arao.hwyt.controller.HwytApplication;

import java.util.HashMap;
import java.util.Map;

public enum LanguageConstant implements Parcelable {
    ENGLISH("EN", HwytApplication.getContext().getString(R.string.english), R.drawable.english_flag),
    FRENCH("FR", HwytApplication.getContext().getString(R.string.french), R.drawable.french_flag),
    GERMAN("GE", HwytApplication.getContext().getString(R.string.german), R.drawable.german_flag),
    ITALIAN("IT", HwytApplication.getContext().getString(R.string.italian), R.drawable.italian_flag),
    SPANISH("ES", HwytApplication.getContext().getString(R.string.spanish), R.drawable.spanish_flag);

    private String mIsoCode;
    private String mLanguageName;
    private int mResImageId;

    /**
     * A mapping between the integer mResImageId and its corresponding Status to facilitate lookup by mResImageId.
     */
    private static Map<String, LanguageConstant> codeToStatusMapping;

    private LanguageConstant(String isoCode, String languageName, int resImageId) {
        mLanguageName = languageName;
        mIsoCode = isoCode;
        mResImageId = resImageId;
    }

    public static LanguageConstant getLanguage(String isoCode) {
        if (codeToStatusMapping == null) {
            initMapping();
        }
        return codeToStatusMapping.get(isoCode);
    }

    public static LanguageConstant getLanguageByPosition(int position) {
        return LanguageConstant.values()[position];
    }

    private static void initMapping() {
        codeToStatusMapping = new HashMap<String, LanguageConstant>();
        for (LanguageConstant s : values()) {
            codeToStatusMapping.put(s.getIsoCode(), s);
        }
    }

    public int getResImageId() {
        return mResImageId;
    }

    public String getIsoCode() {
        return mIsoCode;
    }

    public String getLanguageName() {
        return mLanguageName;
    }

    public void setLanguageName(String mLanguageName) {
        this.mLanguageName = mLanguageName;
    }

    @Override
    public String toString() {
        return mLanguageName;
    }

    public static final Creator<LanguageConstant> CREATOR = new Creator<LanguageConstant>() {

        public LanguageConstant createFromParcel(Parcel in) {
            return LanguageConstant.values()[in.readInt()];
        }

        public LanguageConstant[] newArray(int size) {
            return new LanguageConstant[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }
}
