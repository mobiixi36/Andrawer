package com.mobiixi.andrawer.popup;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.mobiixi.andrawer.R;
import com.mobiixi.andrawer.tools.XiToolBarListener;

/**
 * Created by xichen on 02/01/17.
 */

public class XiPalettePopupMenu extends XiPopupMenu {

    private XiToolBarListener mMenuListener;
    private ImageButton mCurrentColorBtn;
    private Context mContext;

    public XiPalettePopupMenu(Context context, XiToolBarListener listener) {
        super(context);
        mContext = context;
        mMenuListener = listener;
        setDefaultColor();
    }

    @Override
    public void popUpMenu(View anchor) {
        int verticalAdjustment = 10;
        int offsetX = - anchor.getWidth();
        int offsetY = - 4 * anchor.getHeight() + verticalAdjustment;
        mPopupWindow.showAsDropDown(anchor, offsetX, offsetY);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.palette;
    }

    @Override
    protected void prepareMenu(View popupView) {
        prepareColorsByRow(R.id.first_row_colors);
        prepareColorsByRow(R.id.second_row_colors);
        prepareColorsByRow(R.id.third_row_colors);
        prepareColorsByRow(R.id.fourth_row_colors);
    }

    private void setDefaultColor() {
        // use first color as default color
        ViewGroup firstColorRow
                = (ViewGroup) mPopupWindow.getContentView().findViewById(R.id.first_row_colors);
        ImageButton firstColorBtn = (ImageButton) firstColorRow.getChildAt(1);
        onNewColorSelected(firstColorBtn);
    }

    private void prepareColorsByRow(int rowViewId) {
        ViewGroup colorRow
                = (ViewGroup) mPopupWindow.getContentView().findViewById(rowViewId);
        int nrOfColors = colorRow.getChildCount();
        for (int i = 0; i < nrOfColors; i ++) {
            final View childView = colorRow.getChildAt(i);
            if (childView instanceof ImageButton) {
                addListenerToColorBtn(childView);
            }
        }
    }

    private void addListenerToColorBtn(View btnView) {
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view != mCurrentColorBtn) {
                    onNewColorSelected(view);
                    mPopupWindow.dismiss();
                }
            }
        });
    }

    private void onNewColorSelected(View selectedColorBtnView) {
        if (mCurrentColorBtn != null) {
            mCurrentColorBtn.setImageDrawable(ContextCompat.getDrawable(mContext,
                                                                        R.drawable.color_unselected));
        }

        ImageButton selectedColorBtn = (ImageButton) selectedColorBtnView;
        selectedColorBtn.setImageDrawable(ContextCompat.getDrawable(mContext,
                                                                    R.drawable.color_selected));
        String colorTag = (String) selectedColorBtn.getTag();
        mCurrentColorBtn = selectedColorBtn;
        mMenuListener.onColorSelected(colorTag);
    }
}
