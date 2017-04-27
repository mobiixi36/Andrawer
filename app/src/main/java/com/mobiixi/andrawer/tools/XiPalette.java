package com.mobiixi.andrawer.tools;

import android.content.Context;
import android.view.View;

import com.mobiixi.andrawer.popup.XiPalettePopupMenu;
import com.mobiixi.andrawer.popup.XiPopupMenu;

/**
 * Created by xichen on 02/01/17.
 */

public class XiPalette {
    private XiPopupMenu mMenu;

    public XiPalette(Context context, XiToolBarListener listener) {
        mMenu = new XiPalettePopupMenu(context, listener);
    }

    public void popUpMenuOptions(View anchor) {
        mMenu.popUpMenu(anchor);
    }
}
