package com.theitfox.picasso;

import android.app.Application;
import android.content.Context;

/**
 * Created by btquanto on 26/09/2016.
 */

public class PicassoApplication extends Application {

    private static Context context;

    public static Context getAppContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
