package com.arao.hwyt.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

public class Answer extends Entry {

    public Answer(String content, User user, Date timePosted, List<Comment> comments, int positiveVotes, int negativeVotes) {
        super(content, user, timePosted, comments, positiveVotes, negativeVotes);
    }

    private Answer(Parcel in) {
        super(in);
    }

    @Override
    public int getActivity() {
        int totalActivity = super.getActivity();

        for (int i = 0; i < mComments.size(); i++) {
            totalActivity = mComments.get(i).getActivity();
        }

        return totalActivity;
    }

    public static final Creator<Answer> CREATOR = new Parcelable.Creator<Answer>() {
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };
}
