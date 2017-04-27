package com.mobiixi.andrawer.popup;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import android.view.View;
import android.widget.SeekBar;

import com.mobiixi.andrawer.R;
import com.mobiixi.andrawer.tools.XiToolBarListener;

/**
 * Created by xichen on 14/01/17.
 */

public class XiEraserSizePopupMenu extends XiPopupMenu {
    private SeekBar mSizeBar;
    private int mSmallestSize;
    private float mCurrentSize;
    private XiToolBarListener mListener;

    public XiEraserSizePopupMenu(Context context, XiToolBarListener listener) {
        super(context);
        mListener = listener;
    }

    @Override
    public void popUpMenu(View anchor) {
        // progress is always starting from 0,
        // that's why we need to subtract the smallest size
        int currentProgress = (int) mCurrentSize - mSmallestSize;
        mSizeBar.setProgress(currentProgress);

        int verticalAdjustment = 20;
        int offsetX = -anchor.getWidth();
        int offsetY = -3 * anchor.getHeight() - verticalAdjustment;
        mPopupWindow.showAsDropDown(anchor, offsetX, offsetY);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.eraser_size_selector;
    }

    @Override
    protected void prepareMenu(View popupView) {
        mSmallestSize = mContext.getResources().getInteger(R.integer.smallest_eraser_size);
        int largestSize = mContext.getResources().getInteger(R.integer.largest_eraser_size);
        final int sizeRange = largestSize - mSmallestSize;

        mSizeBar = (SeekBar) popupView.findViewById(R.id.size_seek_bar);
        mSizeBar.setThumb(ContextCompat.getDrawable(mContext, R.drawable.eraser_thumb));

        // initial size set to half of the size range
        if (mCurrentSize < mSmallestSize) {
            mCurrentSize = sizeRange / 2;
            mSizeBar.setProgress((int) (mCurrentSize));
        }
        // the minimum value of seek bar is always 0,
        // so, we should set the range as its maximum value
        mSizeBar.setMax(sizeRange);

        mSizeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // progress is starting with 0, that's why we need to add the smallest size
                mCurrentSize = progress + mSmallestSize;
                mListener.onEraserSizeChanged(mCurrentSize);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

        });

    }
}
