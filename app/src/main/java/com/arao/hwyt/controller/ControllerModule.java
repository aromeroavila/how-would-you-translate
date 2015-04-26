package com.arao.hwyt.controller;

import android.content.Context;

public class ControllerModule {

    /**
     * @return application context
     */
    public static Context appContext() {
        return HwytApplication.getContext();
    }
}
