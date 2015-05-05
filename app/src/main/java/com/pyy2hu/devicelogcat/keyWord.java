package com.pyy2hu.devicelogcat;

import android.util.Log;

/**
 * Created by pyy on 2015/5/5.
 */
public class keyWord {
    static final String Tag = "[deviceLogcat]";
    public String keywords="";
    void print () {
        Log.i(Tag, keywords + "\n");
    }

}
