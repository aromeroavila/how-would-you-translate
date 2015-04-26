package com.arao.hwyt.model;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Question extends Entry {

    @SerializedName("origin_language")
    private Language mFromLanguage;
    @SerializedName("destination_langugae")
    private Language mToLanguage;
    @SerializedName("answers_list")
    private List<Answer> mAnswers;


    public Question() {
        super();
    }

    public Question(String content, User user, Date timePosted, List<Comment> comments, int positiveVotes, int negativeVotes,
                    Language fromLanguage, Language toLanguage, List<Answer> answers) {
        super(content, user, timePosted, comments, positiveVotes, negativeVotes);
        mFromLanguage = fromLanguage;
        mToLanguage = toLanguage;
        mAnswers = answers;
    }

    public Question(Parcel in) {
        super(in);
        mFromLanguage = in.readParcelable(Language.class.getClassLoader());
        mToLanguage = in.readParcelable(Language.class.getClassLoader());
        in.readList(mAnswers, Answer.class.getClassLoader());
    }


    public Language getFromLanguage() {
        return mFromLanguage;
    }

    public void setFromLanguage(Language fromLanguage) {
        mFromLanguage = fromLanguage;
    }

    public Language getToLanguage() {
        return mToLanguage;
    }

    public void setToLanguage(Language toLanguage) {
        mToLanguage = toLanguage;
    }

    public List<Answer> getAnswers() {
        return mAnswers;
    }

    public void setmAnswers(List<Answer> answers) {
        mAnswers = answers;
    }


    @Override
    public int getActivity() {
        int totalActivity = super.getActivity();

        for (int i = 0; i < mAnswers.size(); i++) {
            totalActivity += mAnswers.get(i).getActivity();
        }

        for (int i = 0; i < mComments.size(); i++) {
            totalActivity = mComments.get(i).getActivity();
        }

        return totalActivity;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(getFromLanguage(), PARCELABLE_WRITE_RETURN_VALUE);
        dest.writeParcelable(getFromLanguage(), PARCELABLE_WRITE_RETURN_VALUE);
        dest.writeList(mAnswers);
    }
}
