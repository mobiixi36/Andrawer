package com.mobiixi.andrawer.tools;

import android.content.Context;
import android.view.View;

import com.mobiixi.andrawer.popup.XiEraserSizePopupMenu;
import com.mobiixi.andrawer.popup.XiPopupMenu;

/**
 * Created by xichen on 11/01/17.
 */

public class XiEraser {
    private XiPopupMenu mMenu;
    public XiEraser(Context context, XiToolBarListener listener) {
        mMenu = new XiEraserSizePopupMenu(context, listener);
    }

    public void popUpMenuOptions(View anchor) {
        mMenu.popUpMenu(anchor);
    }
}
