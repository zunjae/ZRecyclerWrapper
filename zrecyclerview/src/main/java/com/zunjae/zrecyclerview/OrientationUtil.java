package com.zunjae.zrecyclerview;

import android.content.Context;
import android.content.res.Configuration;

/**
 * Created by zunjae on 6/4/2017.
 */

final class OrientationUtil {
    private OrientationUtil() {
        throw new RuntimeException("No instances pls");
    }

    public static boolean phoneIsInPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    public static boolean phoneIsInLandscape(Context context) {
        return !phoneIsInPortrait(context);
    }
}
