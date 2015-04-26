package com.arao.hwyt.model.enums;

import android.os.Parcel;
import android.os.Parcelable;

import com.arao.hwyt.R;
import com.arao.hwyt.controller.HwytApplication;

public enum LanguageLevel implements Parcelable {
    UNASSIGNED(HwytApplication.getContext().getString(R.string.level_unassigned), -1),
    BEGINNER(HwytApplication.getContext().getString(R.string.level_beginner), 1),
    ELEMENTARY(HwytApplication.getContext().getString(R.string.level_elementary), 2),
    PRE_INTERMEDIATE(HwytApplication.getContext().getString(R.string.level_pre_intermediate), 3),
    INTERMEDIATE(HwytApplication.getContext().getString(R.string.level_intermediate), 4),
    UPPER_INTERMEDIATE(HwytApplication.getContext().getString(R.string.level_upper_intermediate), 5),
    ADVANCED(HwytApplication.getContext().getString(R.string.level_advanced), 6),
    HIGH_ADVANCED(HwytApplication.getContext().getString(R.string.level_high_advanced), 7),
    PROFICIENCY(HwytApplication.getContext().getString(R.string.level_proficiency), 8),
    NATIVE_SPEAKER(HwytApplication.getContext().getString(R.string.level_native_speaker), 9);


    private String mName;
    private int mId;


    private LanguageLevel(String name, int id) {
        mName = name;
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public static String[] getAllDisplayableValues() {
        LanguageLevel[] languageLevels = LanguageLevel.values();
        String[] displayableValues = new String[languageLevels.length - 1];

        for (int i = 1; i < languageLevels.length - 1; i++) {
            displayableValues[i - 1] = languageLevels[i].mName;
        }
        return displayableValues;
    }

    @Override
    public String toString() {
        return mName;
    }


    public static final Creator<LanguageLevel> CREATOR = new Creator<LanguageLevel>() {

        public LanguageLevel createFromParcel(Parcel in) {
            return LanguageLevel.values()[in.readInt()];
        }

        public LanguageLevel[] newArray(int size) {
            return new LanguageLevel[size];
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
