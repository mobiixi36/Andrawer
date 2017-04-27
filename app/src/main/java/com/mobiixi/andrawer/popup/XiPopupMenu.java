package com.mobiixi.andrawer.popup;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by xichen on 02/01/17.
 */

public abstract class XiPopupMenu {
    protected Context mContext;
    protected PopupWindow mPopupWindow;

    public XiPopupMenu(Context context) {
        mContext = context;
        View popUpView = preparePopupWindow();
        prepareMenu(popUpView);
    }

    private View preparePopupWindow() {
        LayoutInflater inflater
                = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mPopupWindow = new PopupWindow(mContext);
        View popUpView = inflater.inflate(getLayoutId(), null);
        mPopupWindow.setContentView(popUpView);

        mPopupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);

        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);
        // transparent background
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        return popUpView;
    }

    public abstract void popUpMenu(View anchor);
    protected abstract int getLayoutId();
    protected abstract void prepareMenu(View popupView);

}
