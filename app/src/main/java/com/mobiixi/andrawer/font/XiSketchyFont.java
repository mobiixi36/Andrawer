package com.mobiixi.andrawer.font;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Use singleton to handle custom font Typeface in order to avoid memory leakage
 * on old Android devices
 *
 * Created by xichen on 03/01/17.
 */

public class XiSketchyFont {
    private static XiSketchyFont mInstance;
    private Typeface mTypeface;
    private XiSketchyFont(Context context) {
        mTypeface = Typeface.createFromAsset(context.getAssets(), "Sketchy.ttf");
    }

    public static XiSketchyFont getInstance(Context context) {
        if(mInstance == null) {
            mInstance = new XiSketchyFont(context);
        }
        return mInstance;
    }

    public Typeface getTypeface() {
        return mTypeface;
    }
}
