package com.mobiixi.andrawer.popup;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.mobiixi.andrawer.R;
import com.mobiixi.andrawer.tools.XiToolBarListener;

/**
 * Created by xichen on 02/01/17.
 */

public class XiBrushSizePopupMenu extends XiPopupMenu {
    private static final String TAG = XiBrushSizePopupMenu.class.getClass().getSimpleName();

    private XiToolBarListener mMenuListener;

    private float mSizeSmall;
    private float mSizeMedium;
    private float mSizeLarge;

    public XiBrushSizePopupMenu(Context context, XiToolBarListener listener) {
        super(context);
        mMenuListener = listener;

        // read sizes
        mSizeSmall = context.getResources().getInteger(R.integer.small_size);
        mSizeMedium = context.getResources().getInteger(R.integer.medium_size);
        mSizeLarge = context.getResources().getInteger(R.integer.large_size);

        // set default size
        mMenuListener.onBrushSizeChanged(mSizeMedium);
    }

    @Override
    public void popUpMenu(View anchor) {
        //int margin = (int) getResources().getDimension(R.dimen.activity_vertical_margin);
        int verticalAdjustment = 5;
        int horizentalAdjustment = 15;
        int offsetX = - anchor.getWidth() - horizentalAdjustment;
        int offsetY = -3 * anchor.getHeight() - verticalAdjustment;
        mPopupWindow.showAsDropDown(anchor, offsetX, offsetY);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.brush_popup_menu;
    }

    @Override
    protected void prepareMenu(View popupView) {
        /*ImageButton smallBrushSymbol = (ImageButton) popupView.findViewById(R.id.small_brush);
        ImageButton mediumBrushSymbol = (ImageButton) popupView.findViewById(R.id.medium_brush);
        ImageButton largeBrushSymbol = (ImageButton) popupView.findViewById(R.id.large_brush);

        smallBrushSymbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMenuListener.onBrushSizeChanged(mSizeSmall);
                mPopupWindow.dismiss();
                Log.d(TAG, "SMALL " + mSizeSmall);
            }
        });

        mediumBrushSymbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMenuListener.onBrushSizeChanged(mSizeMedium);
                mPopupWindow.dismiss();
                Log.d(TAG, "MEDIUM " + mSizeMedium);
            }
        });

        largeBrushSymbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMenuListener.onBrushSizeChanged(mSizeLarge);
                mPopupWindow.dismiss();
                Log.d(TAG, "LARGE " + mSizeLarge);
            }
        });*/
    }
}
