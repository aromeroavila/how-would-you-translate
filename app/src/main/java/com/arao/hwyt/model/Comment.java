package com.arao.hwyt.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

public class Comment extends Entry {

    public Comment(String content, User user, Date timePosted, List<Comment> comments, int positiveVotes, int negativeVotes) {
        super(content, user, timePosted, comments, positiveVotes, negativeVotes);
    }

    private Comment(Parcel in) {
        super(in);
    }

    public static final Creator<Comment> CREATOR = new Parcelable.Creator<Comment>() {
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };
}
