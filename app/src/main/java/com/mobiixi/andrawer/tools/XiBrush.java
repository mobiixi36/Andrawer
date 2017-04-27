package com.mobiixi.andrawer.tools;

import android.content.Context;
import android.view.View;

import com.mobiixi.andrawer.popup.XiBrushSizePopupMenu;
import com.mobiixi.andrawer.popup.XiPopupMenu;

/**
 * Created by xichen on 02/01/17.
 */

public class XiBrush {


    private float mSize;
    private float mColor;
    private XiPopupMenu mMenu;

    public XiBrush(Context context, XiToolBarListener listener) {
        // popup menu
        mMenu = new XiBrushSizePopupMenu(context, listener);
    }

    public void setSize(float newSize) {
        mSize = newSize;
    }

    public float getSize() {
        return mSize;
    }

    public void setColor(float newColor) {
        mColor = newColor;
    }

    public float getColor() {
        return mColor;
    }

    public void popUpMenuOptions(View anchor) {
        mMenu.popUpMenu(anchor);
    }
}
