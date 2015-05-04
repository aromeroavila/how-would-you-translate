package com.arao.hwyt.controller.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

public class DrawerListAdapter extends ArrayAdapter<String> {
    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     */
    public DrawerListAdapter(Context context, int resource) {
        super(context, resource);
    }
}
