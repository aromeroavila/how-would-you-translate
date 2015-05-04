package com.arao.hwyt.controller.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.arao.hwyt.R;
import com.arao.hwyt.model.Entry;
import com.arao.hwyt.model.Question;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * User: angelromero
 * Date: 07/05/2014
 * Time: 11:49
 *
 * List adapter used to fill the data for every entry list view in the app.
 * It can act as a simple ArrayAdapter if it is set as the adapter of a ListView (like in
 * {@link com.arao.hwyt.controller.fragments.home.FilteredQuestionsFragment})
 * but also act as a ExpandableListAdapter when set to a ExpandableListView (like in
 * {@link com.arao.hwyt.controller.fragments.home.SingleQuestionFragment}).
 */
public class EntryListAdapter extends ArrayAdapter<Question> implements ExpandableListAdapter {

    private final static String DATE_FORMAT = "dd-MM-yyyy";
    private final static int SINGLE_QUESTION_POSITION = 0;
    private final static int HEADER_POSITION = 0;

    private Context mContext;
    private int mLayoutResourceId;
    private List<Question> mQuestions;
    private boolean mSingleQuestionMode;
    private EntryListAdapterListener mEntrListAdapterListener;
    private SimpleDateFormat mDateFormat;


    public EntryListAdapter(Context context, int resource, List<Question> questions, boolean singleQuestionMode,
                            EntryListAdapterListener entryListAdapterListener) {
        super(context, resource);
        mContext = context;
        mLayoutResourceId = resource;
        mQuestions = questions;
        mSingleQuestionMode = singleQuestionMode;
        mEntrListAdapterListener = entryListAdapterListener;
        mDateFormat = new SimpleDateFormat(DATE_FORMAT);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EntryViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(mLayoutResourceId, parent, false);

            viewHolder = initViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (EntryViewHolder) convertView.getTag();
        }

        // We want the getView to return a view only when this object is acting as a ArrayAdapter and not as an
        // ExpandableListAdapter. This happens only for the multiple questions screens
        if (!mSingleQuestionMode) {
            Question question = mQuestions.get(position);
            fillEntryDataIntoViewHolder(viewHolder, question, false);

            TextView answersNumberTextView = (TextView) convertView.findViewById(R.id.question_answers_number_tv);
            answersNumberTextView.setVisibility(View.VISIBLE);
            answersNumberTextView.setText(String.valueOf(question.getAnswers().size()));

            setRowBackground(convertView, position, false);
        }

