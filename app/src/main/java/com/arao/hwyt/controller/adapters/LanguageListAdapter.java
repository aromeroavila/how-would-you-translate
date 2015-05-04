package com.arao.hwyt.controller.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.arao.hwyt.R;
import com.arao.hwyt.model.enums.LanguageConstant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LanguageListAdapter extends ArrayAdapter<LanguageConstant> {

    private Context mContext;
    private int mLayoutResourceId;
    private List<LanguageConstant> mLanguagesList;


    public LanguageListAdapter(Context context, int resource, List<LanguageConstant> excludedLanguages) {
        super(context, resource);

        mContext = context;
        mLayoutResourceId = resource;

        if (excludedLanguages == null) {
            mLanguagesList = new ArrayList<LanguageConstant>(Arrays.asList(LanguageConstant.values()));
        } else {
            mLanguagesList = new ArrayList<LanguageConstant>();
            for (int i=0; i< LanguageConstant.values().length; i++) {
                LanguageConstant language = LanguageConstant.values()[i];
                if (!excludedLanguages.contains(language)) {
                    mLanguagesList.add(language);
                }
            }
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LanguageViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(mLayoutResourceId, parent, false);

            viewHolder = new LanguageViewHolder();
            viewHolder.langItemTextView = (TextView) convertView.findViewById(R.id.lang_item_tv);
            viewHolder.lanItemImageView = (ImageView) convertView.findViewById(R.id.lang_item_iv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (LanguageViewHolder) convertView.getTag();
        }

        LanguageConstant languageConstant = mLanguagesList.get(position);

        viewHolder.langItemTextView.setText(languageConstant.getLanguageName());
        viewHolder.lanItemImageView.setImageResource(languageConstant.getResImageId());

        return convertView;
    }

    @Override
    public int getCount() {
        return mLanguagesList.size();
    }

    public List<LanguageConstant> getLanguagesList() {
        return mLanguagesList;
    }

    private static class LanguageViewHolder {
        public TextView langItemTextView;
        public ImageView lanItemImageView;
    }
}
