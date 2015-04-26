package com.arao.hwyt.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Entry implements Parcelable {

    @SerializedName("id")
    protected int mId;
    @SerializedName("content")
    protected String mContent;
    @SerializedName("user")
    protected User mUser;
    @SerializedName("time")
    protected Date mTimePosted;
    @SerializedName("comment_list")
    protected List<Comment> mComments;
    @SerializedName("positive_votes")
    protected int mPositiveVotes;
    @SerializedName("negative_votes")
    protected int mNegativeVotes;


    public Entry() {

    }

    public Entry(String content, User user, Date timePosted, List<Comment> comments, int positiveVotes, int negativeVotes) {
        mContent = content;
        mUser = user;
        mTimePosted = timePosted;
        mComments = comments;
        mPositiveVotes = positiveVotes;
        mNegativeVotes = negativeVotes;
    }

    protected Entry(Parcel in) {
        mId = in.readInt();
        mContent = in.readString();
        mUser = in.readParcelable(User.class.getClassLoader());
        mTimePosted = new Date(in.readLong());
        in.readList(mComments, Comment.class.getClassLoader());
        mPositiveVotes = in.readInt();
        mNegativeVotes = in.readInt();
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user){
        mUser = user;
    }

    public Date getTimePosted() {
        return mTimePosted;
    }

    public void setTimePosted(Date timePosted) {
        mTimePosted = timePosted;
    }

    public List<Comment> getComments() {
        // This tweak is done as the ExpandableListAdapter uses the size of the returned List to determine the number of
        // children views
        if (mComments == null) {
            return new ArrayList<Comment>(0);
        }
        return mComments;
    }

    public void setComments(List<Comment> comments) {
        mComments = comments;
    }

    public int getPositiveVotes() {
        return mPositiveVotes;
    }

    public void setPositiveVotes(int positiveVotes) {
        mPositiveVotes = positiveVotes;
    }

    public int getNegativeVotes() {
        return mNegativeVotes;
    }

    public void setNegativeVotes(int negativeVotes) {
        mNegativeVotes = negativeVotes;
    }


    /**
     *
     * @return The total activity on this entry
     */
    public int getActivity() {
        return mPositiveVotes + mNegativeVotes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mContent);
        dest.writeParcelable(mUser, PARCELABLE_WRITE_RETURN_VALUE);
        dest.writeLong(mTimePosted.getTime());
        dest.writeList(mComments);
        dest.writeInt(mPositiveVotes);
        dest.writeInt(mNegativeVotes);
    }
}