        return convertView;
    }

    private EntryViewHolder initViewHolder(View convertView) {
        EntryViewHolder viewHolder = new EntryViewHolder();

        viewHolder.userAvatarImageView = (ImageView) convertView.findViewById(R.id.question_user_avatar_iv);
        viewHolder.usernameTextView = (TextView) convertView.findViewById(R.id.question_user_name_tv);
        viewHolder.timePostedTextView = (TextView) convertView.findViewById(R.id.question_time_posted_tv);
        viewHolder.contentTextView = (TextView) convertView.findViewById(R.id.question_content_tv);
        viewHolder.upVotesNumberTextView = (TextView) convertView.findViewById(R.id.positive_votes_number_tv);
        viewHolder.downVotesNumberTextView = (TextView) convertView.findViewById(R.id.negative_votes_number_tv);

        return viewHolder;
    }

    private void fillEntryDataIntoViewHolder(EntryViewHolder viewHolder, Entry entry, boolean isAnswerItem) {
        viewHolder.userAvatarImageView.setImageResource(entry.getUser().getAvatarImage());
        viewHolder.usernameTextView.setText(entry.getUser().getName());
        viewHolder.timePostedTextView.setText(mDateFormat.format(entry.getTimePosted()));
        viewHolder.contentTextView.setText(entry.getContent());
        viewHolder.upVotesNumberTextView.setText(String.valueOf(entry.getPositiveVotes()));
        viewHolder.downVotesNumberTextView.setText(String.valueOf(entry.getNegativeVotes()));

        if (isAnswerItem) {
//            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) viewHolder.userAvatarImageView.getLayoutParams();
//            lp.setMargins((int) mContext.getResources().getDimension(R.dimen.extra_large_gap), 0, 0, 0);
//            viewHolder.userAvatarImageView.setLayoutParams(lp);

            int smallAvatarSize = (int) mContext.getResources().getDimension(R.dimen.avatar_image_small_size);
            viewHolder.userAvatarImageView.getLayoutParams().height = smallAvatarSize;
            viewHolder.userAvatarImageView.getLayoutParams().width = smallAvatarSize;
        }
    }

    private void setRowBackground(View convertView, int position, boolean hasHeader) {
        if (position % 2 == 0) {
            if (position == HEADER_POSITION && hasHeader) {
                convertView.setBackgroundColor(mContext.getResources().getColor(R.color.answers_list_view_header_background_color));
            } else {
                convertView.setBackgroundColor(mContext.getResources().getColor(R.color.entries_list_view_even_row_background_color));
            }
        } else {
            convertView.setBackgroundColor(mContext.getResources().getColor(R.color.entries_list_view_odd_row_background_color));
        }
    }

    @Override
    public int getCount() {
        if (mSingleQuestionMode) {
            // When the adapter is in single question mode, the number of elements of the list is the question
            // as the header plus the number of answers it contains
            return mQuestions.get(SINGLE_QUESTION_POSITION).getAnswers().size() + 1;
        } else {
            return mQuestions.size();
        }
    }


    // ExpandableListAdapter methods

    @Override
    public int getGroupCount() {
        return mQuestions.get(SINGLE_QUESTION_POSITION).getAnswers().size() + 1;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (groupPosition == HEADER_POSITION) {
            return mQuestions.get(SINGLE_QUESTION_POSITION).getComments().size();
        } else {
            return mQuestions.get(SINGLE_QUESTION_POSITION).getAnswers().get(groupPosition - 1).getComments().size();
        }
    }

    // We want the getGroupView to return a view only when this object is acting as a ExpandableListAdapter and not as an
    // ArrayAdapter. This happens only for the simple question screens
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        EntryViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(mLayoutResourceId, parent, false);

            viewHolder = initViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (EntryViewHolder) convertView.getTag();
        }

        if (groupPosition == HEADER_POSITION) {
            fillEntryDataIntoViewHolder(viewHolder, mQuestions.get(SINGLE_QUESTION_POSITION), false);

            View separator2 = convertView.findViewById(R.id.question_line_separator_2);
            separator2.setVisibility(View.VISIBLE);
            final Button answerButton = (Button) convertView.findViewById(R.id.question_answer_b);
            answerButton.setVisibility(View.VISIBLE);

            answerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mEntrListAdapterListener != null) {
                        mEntrListAdapterListener.onAnswerButtonClicked(v);
                    }
                }
            });
        } else {
            fillEntryDataIntoViewHolder(viewHolder,
                    mQuestions.get(SINGLE_QUESTION_POSITION).getAnswers().get(groupPosition - 1), true);
        }

        setRowBackground(convertView, groupPosition, true);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        TextView commentTextView = new TextView(mContext);

        if (groupPosition == HEADER_POSITION) {
            commentTextView.setText(mQuestions.get(SINGLE_QUESTION_POSITION).
                    getComments().get(childPosition).getContent());
        } else {
            commentTextView.setText(mQuestions.get(SINGLE_QUESTION_POSITION).
                    getAnswers().get(groupPosition - 1).getComments().get(childPosition).getContent());
        }

        return commentTextView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }


    private static class EntryViewHolder {
        public ImageView userAvatarImageView;
        public TextView usernameTextView;
        public TextView timePostedTextView;
        public TextView contentTextView;
        public TextView upVotesNumberTextView;
        public TextView downVotesNumberTextView;
    }
}
