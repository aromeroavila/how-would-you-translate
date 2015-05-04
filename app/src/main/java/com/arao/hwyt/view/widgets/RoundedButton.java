package com.arao.hwyt.view.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.arao.hwyt.R;

public class RoundedButton extends Button {

    private final float SCALE = getContext().getResources().getDisplayMetrics().density;
    private final int SMALL_GAP_DP = getDpFromPixels(getResources().getDimension(R.dimen.small_gap));
    private final int MEDIUM_GAP_DP = getDpFromPixels(getResources().getDimension(R.dimen.medium_gap));
    private final int BUTTON_SHADOW_SIZE_DP = getDpFromPixels(getResources().getDimension(R.dimen.rounded_button_shadow_size));

    private int mMarginLeft = -1;
    private int mMarginTop = -1;
    private int mMarginRight = -1;
    private int mMarginBottom = -1;


    public RoundedButton(Context context) {
        super(context);
        setBackgroundResource(R.drawable.rounded_button_states);
        setDefaultPadding();
    }

    public RoundedButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundResource(R.drawable.rounded_button_states);
        setDefaultPadding();
    }

    public RoundedButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setBackgroundResource(R.drawable.rounded_button_states);
        setDefaultPadding();
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
//        setPressedMargins(pressed);
    }

    private void setPressedMargins(boolean pressed) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getLayoutParams();
        initSavedMarginValues(params);

        if (pressed) {
            params.setMargins(mMarginLeft + BUTTON_SHADOW_SIZE_DP, mMarginTop + BUTTON_SHADOW_SIZE_DP, mMarginRight, mMarginBottom);
        } else {
            params.setMargins(mMarginLeft, mMarginTop, mMarginRight, mMarginBottom);
        }

        setLayoutParams(params);
    }

    private void initSavedMarginValues(RelativeLayout.LayoutParams params) {
        if (mMarginLeft < 0) {
            mMarginLeft = params.leftMargin;
        }
        if (mMarginTop < 0) {
            mMarginTop = params.topMargin;
        }
        if (mMarginRight < 0) {
            mMarginRight = params.rightMargin;
        }
        if (mMarginBottom < 0) {
            mMarginBottom = params.bottomMargin;
        }
    }

    private void setDefaultPadding() {
        setPadding(MEDIUM_GAP_DP - BUTTON_SHADOW_SIZE_DP, SMALL_GAP_DP - BUTTON_SHADOW_SIZE_DP, MEDIUM_GAP_DP, SMALL_GAP_DP);
    }

    private int getDpFromPixels(float pixelsDimen) {
        return (int) (pixelsDimen * SCALE - 0.5f);
    }
}
