package com.mobiixi.andrawer.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.mobiixi.andrawer.font.XiSketchyFont;

/**
 * Created by xichen on 03/01/17.
 */

public class XiSketchyTextView extends TextView {
    public XiSketchyTextView(Context context) {
        super(context);
        setTypeface(XiSketchyFont.getInstance(context).getTypeface());
    }

    public XiSketchyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(XiSketchyFont.getInstance(context).getTypeface());
    }

    public XiSketchyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setTypeface(XiSketchyFont.getInstance(context).getTypeface());
    }
}
